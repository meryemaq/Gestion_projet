package com.example.demo.service.impl;

import com.example.demo.entities.Message;
import com.example.demo.entities.Project;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repo.MessageRepository;
import com.example.demo.repo.ProjectRepository;
import com.example.demo.repo.RoleRepository;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class GuestServiceImpl implements GuestService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void signUp(User user) {
        userRepository.save(user);
        Role r=roleRepository.findOne(3);
        user.setRoles(new ArrayList<Role>(){
            {
                add(r);
            }
        });
    }

    @Override
    public User signIn(String usernameOrEmail) {
        return userRepository.findByUsername(usernameOrEmail);
    }

    @Override
    public void sendMessage(Message msg) {
        msg.setDate(new Date());
        messageRepository.save(msg);
    }

    @Override
    public List<Project> search(String keyword) {
        return projectRepository.search(keyword);
    }
}
