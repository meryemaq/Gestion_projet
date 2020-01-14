package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Project;
import com.example.demo.entities.Task;
import com.example.demo.entities.User;

import java.util.List;
import java.util.Map;


public interface ManagerService {
    void addProject(Project project, String user);

    void updateProject(Project project, String user);

    void deleteProject(int idProject, String user);

    Task getTask(int idT, int idP, String user);

    void addTask(Task task, int idProject, String user);

    void updateTask(Task task, int idProject, String user);

    void deleteTask(int idTask, int idProject, String user);

    void approveTask(int idTask, String user);

    Map<String, Object> getStatistic(int idProject, String user);

    List<User> getCollaboratorsList();
}
