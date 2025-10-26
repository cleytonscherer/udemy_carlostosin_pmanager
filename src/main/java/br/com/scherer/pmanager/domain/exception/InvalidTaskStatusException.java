package br.com.scherer.pmanager.domain.exception;

import br.com.scherer.pmanager.infrastructure.exception.RequestException;

public class InvalidTaskStatusException extends RequestException {

    public InvalidTaskStatusException(String statusStr) {
        super("InvalidTaskStatus", "Invalid Task Status: " + statusStr);
    }
}
