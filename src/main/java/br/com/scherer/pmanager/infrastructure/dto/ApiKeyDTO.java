package br.com.scherer.pmanager.infrastructure.dto;

import br.com.scherer.pmanager.domain.document.ApiKey;
import lombok.Data;

import java.time.Instant;

@Data
public class ApiKeyDTO {

    private final String  id;

    private final String  value;

    private final Instant expireWhen;

    public static ApiKeyDTO create(ApiKey apiKey) {
        return new ApiKeyDTO(
            apiKey.getId(),
            apiKey.getValue(),
            apiKey.getExpiresWhen()
        );
    }
}
