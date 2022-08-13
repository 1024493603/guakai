package com.practice.ssm.dao.Impl;

import com.practice.ssm.dao.IStudentDao;
import com.practice.ssm.pojo.Student;
import com.practice.ssm.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements IStudentDao {
    private SqlSession sqlSession;

    public StudentDaoImpl() {
        sqlSession = MyBatisUtil.getSqlSession();
    }

    @Override
    public List<Student> selectByPage(Integer offset, Integer pageSize) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(offset);
        integerList.add(pageSize);
        return sqlSession.selectList("student.selectByPage", integerList);
    }

    @Override
    public Integer countTotalNum() {
        return sqlSession.selectOne("student.countTotalNum");
    }

    @Override
    public void deleteById(Integer id) {
        sqlSession.delete("student.deleteById", id);
        sqlSession.commit();
    }

}
