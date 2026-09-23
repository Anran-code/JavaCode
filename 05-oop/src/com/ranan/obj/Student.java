package com.ranan.obj;

public class Student {
    String name;
    Double chinese;
    Double math;

    public Student(String _name){
        name =_name;
    }

    public void getAllScore(){
        System.out.println(name + "的总成绩是：" + (chinese + math));
    }

    public void love(String name){
        System.out.println(this.name + "喜欢" + name);
    }
}
