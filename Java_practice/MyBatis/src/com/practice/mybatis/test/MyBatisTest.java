package com.practice.mybatis.test;

import com.practice.mybatis.pojo.Student;
import com.practice.mybatis.util.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {

    @Test
    public void testSelectById() throws IOException {
        String resource = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 创建 SqlSessionFactory    Session:会话 (连接数据库后就建立了一次会话，有了会话就可以操作数据库)
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执行sql语句
        //第一个参数为namespace.id
        //查找一个调用selectOne
        Student student = sqlSession.selectOne("student.selectById", 2);
        System.out.println(student);
    }

    @Test
    public void testSelectById2() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        Student student = sqlSession.selectOne("student.selectById", 4);
        System.out.println(student);
    }
}
