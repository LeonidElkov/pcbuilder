package com.example.pcbuilder.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ram")
public class Ram {
    @Id
    private String name;

    private int capacity; // в ГБ
    private int speed; // в МГц
    private String type;

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
