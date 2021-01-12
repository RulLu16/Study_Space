package com.example.core.sigleton;

public class StatefulService {

    private int price; // 상태 유지 필드

    public int order(String name, int price){
        //this.price = price; // problem!!
        return price;
    }

    public int getPrice(){
        return price;
    }
}
