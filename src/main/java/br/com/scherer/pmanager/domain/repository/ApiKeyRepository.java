package br.com.scherer.pmanager.domain.repository;

import br.com.scherer.pmanager.domain.document.ApiKey;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiKeyRepository extends MongoRepository<ApiKey, String> {

}
