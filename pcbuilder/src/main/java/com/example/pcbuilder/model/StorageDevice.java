package com.example.pcbuilder.model;

import jakarta.persistence.*;

@Entity
@Table(name = "storagedevices")
public class StorageDevice {
    @Id
    private String name;

    private int capacity; // в ГБ
    private String type; // HDD или SSD

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
