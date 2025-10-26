package br.com.scherer.pmanager.domain.repository;

import br.com.scherer.pmanager.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

}
