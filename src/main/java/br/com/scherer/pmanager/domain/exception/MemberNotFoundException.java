package br.com.scherer.pmanager.domain.exception;

import br.com.scherer.pmanager.infrastructure.exception.RequestException;

public class MemberNotFoundException extends RequestException {

    public MemberNotFoundException(String memberId) {
        super("MemberNotFound", "Member not found: " + memberId);
    }
}
