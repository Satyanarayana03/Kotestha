package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, String> {
}
