package com.ranan.practice;

public class Card {
    private String car_id;
    private String owner_name;
    private String phone_number;
    private double balance;
    private double Discount;

    public Card(String car_id, String owner_name, String phone_number, double balance) {
        this.car_id = car_id;
        this.owner_name = owner_name;
        this.phone_number = phone_number;
        this.balance = balance;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public void topUp(double money){
        balance += money;
        System.out.println("成功充值：" + money + " 元，余额为：" + getBalance() + " 元");
    }

    public void consume(double money){}
}
