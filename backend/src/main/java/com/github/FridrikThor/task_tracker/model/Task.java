package com.github.FridrikThor.task_tracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.FridrikThor.task_tracker.dto.TaskDTO;
import com.github.FridrikThor.task_tracker.enums.TaskPriority;
import com.github.FridrikThor.task_tracker.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table

public class Task {
    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    private Long id;

    @NotNull(message = "Title is mandatory")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String title;


    @Size(max = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;


    private LocalDate dueDate;

    private LocalDate createdAt;

    private String owner;

    @ElementCollection
    private List<String> assignees = new ArrayList<>();

    /*@Transient
    private boolean isOverDue;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonBackReference
    private Project project;

    public Task() {
    }

    public Task(Long id,
                String title,
                String description,
                TaskStatus status,
                TaskPriority priority,
                LocalDate dueDate,
                String owner,
                List<String> assignees) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
        this.owner = owner;
        this.assignees = assignees;
    }

    public Task(String title,
                String description,
                TaskStatus status,
                TaskPriority priority,
                LocalDate dueDate,
                String owner,
                List<String> assignees) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
        this.owner = owner;
        this.assignees = assignees;
    }

    public Task(TaskDTO taskDTO){
        this.title = taskDTO.getTitle();
        this.description = taskDTO.getDescription();
        this.status = taskDTO.getStatus();
        this.priority = taskDTO.getPriority();
        this.dueDate = taskDTO.getDueDate();
        this.owner = taskDTO.getOwner();
        this.assignees = taskDTO.getAssignees();
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<String> assignees) {
        this.assignees = assignees;
    }

    /*public boolean isOverdue() {
        return dueDate != null
                && dueDate.isBefore(LocalDate.now())
                && (status != TaskStatus.COMPLETE);
    }*/

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", priority='" + priority + '\'' +
                ", dueDate=" + dueDate +
                ", createdAt=" + createdAt +
                /*", overdue=" + isOverDue +*/
                '}';
    }
}
