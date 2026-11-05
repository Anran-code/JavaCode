package com.ranan.method1reference;

public class Demo3 {
    public static void main(String[] args) {
        // 构造器引用
        // 1.匿名内部类
        CarFactory cf = new CarFactory() {
            @Override
            public Car getCar(String name) {
                return new Car(name);
            }
        };
        Car c1 = cf.getCar("奔驰");
        System.out.println(c1.getName());


        // 2. lambda表达式
        CarFactory cf1 = name -> new Car(name);
        Car c2 = cf1.getCar("劳斯莱斯");
        System.out.println(c2.getName());

        // 3.构造器引用：如果某个Lambda表达式只是在创建对象，并且"->"前后参数情况一致，可以使用构造器引用
        CarFactory cf2 = Car::new;
        Car c3 = cf2.getCar("大众");
        System.out.println(c3.getName());
    }
}

interface CarFactory {
    Car getCar(String name);
}

class Car{
    private String name;

    public Car() {
    }

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}