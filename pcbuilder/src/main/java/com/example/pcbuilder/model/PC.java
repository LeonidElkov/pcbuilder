package com.example.pcbuilder.model;

import jakarta.persistence.*;

@Entity
@Table(name = "computers")
public class PC {
    @Id
    private String name;

    @ManyToOne
    @JoinColumn(name = "processor_name", referencedColumnName = "name")
    private Processor processor;

    @ManyToOne
    @JoinColumn(name = "motherboard_name", referencedColumnName = "name")
    private Motherboard motherboard;

    @ManyToOne
    @JoinColumn(name = "ram_name", referencedColumnName = "name")
    private Ram ram;

    @ManyToOne
    @JoinColumn(name = "gpu_name", referencedColumnName = "name")
    private Gpu gpu;

    @ManyToOne
    @JoinColumn(name = "storage_device_name", referencedColumnName = "name")
    private StorageDevice storageDevice;

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;
    }

    public Ram getRam() {
        return ram;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public Gpu getGpu() {
        return gpu;
    }

    public void setGpu(Gpu gpu) {
        this.gpu = gpu;
    }

    public StorageDevice getStorageDevice() {
        return storageDevice;
    }

    public void setStorageDevice(StorageDevice storageDevice) {
        this.storageDevice = storageDevice;
    }
}
