package br.com.scherer.pmanager.domain.exception;

import br.com.scherer.pmanager.infrastructure.exception.RequestException;

public class TaskNotFoundException extends RequestException {

    public TaskNotFoundException(String taskId) {
        super("TaskNotFound", "Task not found: " + taskId);
    }
}
