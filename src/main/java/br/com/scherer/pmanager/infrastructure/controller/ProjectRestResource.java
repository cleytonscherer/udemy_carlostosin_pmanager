package br.com.scherer.pmanager.infrastructure.controller;

import br.com.scherer.pmanager.domain.applicationservice.ProjectService;
import br.com.scherer.pmanager.domain.entity.Project;
import br.com.scherer.pmanager.infrastructure.dto.SaveProjectDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectRestResource {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> createProect(@RequestBody SaveProjectDataDto saveProjectData) {
        Project project = projectService.createProject(saveProjectData);
        return ResponseEntity
                .created(URI.create("/projects/" + project.getId()))
                .body(project);
    }

}
