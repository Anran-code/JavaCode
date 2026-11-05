package com.ranan.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher implements Comparable<Teacher> {
    private String name;
    private int age;
    private double salary;

    // 方案一：
    // t2.compareTo(t1)
    // t2 == this 比较者
    // t1 == o 被比较者
    // 规定，认为左边大于右边，返回正整数，反之负整数，等于返回0
    @Override
    public int compareTo(Teacher o) {
        // if(age == o.getAge()) return 1;
        return Integer.compare(age,o.getAge()); // 相等会直接省略，只留一个，这里可以把相等归到一类里面去，就不会省略掉 if(age == o.getAge()) return 1
    }

    @Override
    public String toString(){
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary + "}";
    }
}
