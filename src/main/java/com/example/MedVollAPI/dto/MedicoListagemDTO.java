package com.example.MedVollAPI.dto;

import com.example.MedVollAPI.model.Especialidade;
import com.example.MedVollAPI.model.Medico;

public record MedicoListagemDTO(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {

    public MedicoListagemDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
