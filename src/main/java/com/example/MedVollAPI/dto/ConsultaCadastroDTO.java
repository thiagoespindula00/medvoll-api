package com.example.MedVollAPI.dto;

import com.example.MedVollAPI.model.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaCadastroDTO(
        @NotNull
        Long idMedico,
        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade
) {
}
