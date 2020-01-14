package com.example.demo.service.impl;

import com.example.demo.entities.ManagerRequest;
import com.example.demo.entities.Message;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repo.ManagerRequestRepository;
import com.example.demo.repo.MessageRepository;
import com.example.demo.repo.RoleRepository;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ManagerRequestRepository requestRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<ManagerRequest> getRequests() {
        return requestRepository.findAll();
    }

    @Override
    public void acceptRequest(int idReq) {
        User user = requestRepository.findOne(idReq).getUser();
        List<Role> list = user.getRoles();
        list.add(roleRepository.findByRole("Manager"));
        user.setRoles(list);
        userRepository.save(user);
        requestRepository.delete(idReq);
    }

    @Override
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }
}
