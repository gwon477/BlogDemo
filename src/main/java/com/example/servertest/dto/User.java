package com.example.servertest.dto;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID ;
    private int Point;

    public Long getId() {
        return ID;
    }

    public void setId(Long id) {
        this.ID = id;
    }

    public int getPoint() {
        return Point;
    }

    public void setPoint(int point) {
        this.Point = point;
    }
}
