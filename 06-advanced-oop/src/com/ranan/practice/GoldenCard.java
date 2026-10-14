package com.ranan.practice;

public class GoldenCard extends Card {
    public GoldenCard(String car_id, String owner_name, String phone_number, double balance) {
        super(car_id, owner_name, phone_number, balance);
        setDiscount(0.8);
    }

    @Override
    public void consume(double money){
        double consume = money * getDiscount();
        if(getBalance() < consume){
            System.out.println("余额不足（" + getBalance() + "元），不能支付 " + consume + " 元");
        } else {
            setBalance(getBalance() - consume);
            System.out.println("支付成功！本次消费：" + consume + " 元，（余额：" + getBalance() + " 元）");
            if (consume >= 200) {
                System.out.println("此次消费：" + consume + "元，超过 200 元，将提供免费洗车票服务");
            }
        }
    }
}
