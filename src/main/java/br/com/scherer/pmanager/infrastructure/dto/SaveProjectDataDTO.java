package br.com.scherer.pmanager.infrastructure.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SaveProjectDataDTO {
    @NotNull(message = "Nome do Projeto deve ser informado")
    @Size(min = 1, max = 80, message = "Nome do Projeto deve ter tamanho entre 1 e 80 caracteres")
    private final String        name;

    @NotNull(message = "Descrição do Projeto deve ser informada")
    @Size(min = 1, max = 150, message = "Descrição do Projeto deve ter tamanho entre 1 e 150 caracteres")
    private final String        description;

    @NotNull(message = "Data inicial deve ser informada")
    private final LocalDate     initialDate;

    @NotNull(message = "Data final deve ser informada")
    private final LocalDate     finalDate;

    private final String        status;

    @AssertTrue(message = "Datas final e inicial inconsistentes")
    @SuppressWarnings("unused")
    private boolean isInitialDateBeforeFinalDate() {
        return initialDate.isBefore(finalDate);
    }
}
