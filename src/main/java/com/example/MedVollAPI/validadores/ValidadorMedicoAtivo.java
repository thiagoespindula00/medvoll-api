package com.example.MedVollAPI.validadores;

import com.example.MedVollAPI.dto.ConsultaCadastroDTO;
import com.example.MedVollAPI.repository.MedicoRepository;
import com.example.MedVollAPI.trata_erros.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorConsultas{
    @Autowired
    MedicoRepository medicoRepository;
    @Override
    public void validar(ConsultaCadastroDTO dados) {
        var medico = medicoRepository.findAtivoById(dados.idMedico());
        if (medico == null) {
            throw new ValidacaoException("Médico informado não está ativo");
        }
    }
}
