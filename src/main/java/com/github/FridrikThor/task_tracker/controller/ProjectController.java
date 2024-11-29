package com.github.FridrikThor.task_tracker.controller;

import com.github.FridrikThor.task_tracker.dto.ProjectDTO;
import com.github.FridrikThor.task_tracker.enums.ProjectStatus;
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

    @GetMapping
    public List<Project> getProjects() {
        return projectService.getProjects();
    }

    @PostMapping
    public void registerNewProject(@RequestBody Project project) {
        projectService.addNewProject(project);
    }

    @DeleteMapping(path = "{projectId}")
    public void deleteProject(@PathVariable("projectId") Long projectId) {
        projectService.deleteProject(projectId);
    }

    @PutMapping(path = "/{projectId}")
    public void updateProject(@PathVariable("projectId") Long projectId, @RequestBody ProjectDTO projectDTO) {
        projectService.updateProject(projectId, projectDTO);
    }
}
