package com.github.FridrikThor.task_tracker.controller;

import com.github.FridrikThor.task_tracker.dto.ProjectDTO;
import com.github.FridrikThor.task_tracker.enums.ProjectStatus;
import com.github.FridrikThor.task_tracker.model.Project;
import com.github.FridrikThor.task_tracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }


    @PostMapping
    public ResponseEntity<Project> registerNewProject(@RequestBody ProjectDTO projectDTO) {
        Project newProject = new Project(projectDTO);
        projectService.addNewProject(newProject);
        //Project newProject = projectService.addNewProject(projectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProject);
    }

    @DeleteMapping(path = "{projectId}")
    public void deleteProject(@PathVariable("projectId") Long projectId) {
        projectService.deleteProject(projectId);
    }

    @PutMapping(path = "{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable("projectId") Long projectId, @RequestBody ProjectDTO projectDTO) {
        Project updatedProject = projectService.updateProject(projectId, projectDTO);
        return ResponseEntity.ok(updatedProject);
    }
}
