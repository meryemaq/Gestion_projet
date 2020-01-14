package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.ManagerRequest;
import com.example.demo.entities.Project;
import com.example.demo.entities.Task;

import java.util.List;

public interface CollaboratorService {
    void sendManagerRequest(ManagerRequest req, String username);

    void confirmTask(int idTask, String username);

    List<Project> getAllProjects(String username);

    Project getProjectWithTasks(int idProject, String username);

    List<Task> getAllTasks(String username);
}