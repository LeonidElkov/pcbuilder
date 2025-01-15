package com.example.pcbuilder.repository;

import com.example.pcbuilder.model.StorageDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageDeviceRepository extends JpaRepository<StorageDevice, String> {
}
