package com.example.servertest.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "ITEM")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String ItemName;


    private int Price;

    private String ItemInfo;

    private int count = 0;

    public Long getID() {
        return id;
    }

    public void setID(Long ID) {
        this.id = id;
    }

    public String getItem_Name() {
        return ItemName;
    }

    public void setItem_Name(String item_Name) {
        ItemName = item_Name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getItem_Info() {
        return ItemInfo;
    }

    public void setItem_Info(String item_Info) {
        ItemInfo = item_Info;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
