package br.com.scherer.pmanager.domain.applicationservice;

import br.com.scherer.pmanager.domain.entity.Member;
import br.com.scherer.pmanager.domain.entity.Project;
import br.com.scherer.pmanager.domain.exception.DuplicatedProjectException;
import br.com.scherer.pmanager.domain.exception.InvalidProjectStatusException;
import br.com.scherer.pmanager.domain.exception.ProjectNotFoundException;
import br.com.scherer.pmanager.domain.model.ProjectStatus;
import br.com.scherer.pmanager.domain.repository.ProjectRepository;
import br.com.scherer.pmanager.infrastructure.dto.SaveProjectDataDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

//    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);

    private final ProjectRepository projectRepository;
    private final MemberService memberService;

    @Transactional
    public Project createProject(SaveProjectDataDTO saveProjectData) {

        if (existsProjectWithName(saveProjectData.getName(), null)) {
            log.info("Projecto duplicado: " + saveProjectData.getName());

            throw new DuplicatedProjectException(saveProjectData.getName());
        }

        Project project = Project
                .builder()
                .name(saveProjectData.getName())
                .description(saveProjectData.getDescription())
                .initialDate(saveProjectData.getInitialDate())
                .finalDate(saveProjectData.getFinalDate())
                .status(ProjectStatus.PENDING)
                .build();

        projectRepository.save(project);

        addMembersToProject(saveProjectData.getMemberIds(), project);

        log.info("Project created: {} ", project);

        return project;
    }

    public Project loadProject(String projectId) {
        return projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
    }

    @Transactional
    public void deleteProject(String projectId) {
        Project project = loadProject(projectId);
        projectRepository.delete(project);
    }

    @Transactional
    public Project updateProject(String projectId, SaveProjectDataDTO saveProjectData) {

        if (existsProjectWithName(saveProjectData.getName(), projectId)) {
            throw new DuplicatedProjectException(saveProjectData.getName());
        }

        Project project = loadProject(projectId);

        project.setName(saveProjectData.getName());
        project.setDescription(saveProjectData.getDescription());
        project.setInitialDate(saveProjectData.getInitialDate());
        project.setFinalDate(saveProjectData.getFinalDate());
        project.setStatus(converttoProjectStatus(saveProjectData.getStatus()));

        addMembersToProject(saveProjectData.getMemberIds(), project);

        return project;
    }

    private ProjectStatus converttoProjectStatus(String statusStr) {
        try {
            return ProjectStatus.valueOf(statusStr);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InvalidProjectStatusException(statusStr);
        }
    }

    private boolean existsProjectWithName(String name, String idToExclude) {
        return projectRepository
                .findByName(name)
                .filter(p -> !Objects.equals(p.getId(), idToExclude))
                .isPresent();

    }

    private void addMembersToProject(Set<String> memberIds, Project project) {
        List<Member> members = Optional.ofNullable(memberIds).orElse(Set.of())
                .stream()
                .map(memberService::loadMemberById)
                .collect(Collectors.toList());

        project.setMembers(members);
    }
}
