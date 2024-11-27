package com.github.FridrikThor.task_tracker.controller;

import com.github.FridrikThor.task_tracker.model.Project;
import com.github.FridrikThor.task_tracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // Retrieve all projects
    @GetMapping
    public List<Project> getProjects() {
        return projectService.getProjects();
    }

    // Add a new project
    @PostMapping
    public void registerNewProject(@RequestBody Project project) {
        projectService.addNewProject(project);
    }

    // Delete a project by ID
    @DeleteMapping(path = "{projectId}")
    public void deleteProject(@PathVariable("projectId") Long projectId) {
        projectService.deleteProject(projectId);
    }
}
