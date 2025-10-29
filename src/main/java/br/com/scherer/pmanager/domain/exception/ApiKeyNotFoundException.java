package br.com.scherer.pmanager.domain.exception;

import br.com.scherer.pmanager.infrastructure.exception.RequestException;

public class ApiKeyNotFoundException extends RequestException {

    public ApiKeyNotFoundException(String apikeyId) {
        super("ApiKeyNotFound", "The Api Key was not found: " + apikeyId);
    }
}
