package com.example.MedVollAPI.service;

import com.example.MedVollAPI.dto.ConsultaCadastroDTO;
import com.example.MedVollAPI.model.Consulta;
import com.example.MedVollAPI.model.Medico;
import com.example.MedVollAPI.repository.ConsultaRepository;
import com.example.MedVollAPI.repository.MedicoRepository;
import com.example.MedVollAPI.trata_erros.ValidacaoException;
import com.example.MedVollAPI.validadores.ValidadorConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidacoesConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private List<ValidadorConsultas> validadores;
    public Consulta validarDados(ConsultaCadastroDTO dados) {
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico é inválido");
        }

        validadores.forEach(v-> v.validar(dados));

        var medico = escolheMedicoAleatorio(dados);
        var consulta = new Consulta(null, medico, dados.data());
        consultaRepository.save(consulta);

        return consulta;
    }

    private Medico escolheMedicoAleatorio(ConsultaCadastroDTO dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        return medicoRepository.escolheMedicoAleatorioNaData(dados.especialidade(), dados.data());
    }
}
