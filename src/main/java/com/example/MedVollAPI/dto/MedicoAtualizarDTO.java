package com.example.MedVollAPI.dto;

import com.example.MedVollAPI.model.Medico;
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
        public MedicoAtualizarDTO(Medico medico) {
                this(medico.getNome(), medico.getTelefone(), new EnderecoDTO(medico.getEndereco()));
        }
}
