package br.com.scherer.pmanager.domain.exception;

import br.com.scherer.pmanager.infrastructure.exception.RequestException;

public class DuplicateMemberException extends RequestException {

    public DuplicateMemberException(String email) {
        super("MemberDuplicate", "A Member with the email already exists: " + email);
    }
}
