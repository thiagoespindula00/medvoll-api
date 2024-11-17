package com.example.MedVollAPI.model;

import com.example.MedVollAPI.dto.MedicoAtualizarDTO;
import com.example.MedVollAPI.dto.MedicoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String crm;
    private String telefone;

    @Column(nullable = false)
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(MedicoDTO medicoDTO) {
        this.nome = medicoDTO.nome();
        this.email = medicoDTO.email();
        this.crm = medicoDTO.crm();
        this.especialidade = medicoDTO.especialidade();
        this.endereco = new Endereco(medicoDTO.endereco());
        this.telefone = medicoDTO.telefone();
    }

    public void atualizarDados(MedicoAtualizarDTO dados) {
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.endereco.atualizarDados(dados.endereco());
    }

    public void desativar() {
        this.ativo = false;
    }
}
