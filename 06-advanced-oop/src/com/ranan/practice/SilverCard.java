package com.ranan.practice;

public class SilverCard extends Card {

    public SilverCard(String car_id, String owner_name, String phone_number, double balance){
        super(car_id,owner_name,phone_number,balance);
        setDiscount(0.9);
    }

    @Override
    public void consume(double money){
        double consume = money * getDiscount();
        if(getBalance() < consume){
            System.out.println("余额不足（" + getBalance() + "元），不能支付 " + consume + " 元");
        } else {
            setBalance(getBalance() - consume);
            System.out.println("支付成功！本次消费：" + consume + " 元，（余额：" + getBalance() + " 元）");
        }
    }
}
