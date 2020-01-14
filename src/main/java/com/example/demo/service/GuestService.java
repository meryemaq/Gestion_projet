package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Message;
import com.example.demo.entities.Project;
import com.example.demo.entities.User;

import java.util.List;


public interface GuestService {
    void signUp(User user);

    User signIn(String usernameOrEmail);

    void sendMessage(Message msg);

    List<Project> search(String keyword);
}
