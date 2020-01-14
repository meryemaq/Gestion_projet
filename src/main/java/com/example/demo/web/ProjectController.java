package com.example.demo.web;

import com.example.demo.entities.Project;
import com.example.demo.entities.ProjectState;
import com.example.demo.service.CollaboratorService;
import com.example.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping(value = "/projects")
public class ProjectController {

    @Autowired
    private CollaboratorService collaboratorService;

    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, Principal principal) {
        model.addAttribute("title", "Projects");
        model.addAttribute("projects", collaboratorService.getAllProjects(principal.getName()));
        return "projects/index";
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newGet(Model model) {
        model.addAttribute("title", "Add project");
        model.addAttribute("project", new Project());
        model.addAttribute("projectState", ProjectState.values());
        return "projects/form";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String newPost(Model model, @ModelAttribute @Valid Project project, Errors errors, Principal principal) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add project");
            model.addAttribute("error", "error");
            model.addAttribute("projectState", ProjectState.values());
            return "projects/form";
        }
        project.setCover("cover.jpg");
        managerService.addProject(project ,principal.getName());
        return "redirect:/projects/new?success";
    }

    @RequestMapping(value = "{id}/delete")
    private String delete(@PathVariable String id, Principal principal) {
        managerService.deleteProject(Integer.parseInt(id), principal.getName());
        return "redirect:/projects";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    private String updateGet(Model model, @PathVariable String id, Principal principal){
        model.addAttribute("title", "Add project");
        model.addAttribute("project", collaboratorService.getProjectWithTasks(Integer.parseInt(id), principal.getName()));
        model.addAttribute("projectState", ProjectState.values());
        return "projects/form";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.POST)
    public String updatePost(Model model, @PathVariable String id, @ModelAttribute @Valid Project project, Errors errors, Principal principal) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit project");
            model.addAttribute("error", "error");
            return "projects/form";
        }
        project.setCover("cover.jpg");
        project.setId(Integer.parseInt(id));
        managerService.updateProject(project ,principal.getName());
        return "redirect:/projects/new?successEdit";
    }
/*
    @RequestMapping(value = "{id}/statistic", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> statistic(@PathVariable String id, Principal principal) {
        //return managerService.getStatistic(Integer.parseInt(id), principal.getName());
        managerService.getStatistic(Integer.parseInt(id), principal.getName());
        return "projects/statistics";
    }
*/
    @RequestMapping(value = "{id}/statistic", method = RequestMethod.GET)
    public String statistics(@PathVariable String id, Principal principal,Model model) {
        //return managerService.getStatistic(Integer.parseInt(id), principal.getName());
        Map<String,Object> mng = managerService.getStatistic(Integer.parseInt(id), principal.getName());
    	model.addAttribute("map", mng);
        return "projects/statistics";
    }
    @RequestMapping(value = "{id}")
    public String home (@PathVariable String id, Principal principal, Model model) {
        Project p = collaboratorService.getProjectWithTasks(Integer.parseInt(id), principal.getName());
        model.addAttribute("title", "Project Manager | "+p.getName());
        model.addAttribute("project", p);
        return "projects/home";
    }
}
