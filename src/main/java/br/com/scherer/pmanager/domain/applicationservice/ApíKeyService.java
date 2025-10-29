package br.com.scherer.pmanager.domain.applicationservice;

import br.com.scherer.pmanager.domain.document.ApiKey;
import br.com.scherer.pmanager.domain.exception.ApiKeyNotFoundException;
import br.com.scherer.pmanager.domain.repository.ApiKeyRepository;
import br.com.scherer.pmanager.infrastructure.config.AppConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApíKeyService {

    private final ApiKeyRepository apiKeyRepository;
    private final AppConfigProperties props;

    public ApiKey createApiKey() {
        ApiKey apiKey = ApiKey
                .builder()
                .value(UUID.randomUUID().toString())
                .expiresWhen(
                        OffsetDateTime.now()
                                .plusDays(props.getSecurity().getExpirationDays())
                                .toInstant()
                )
                .build();

        apiKeyRepository.save(apiKey);

        return apiKey;
    }

    public void revokeApiKey(String id) {
        ApiKey apiKey = loadApiKeyById(id);
        apiKey.setExpiresWhen(Instant.EPOCH);
        apiKeyRepository.save(apiKey);
    }

    private ApiKey loadApiKeyById(String id) {
        return apiKeyRepository
                .findById(id)
                .orElseThrow(() -> new ApiKeyNotFoundException(id));
    }
}
