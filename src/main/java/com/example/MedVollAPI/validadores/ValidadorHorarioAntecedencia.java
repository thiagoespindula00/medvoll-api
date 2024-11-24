package com.example.MedVollAPI.validadores;

import com.example.MedVollAPI.dto.ConsultaCadastroDTO;
import com.example.MedVollAPI.trata_erros.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorConsultas{
    @Override
    public void validar(ConsultaCadastroDTO dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
