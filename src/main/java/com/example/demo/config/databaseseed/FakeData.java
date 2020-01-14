package com.example.demo.config.databaseseed;

import com.github.javafaker.Faker;
import com.example.demo.entities.*;
import com.example.demo.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/*
@Component
@Transactional
public class FakeData implements ApplicationListener<ContextRefreshedEvent> {

	private Faker faker;

    @Autowired
    private MessageRepository msgRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        faker = new Faker();

        // Inserting 10 messages
        Message m;
        for (int i = 0; i < 10; i++) {
            m = new Message();
            m.setSender(faker.internet().emailAddress());
            String msg;
            do{
                msg = faker.lorem().paragraph(2);
            }while(msg.length()>200);
            m.setMessage(msg);
            m.setDate(new Date());
            msgRepo.save(m);
        }

        // Inserting 3 roles
        Role r = new Role();
        r.setRole("Admin");
        roleRepo.save(r);
        Role r2 = new Role();
        r2.setRole("Manager");
        roleRepo.save(r2);
        Role r3 = new Role();
        r3.setRole("Collaborator");
        roleRepo.save(r3);

        // Inserting 3 users and assigning roles
        User u = new User();
        u.setAvatar("img_avatar1.png");
        u.setCryptPassword(encoder.encode("123"));
        u.setEmail("meryem@gmail.com");
        u.setUsername("meryem");
        u.setRoles(new ArrayList<Role>() {
            {
                add(r);
                add(r2);
                add(r3);
            }
        });
        userRepo.save(u);
        User u2 = new User();
        u2.setAvatar("img_avatar1.png");
        u2.setCryptPassword(encoder.encode("123"));
        u2.setEmail("mouna@gmail.com");
        u2.setUsername("mouna");
        u2.setRoles(new ArrayList<Role>() {
            {
                add(r3);
            }
        });
        userRepo.save(u2);
        
        User u3 = new User();
        u3.setAvatar("img_avatar1.png");
        u3.setCryptPassword(encoder.encode("123"));
        u3.setEmail("maha@gmail.com");
        u3.setUsername("maha");
        u3.setRoles(new ArrayList<Role>() {
            {
                add(r3);
            }
        });
        userRepo.save(u3);

        // Inserting 5 projects and assigning users
        for (int i=0; i<5; i++) {
            Project p = new Project();
            p.setName("Project "+(i+1));
            String msg;
            do {
                msg = faker.lorem().paragraph(4);
            } while (msg.length() > 800);
            p.setDescription(msg);
            p.setCover("cover"+(i+1)+".jpg");
            p.setInitDate(new Date());
            if (i == 4){
                p.setState(ProjectState.FINISHED);
            }else if (i == 2){
                p.setState(ProjectState.STOPPED);
            }else {
                p.setState(ProjectState.STARTED);
            }
            if (i>2) {
                p.setUsers(new ArrayList<User>() {
                    {
                        add(u);
                        add(u2);
                    }
                });
            }else {
                p.setUsers(new ArrayList<User>() {
                    {
                        add(u);
                    }
                });
            }
            projectRepository.save(p);
        }

        // Inserting tasks to project 1
        for (int i=0; i<7;i++) {
            Task t = new Task();
            t.setTitle("Task "+(i+1));
            String msg;
            do {
                msg = faker.lorem().paragraph(4);
            } while (msg.length() > 1000);
            t.setDescription(msg);
            t.setInitDate(new Date());
            if (i==2 || i==3) {
                t.setState(TaskState.CONFIRMED);
                t.setFinishDate(faker.date().future(30, TimeUnit.DAYS, t.getInitDate()));
                t.setRealFinishDate(faker.date().future(30, TimeUnit.DAYS, t.getInitDate()));
            }else if (i==5) {
                t.setState(TaskState.APPROVED);
                t.setFinishDate(faker.date().future(28, TimeUnit.DAYS, t.getInitDate()));
                t.setRealFinishDate(faker.date().future(15, TimeUnit.DAYS, t.getInitDate()));
            }else {
                t.setState(TaskState.ASSIGNED);
                t.setFinishDate(faker.date().future(15, TimeUnit.DAYS, t.getInitDate()));
                t.setRealFinishDate(faker.date().future(25, TimeUnit.DAYS, t.getInitDate()));
            }
            if (i%2==0) {
                t.setProject(projectRepository.findOne(1));
            }else {
                t.setProject(projectRepository.findOne(2));
            }
            if (i % 3 == 0 )
                t.setUser(u);
            else {
                t.setUser(u2);
            }
            taskRepository.save(t);
        }
    }
}
*/