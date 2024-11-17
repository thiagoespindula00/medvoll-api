package com.example.MedVollAPI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        @Pattern(regexp = "\\d{8}") // 8 dígitos
        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,
        String numero,
        String complemento) {
}
