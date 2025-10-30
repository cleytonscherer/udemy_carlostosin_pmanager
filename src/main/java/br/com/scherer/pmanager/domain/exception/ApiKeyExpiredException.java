package br.com.scherer.pmanager.domain.exception;

import br.com.scherer.pmanager.infrastructure.exception.RequestException;

public class ApiKeyExpiredException extends RequestException {

    public ApiKeyExpiredException(String apikeyId) {
        super("ApiKeyExpired", "The Api Key expired: " + apikeyId);
    }
}
