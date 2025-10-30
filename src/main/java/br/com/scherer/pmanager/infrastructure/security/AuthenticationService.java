package br.com.scherer.pmanager.infrastructure.security;

import br.com.scherer.pmanager.domain.applicationservice.ApíKeyService;
import br.com.scherer.pmanager.domain.exception.ApiKeyExpiredException;
import br.com.scherer.pmanager.domain.exception.ApiKeyNotFoundException;
import br.com.scherer.pmanager.infrastructure.config.AppConfigProperties;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ApíKeyService apiKeyService;

    private final AppConfigProperties props;

    private final static String AUTH_TOKEN_HEADER_NAME = "x-api-key";

    public Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);

        if (!Objects.equals(apiKey, props.getSecurity().getMasterApiKey())) {
            //throw new BadCredentialsException("Invalid API Key: " + apiKey);
            try {
                apiKeyService.validateApiKey(apiKey);
            } catch (ApiKeyNotFoundException | ApiKeyExpiredException e) {
                throw new BadCredentialsException("API Key is not valid: " + apiKey, e);
            }
        }

        return new ApiKeyAuthenticationToken(apiKey);
    }
}
