package com.github.FridrikThor.task_tracker.service;


import com.github.FridrikThor.task_tracker.dto.TaskDTO;
import com.github.FridrikThor.task_tracker.model.Task;
import com.github.FridrikThor.task_tracker.repository.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    public void addNewTask(Task task) {
        Optional<Task> taskOptional = taskRepository.findTaskByTitle(task.getTitle());

        if(taskOptional.isPresent()){
            throw new IllegalStateException("title taken");
        }
        taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        boolean exists = taskRepository.existsById(taskId);
        if(!exists){
            throw new IllegalStateException("task with id " + taskId + " does not exist");
        }
        taskRepository.deleteById(taskId);
    }

    public void updateTask(Long taskId, TaskDTO taskDTO) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalStateException("Task not found"));

        // Update fields if they are not null in the DTO
        if (taskDTO.getTitle() != null) {
            task.setTitle(taskDTO.getTitle());
        }
        if (taskDTO.getDescription() != null) {
            task.setDescription(taskDTO.getDescription());
        }
        if (taskDTO.getStatus() != null) {
            task.setStatus(taskDTO.getStatus());
        }
        if (taskDTO.getPriority() != null) {
            task.setPriority(taskDTO.getPriority());
        }
        if (taskDTO.getDueDate() != null) {
            task.setDueDate(taskDTO.getDueDate());
        }
        if (taskDTO.getAssignees() != null && !taskDTO.getAssignees().isEmpty()) {
            task.setAssignees(taskDTO.getAssignees());
        }
        taskRepository.save(task);
    }
}
