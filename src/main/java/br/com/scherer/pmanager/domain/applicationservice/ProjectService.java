package br.com.scherer.pmanager.domain.applicationservice;

import br.com.scherer.pmanager.domain.entity.Project;
import br.com.scherer.pmanager.domain.model.ProjectStatus;
import br.com.scherer.pmanager.domain.repository.ProjectRepository;
import br.com.scherer.pmanager.infrastructure.dto.SaveProjectDataDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

//    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);

    private final ProjectRepository projectRepository;

    @Transactional
    public Project createProject(SaveProjectDataDto saveProjectData) {

        Project project = Project
                .builder()
                .name(saveProjectData.getName())
                .description(saveProjectData.getDescription())
                .initialDate(saveProjectData.getInitialDate())
                .finalDate(saveProjectData.getFinalDate())
                .status(ProjectStatus.PENDING)
                .build();

        projectRepository.save(project);

        log.info("Project created: {} ", project);

        return project;
    }
}
