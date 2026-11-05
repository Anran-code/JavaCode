package com.ranan.practice;

import java.util.Scanner;

public class SmartHomeControl {
    private static final SmartHomeControl smartHomeControl = new SmartHomeControl(); // 单例

    private SmartHomeControl() {
    }

    public static SmartHomeControl getInstance(){
        return smartHomeControl;
    }

    public void control(Device device){ // 使用了多态
        System.out.println(device.getName() + "现在状态为：" + device.getStatus());
        device.press();
        System.out.println("已更改为：" + device.getStatus());
    }

    public void printAllStatus(Device[] devices){
        for(int i = 0;i < devices.length;++i){
            System.out.println(i + 1 +" : " + devices[i].getName() + "现在状态为：" + devices[i].getStatus());
        }
    }

    public void choose(Device[] devices){
        while(true){
            System.out.printf("请您选择要控制的设备（选择序号1，2，3，4）：");
            Scanner sc = new Scanner(System.in);
            int index;
            if(sc.hasNextInt()){
                index = sc.nextInt();
                if(index > 0 && index <= devices.length){
                    control(devices[index - 1]);
                    return;
                }
            } else {
                sc.next();
            }
            System.out.println("输入不合法请重新输入！");
        }
    }
}
