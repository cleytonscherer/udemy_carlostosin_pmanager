package br.com.scherer.pmanager.infrastructure.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SaveTaskDataDTO {

    @NotNull(message = "Titulo deve ser informado")
    @Size(min = 1, max = 80, message = "Tamanho mínimo 1, máximo 80")
    private final String  title;

    @NotNull(message = "Descrição deve ser informada")
    @Size(min = 1, max = 150, message = "Tamanho mínimo 1, máximo 150")
    private final String  description;

    @NotNull(message = "Número de Dias deve ser informado")
    @Positive(message = "Número de Dias deve ser um valor positivo")
    private final Integer numberOfDays;

    private final String status;

}
