package com.example.MedVollAPI.dto;

import com.example.MedVollAPI.model.Especialidade;
import com.example.MedVollAPI.model.Medico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoDetalhesDTO(
        Long id,
        String nome,
        EnderecoDTO endereco,
        String email,
        String crm,
        Especialidade especialidade,
        String telefone
) {
    public MedicoDetalhesDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), new EnderecoDTO(medico.getEndereco()), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getTelefone());
    }
}
