package br.com.scherer.pmanager.domain.entity;

import br.com.scherer.pmanager.domain.model.ProjectStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "project")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, length = 36)
    private String      id;

    @Column(name = "name", nullable = false, length = 80)
    private String      name;

    @Column(name = "description", nullable = false, length = 150)
    private String      description;

    @Column(name = "initial_date", nullable = false)
    private LocalDate   initialDate;

    @Column(name = "final_date", nullable = false)
    private LocalDate   finalDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectStatus   status;


}
