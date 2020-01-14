package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.ManagerRequest;

@Repository
public interface ManagerRequestRepository extends JpaRepository<ManagerRequest, Integer>{
}
