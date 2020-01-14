package com.example.demo.web;

import com.example.demo.entities.Project;
import com.example.demo.entities.ProjectState;
import com.example.demo.entities.Task;
import com.example.demo.entities.TaskState;
import com.example.demo.service.CollaboratorService;
import com.example.demo.service.GuestService;
import com.example.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;


@Controller
@RequestMapping("/projects")
public class TaskController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private GuestService guestService;

    @Autowired
    private CollaboratorService collaboratorService;

    @RequestMapping(value = "{id}/task/new", method = RequestMethod.GET)
    public String newtaskGet(Model model, @PathVariable String id) {
        model.addAttribute("title", "Add task");
        model.addAttribute("task", new Task());
        model.addAttribute("taskState", TaskState.values());
        model.addAttribute("projectId", id);
        model.addAttribute("users", managerService.getCollaboratorsList());
        return "projects/taskForm";
    }

    @RequestMapping(value = "{idP}/task/new", method = RequestMethod.POST)
    public String newtaskPost(Model model, @PathVariable String idP, @ModelAttribute @Valid Task task, Errors errors, Principal principal, @RequestParam String u) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add task");
            model.addAttribute("errorTask", "errorTask");
            model.addAttribute("task", new Task());
            model.addAttribute("taskState", TaskState.values());
            model.addAttribute("users", managerService.getCollaboratorsList());
            return "projects/taskForm";
        }
        task.setUser(guestService.signIn(u));
        task.setFinishDate(new Date());//add to formTask
        managerService.addTask(task,Integer.parseInt(idP),principal.getName());
        return "redirect:/projects/"+idP+"/task/new?success";
    }

    @RequestMapping(value = "{id}/task/{idT}/delete")
    public String deleteTask(@PathVariable String id, @PathVariable String idT , Principal principal) {
        managerService.deleteTask(Integer.parseInt(idT), Integer.parseInt(id),principal.getName());
        return "redirect:/projects/"+id;
    }

    @RequestMapping(value = "{id}/task/{idT}/edit", method = RequestMethod.GET)
    public String editTaskGet(Model model, @PathVariable String id, @PathVariable String idT, Principal principal) {
        model.addAttribute("title", "Edit task");
        model.addAttribute("task", managerService.getTask(Integer.parseInt(idT),Integer.parseInt(id),principal.getName()));
        model.addAttribute("taskState", TaskState.values());
        model.addAttribute("projectId", id);
        model.addAttribute("users", managerService.getCollaboratorsList());
        return "projects/taskForm";
    }

    @RequestMapping(value = "{idP}/task/{id}/edit", method = RequestMethod.POST)
    public String editTaskPost(Model model, @PathVariable String idP, @PathVariable String id, @ModelAttribute @Valid Task task, Errors errors, Principal principal, @RequestParam String u) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit task");
            model.addAttribute("error", "error");
            model.addAttribute("task", managerService.getTask(Integer.parseInt(id),Integer.parseInt(idP),principal.getName()));
            model.addAttribute("taskState", TaskState.values());
            model.addAttribute("users", managerService.getCollaboratorsList());
            return "projects/taskForm";
        }
        task.setUser(guestService.signIn(u));
        task.setFinishDate(new Date());//add to formTask
        managerService.addTask(task,Integer.parseInt(idP),principal.getName());
        return "redirect:/projects/"+idP+"/task/new?success";
    }

    @RequestMapping(value = "{idP}/task/{id}/confirm", method = RequestMethod.GET)
    public String confirm(@PathVariable String idP, @PathVariable String id, Principal principal) {
        collaboratorService.confirmTask(Integer.parseInt(id), principal.getName());
        return "redirect:/profile?confirmed";
    }
}
