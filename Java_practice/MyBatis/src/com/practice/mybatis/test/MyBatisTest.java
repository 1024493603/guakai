package com.practice.mybatis.test;

import com.practice.mybatis.pojo.Banji;
import com.practice.mybatis.pojo.Student;
import com.practice.mybatis.util.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void selectAll() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        List<Student> students = sqlSession.selectList("student.selectAll");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void delete() {
        // Setting autocommit to false on JDBC Connection
        // MyBatis把JDBC的autocommit设置为false，
        // 当执行delete的时候并没有真正提交到数据库，对于更新类的操作需要手动提交。
        // 在JDBC里面默认不需要用户手动提交因为autocommit 默认就是true，执行executeUpdate
        // 的时候直接修改了数据库
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        int count = sqlSession.delete("student.deleteById", 15);
        System.out.println(count);
        //需要手动提交
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void add() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        Student student = new Student("王五",24,"男",1);
        int count = sqlSession.insert("student.add", student);
        System.out.println(count);
        //需要手动提交
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void update() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        Student student = new Student("王",2,"男",1);
        student.setId(16);
        int count = sqlSession.update("student.update", student);
        System.out.println(count);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectByPage() throws IOException {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        int pageNo = 2;
        int pageSize = 3;
        int offset = (pageNo - 1) * pageSize;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("offset", offset);
        map.put("pageSize", pageSize);
        List<Student> list = sqlSession.selectList("student.selectByPage", map);
        for (Student student : list) {
            System.out.println(student);
        }
    }

    @Test
    public void testSelectTotalCount() throws IOException {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        int count = sqlSession.selectOne("student.selectTotalCount");
        System.out.println("count: " + count);
    }

    @Test
    public void testSelectStudentBanjiInfo() throws IOException {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        List<Student> list = sqlSession.selectList("student.selectStudentBanjiInfo");
        for (Student student : list) {
            System.out.println(student);
        }
    }

    @Test
    public void testSelectBanjiStudentInfo() throws IOException {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        List<Banji> list = sqlSession.selectList("banji.selectBanjiStudentInfo");
        for (Banji banji : list) {
            System.out.println(banji);
            List<Student> studentList = banji.getList();
            for (Student student : studentList) {
                System.out.println(student);
            }
        }
    }

    @Test
    public void selectByCondition() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        Student stu = new Student();
        stu.setName("王");
        List<Student> list = sqlSession.selectList("student.selectByCondition", stu);
        for (Student student : list) {
            System.out.println(student);
        }
    }

    @Test
    public void updateCondition() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        Student stu = new Student();
        stu.setId(6);
        stu.setAge(55);
        stu.setName("哈人");
        int count = sqlSession.update("student.updateCondition", stu);
        System.out.println(count);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteAllByArray() {
        int[] array = {1,2,3};
        SqlSession sqlSession = MyBatisUtil.getSqlSession();;
        int count = sqlSession.update("student.deleteAllByArray", array);
        System.out.println("count: " + count);
//        sqlSession.commit();
//        sqlSession.close();
    }

    @Test
    public void testDeleteAllByList() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(11);
        list.add(12);
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        int count = sqlSession.update("student.deleteAllByList", list);
        System.out.println("count: " + count);
        sqlSession.commit();
        sqlSession.close();
    }
}
