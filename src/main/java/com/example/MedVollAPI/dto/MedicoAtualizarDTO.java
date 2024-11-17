package com.example.MedVollAPI.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record MedicoAtualizarDTO(
        @NotNull
        String nome,
        @NotNull
        String telefone,

        @Valid
        EnderecoDTO endereco
) {
}
