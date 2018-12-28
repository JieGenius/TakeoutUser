package com.example.genius.takeoutuser;

public class SubmitMenuItem {
    String name;
    String price;

    public SubmitMenuItem(String name, String price) {
        this.name = name+":";
        this.price = price+"元";
    }
}
