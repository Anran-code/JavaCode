package com.ranan.practice;

public class Test {
    public static void main(String[] args) {
        // 目标：面向对象编成实现智能家居控制系统
        // 角色：设备（吊灯、电视、洗衣机、落地窗等）
        // 角色具备的功能：开和关
        // 谁控制他们：智能控制系统（设计成单例对象），控制调用设备的开和关
        // 1.定义设备类：创建设备对象代表家里的设备
        // 2.准备家具放到数组中
        Device[] devices = new Device[4];
        devices[0] = new Lamp("Big Lamp",Status.CLOSE);
        devices[1] = new WashMachine("XiaoMi WashMachine",Status.OPEN);
        devices[2] = new Television("SkyWatch Television",Status.OPEN);
        devices[3] = new AirCondition("HaiEr AirCondition"); // DEFAULT = Status.CLOSE

        // 3.为每个设备增加开和关的功能（接口）
        // 4.创建智能控制系统对象，控制设备开关
        SmartHomeControl smartHomeControl = SmartHomeControl.getInstance();
        // 5. 控制电视机器
        smartHomeControl.control(devices[0]);

        System.out.println("=====================================");

        // 6.提示用户操作 a.展示全部设备情况 b.让用户选一个操作
        smartHomeControl.printAllStatus(devices);

        System.out.println("=====================================");

        smartHomeControl.choose(devices);
    }
}
