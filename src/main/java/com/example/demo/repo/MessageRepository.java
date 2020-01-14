package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
