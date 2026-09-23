package com.ranan.practice;

public class Movie {
    private int id;
    private String name;
    private double price;

    public Movie(int _id , String _name, double _price){
        id = _id;
        name = _name;
        price = _price;
    }

    public Movie(){}

    public void setId(int _id){
        id = _id;
    }

    public void setName(String _name){
        name = _name;
    }

    public void setPrice(double _price){
        price = _price;
    }

    public int getId(){
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
