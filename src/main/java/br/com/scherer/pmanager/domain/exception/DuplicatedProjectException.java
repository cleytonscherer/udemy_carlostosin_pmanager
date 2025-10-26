package br.com.scherer.pmanager.domain.exception;

import br.com.scherer.pmanager.infrastructure.exception.RequestException;

public class DuplicatedProjectException extends RequestException {

    public DuplicatedProjectException(String name) {

        super("ProjectDuplicate", "A Project with the name alredy exists: " + name);
    }
}
