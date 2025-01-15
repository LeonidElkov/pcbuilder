package com.example.pcbuilder.model;

import jakarta.persistence.*;

@Entity
@Table(name = "processors")
public class Processor {
    @Id
    private String name;

    private int cores;
    private int threads;
    private double baseClock;
    private double boostClock;
    private String socketType;

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public double getBaseClock() {
        return baseClock;
    }

    public void setBaseClock(double baseClock) {
        this.baseClock = baseClock;
    }

    public double getBoostClock() {
        return boostClock;
    }

    public void setBoostClock(double boostClock) {
        this.boostClock = boostClock;
    }

    public String getSocketType() {
        return socketType;
    }

    public void setSocketType(String socketType) {
        this.socketType = socketType;
    }
}
