package br.com.scherer.pmanager.infrastructure.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SaveMemberDataDTO {

    @NotNull(message = "Nome deve ser informado")
    @Size(min = 1, max = 80, message = "Nome deve ter entre 1 e 80 caracteres")
    private final String  name;

    @NotNull(message = "Endereço de e-mail deve ser informado")
    @Email(message = "Endereço de e-mail inválido")
    private final String  email;
}
