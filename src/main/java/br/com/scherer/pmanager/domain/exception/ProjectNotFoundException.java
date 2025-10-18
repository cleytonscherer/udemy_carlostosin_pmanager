package br.com.scherer.pmanager.domain.exception;

import br.com.scherer.pmanager.infrastructure.exception.RequestException;

public class ProjectNotFoundException extends RequestException {

    public ProjectNotFoundException(String projectId) {
        super("ProjectNotFound", "Project not found: " + projectId);
    }
}
