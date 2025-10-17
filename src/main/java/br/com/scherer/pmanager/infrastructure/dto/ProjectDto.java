package br.com.scherer.pmanager.infrastructure.dto;

import br.com.scherer.pmanager.domain.entity.Project;
import br.com.scherer.pmanager.domain.model.ProjectStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectDto {
    private final String      id;
    private final String      name;
    private final String      description;
    private final LocalDate   initialDate;
    private final LocalDate   finalDate;
    private final ProjectStatus status;

    public static ProjectDto create(Project project) {
        return new ProjectDto(
          project.getId(),
          project.getName(),
          project.getDescription(),
          project.getInitialDate(),
          project.getFinalDate(),
          project.getStatus()
        );
    }
}
