package com.practice.springmvc.dao.Impl;

import com.practice.springmvc.dao.IStudentDao;
import com.practice.springmvc.pojo.Student;
import com.practice.springmvc.util.MyBatisUtil;
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

    @Override
    public void add(Student student) {
        sqlSession.insert("student.add",student);
    }

    @Override
    public Student selectById(Integer id) {
        return sqlSession.selectOne("student.selectById", id);
    }

    @Override
    public void update(Student student) {
        sqlSession.update("student.update", student);
        sqlSession.commit();
    }

}
