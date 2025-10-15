package br.com.scherer.pmanager.infrastructure.dto;

import br.com.scherer.pmanager.domain.model.ProjectStatus;

import java.time.LocalDate;

public class ProjectDto {
    private String      id;
    private String      name;
    private String      description;
    private LocalDate   initialDate;
    private LocalDate   finalDate;
    private ProjectStatus status;
}
