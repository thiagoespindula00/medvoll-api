package com.example.MedVollAPI.dto;

import com.example.MedVollAPI.model.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoDTO(@NotBlank
                        String nome,
                        @NotNull
                        @Valid
                        EnderecoDTO endereco,
                        @NotBlank
                        @Email
                        String email,
                        @NotBlank
                        @Pattern(regexp = "\\d{4,6}") // Digitos de 4 a 7 dígitos
                        String crm,
                        @NotNull
                        Especialidade especialidade,
                        @NotBlank
                        String telefone) {
}
