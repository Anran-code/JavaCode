package com.ranan.genericity2;

// 实现类实现对具体类型的实现
public class StudentData implements Data<Student>{
    @Override
    public void add(Student student) {

    }

    @Override
    public void delete(Student student) {

    }

    @Override
    public void update(Student student) {

    }

    @Override
    public Student query(int id) {
        return null;
    }
}
