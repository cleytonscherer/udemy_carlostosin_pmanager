package br.com.scherer.pmanager.infrastructure.controller;

import br.com.scherer.pmanager.domain.applicationservice.TaskService;
import br.com.scherer.pmanager.domain.entity.Task;
import br.com.scherer.pmanager.infrastructure.dto.SaveTaskDataDTO;
import br.com.scherer.pmanager.infrastructure.dto.TaskDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static br.com.scherer.pmanager.infrastructure.controller.RestConstants.PATH_TASKS;

@RestController
@RequestMapping(PATH_TASKS)
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class TaskRestResource {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody @Valid SaveTaskDataDTO saveTaskData) {
        Task task = taskService.createTask(saveTaskData);
        return ResponseEntity
                .created(URI.create(PATH_TASKS + "/" + task.getId()))
                .body(TaskDTO.create(task));
    }


    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> loadTask(@PathVariable("id") String taskId) {
        Task task = taskService.loadTask(taskId);
        return ResponseEntity.ok(TaskDTO.create(task));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable("id") String taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(
            @PathVariable("id") String taskId,
            @RequestBody @Valid SaveTaskDataDTO saveTaskData
    ) {
        Task task = taskService.updateTask(taskId, saveTaskData);
        return ResponseEntity.ok(TaskDTO.create(task));
    }

}
