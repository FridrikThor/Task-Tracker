package com.github.FridrikThor.task_tracker.controller;

import com.github.FridrikThor.task_tracker.dto.TaskDTO;
import com.github.FridrikThor.task_tracker.enums.TaskStatus;
import com.github.FridrikThor.task_tracker.model.Project;
import com.github.FridrikThor.task_tracker.model.Task;
import com.github.FridrikThor.task_tracker.service.ProjectService;
import com.github.FridrikThor.task_tracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/task")
public class TaskController {

    private final TaskService taskService;
    private final ProjectService projectService;

    @Autowired
    public TaskController(TaskService taskService, ProjectService projectService) {
        this.taskService = taskService;
        this.projectService = projectService;
    }


    @GetMapping
    public List<Task> getTasks(){
        return taskService.getTasks();
    }

    @PostMapping
    public ResponseEntity<Task> registerNewTask(@RequestBody TaskDTO taskDTO){
        Project project = projectService.getProjectById(taskDTO.getProjectId());
        Task newTask = new Task(taskDTO);
        newTask.setProject(project);

        taskService.addNewTask(newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @DeleteMapping(path = "{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId){
        taskService.deleteTask(taskId);
    }

    @PutMapping(path = "/{taskId}")
    public void updateTask(@PathVariable("taskId") Long taskId, @RequestBody TaskDTO taskDTO) {
        taskService.updateTask(taskId, taskDTO);
    }
}
