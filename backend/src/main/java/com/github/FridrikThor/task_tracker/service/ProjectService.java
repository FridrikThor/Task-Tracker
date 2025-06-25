package com.github.FridrikThor.task_tracker.service;

import com.github.FridrikThor.task_tracker.dto.ProjectDTO;
import com.github.FridrikThor.task_tracker.enums.ProjectStatus;
import com.github.FridrikThor.task_tracker.model.Project;
import com.github.FridrikThor.task_tracker.repository.ProjectRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalStateException("Project with id " + projectId + " not found"));
    }

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }


    public void addNewProject(Project project) {
        Optional<Project> projectOptional = projectRepository.findProjectByTitle(project.getTitle());
        if (projectOptional.isPresent()) {
            throw new IllegalStateException("title already taken");
        }
        if(project.getDueDate().isBefore(LocalDate.now())){
            throw new IllegalStateException("due date can not be a day that has passed");
        }

        /*Project newProject = new Project();
        newProject.setTitle(projectDTO.getTitle());
        newProject.setDescription(projectDTO.getDescription());
        newProject.setStatus(projectDTO.getStatus());
        newProject.setDueDate(projectDTO.getDueDate());
        newProject.setOwner(projectDTO.getOwner());*/

        //return projectRepository.save(project);
        projectRepository.save(project);
    }


    public void deleteProject(Long projectId) {
        boolean exists = projectRepository.existsById(projectId);
        if (!exists) {
            throw new IllegalStateException("project with id " + projectId + " does not exist");
        }
        projectRepository.deleteById(projectId);
    }

    public Project updateProject(Long projectId, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalStateException("Project not found"));

        /*if (projectDTO.getDueDate().isBefore(LocalDate.now())) {
            return ResponseEntity.badRequest().body("Due date cannot be in the past");
        }*/
        if (projectDTO.getTitle() != null) {
            project.setTitle(projectDTO.getTitle());
        }
        if (projectDTO.getDescription() != null) {
            project.setDescription(projectDTO.getDescription());
        }
        if (projectDTO.getStatus() != null) {
            project.setStatus(projectDTO.getStatus());
        }
        if (projectDTO.getDueDate() != null && !(projectDTO.getDueDate().isBefore(LocalDate.now()))) {
            project.setDueDate(projectDTO.getDueDate());
        }
        return projectRepository.save(project);
    }
}