package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
