package br.com.scherer.pmanager.domain.exception;

import br.com.scherer.pmanager.infrastructure.exception.RequestException;

public class DuplcatedProjectException extends RequestException {

    public DuplcatedProjectException(String name) {

        super("ProjectDuplicate", "A Project with the name alredy exists: " + name);
    }
}
