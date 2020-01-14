package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.ManagerRequest;
import com.example.demo.entities.Message;

import java.util.List;


public interface AdminService {
    List<ManagerRequest> getRequests();

    void acceptRequest(int idReq);

    List<Message> getMessages();
}
