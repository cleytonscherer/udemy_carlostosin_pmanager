package br.com.scherer.pmanager.infrastructure.controller;

import br.com.scherer.pmanager.domain.applicationservice.ProjectService;
import br.com.scherer.pmanager.domain.entity.Project;
import br.com.scherer.pmanager.infrastructure.dto.ProjectDto;
import br.com.scherer.pmanager.infrastructure.dto.SaveProjectDataDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static br.com.scherer.pmanager.infrastructure.controller.RestConstants.PATH_PROJECTS;

@RestController
@RequestMapping(PATH_PROJECTS)
@RequiredArgsConstructor
public class ProjectRestResource {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectDto> createProect(@RequestBody @Valid SaveProjectDataDto saveProjectData) {
        Project project = projectService.createProject(saveProjectData);
        return ResponseEntity
                .created(URI.create(PATH_PROJECTS + "/" + project.getId()))
                .body(ProjectDto.create(project));
    }

}
