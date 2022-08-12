package com.practice.ssm.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        String resource = "mybatis.xml";
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            // 创建 SqlSessionFactory    Session:会话 (连接数据库后就建立了一次会话，有了会话就可以操作数据库)
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static SqlSession getSqlSession() {
        // 得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }
}
