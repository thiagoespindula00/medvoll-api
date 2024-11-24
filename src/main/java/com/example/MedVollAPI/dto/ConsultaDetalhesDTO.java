package com.example.MedVollAPI.dto;

import java.time.LocalDateTime;

public record ConsultaDetalhesDTO(
        Long id,
        Long idMedico,
        LocalDateTime data
) {
}
