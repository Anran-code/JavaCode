package com.ranan.hashset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data  // @Data重写了hashcode和equals方法，可以判断去重
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;
    private int age;
    private double score;

//    @Override
//    public boolean equals(Object o) {
    // 1.自己和自己比
//        if(this == o) return true;
    // 2.o为空或类型不同
//        if(o == null || getClass() != o.getClass()) return false;
    // 3.逐成员比较
//        Student student = (Student) o;
//        return age == student.getAge() && Objects.equals(name,student.name) && score == student.score;
//    }

//    @Override
//    public int hashCode(){
//        return Objects.hash(name,age,score);
//    }

    @Override
    public String toString(){
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score + "}" + '\n';
    }
}
