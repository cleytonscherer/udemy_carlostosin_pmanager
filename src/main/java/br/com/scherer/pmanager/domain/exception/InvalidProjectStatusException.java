package br.com.scherer.pmanager.domain.exception;

import br.com.scherer.pmanager.infrastructure.exception.RequestException;

public class InvalidProjectStatusException extends RequestException {

    public InvalidProjectStatusException(String statusStr) {
        super("InvalidProjectStatus", "Invalid Project Status: " + statusStr);
    }
}
