package com.example.pcbuilder.repository;

import com.example.pcbuilder.model.Gpu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GpuRepository extends JpaRepository<Gpu, String> {
}
