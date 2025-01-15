package com.example.pcbuilder.repository;

import com.example.pcbuilder.model.PC;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PCRepository extends JpaRepository<PC, String> {
}
