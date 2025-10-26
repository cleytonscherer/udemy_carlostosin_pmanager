package br.com.scherer.pmanager.infrastructure.dto;

import br.com.scherer.pmanager.domain.entity.Task;
import br.com.scherer.pmanager.domain.model.TaskStatus;
import lombok.Data;

@Data
public class TaskDTO {
    private final String  id;
    private final String  title;
    private final String  description;
    private final Integer numberOfDays;
    private final TaskStatus status;

    public static TaskDTO create(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getNumberOfDays(),
                task.getStatus()
        );
    }

}
