package com.github.FridrikThor.task_tracker.service;

import com.github.FridrikThor.task_tracker.dto.ProjectDTO;
import com.github.FridrikThor.task_tracker.enums.ProjectStatus;
import com.github.FridrikThor.task_tracker.model.Project;
import com.github.FridrikThor.task_tracker.repository.ProjectRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
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
        projectRepository.save(project);
    }


    public void deleteProject(Long projectId) {
        boolean exists = projectRepository.existsById(projectId);
        if (!exists) {
            throw new IllegalStateException("project with id " + projectId + " does not exist");
        }
        projectRepository.deleteById(projectId);
    }

    public void updateProject(Long projectId, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalStateException("Project not found"));

        if (projectDTO.getTitle() != null) {
            project.setTitle(projectDTO.getTitle());
        }
        if (projectDTO.getDescription() != null) {
            project.setDescription(projectDTO.getDescription());
        }
        if (projectDTO.getStatus() != null) {
            project.setStatus(projectDTO.getStatus());
        }
        if (projectDTO.getDueDate() != null) {
            project.setDueDate(projectDTO.getDueDate());
        }

        projectRepository.save(project);
    }


}
