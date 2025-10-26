package br.com.scherer.pmanager.domain.applicationservice;

import br.com.scherer.pmanager.domain.entity.Task;
import br.com.scherer.pmanager.domain.exception.InvalidTaskStatusException;
import br.com.scherer.pmanager.domain.exception.MemberNotFoundException;
import br.com.scherer.pmanager.domain.exception.TaskNotFoundException;
import br.com.scherer.pmanager.domain.model.TaskStatus;
import br.com.scherer.pmanager.domain.repository.TaskRepository;
import br.com.scherer.pmanager.infrastructure.dto.SaveTaskDataDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional
    public Task createTask(SaveTaskDataDTO saveTaskData) {
        Task task = Task
                .builder()
                .title(saveTaskData.getTitle())
                .description(saveTaskData.getDescription())
                .numberOfDays(saveTaskData.getNumberOfDays())
                .status(TaskStatus.PENDING)
                .build();

        taskRepository.save(task);

        return task;
    }

    public Task loadTask(String taskId) {
        return taskRepository
                .findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    @Transactional
    public void deleteTask(String taskId) {
        Task task = loadTask(taskId);
        taskRepository.delete(task);
    }

    @Transactional
    public Task updateTask(String taskId, SaveTaskDataDTO saveTaskData) {
        Task task = loadTask(taskId);

        task.setTitle(saveTaskData.getTitle());
        task.setDescription(saveTaskData.getDescription());
        task.setNumberOfDays(saveTaskData.getNumberOfDays());
        task.setStatus(convertToTaskStatus(saveTaskData.getStatus()));

        return task;
    }

    private TaskStatus convertToTaskStatus(String statusStr) {
        try {
            return TaskStatus.valueOf(statusStr);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InvalidTaskStatusException(statusStr);
        }
    }
}
