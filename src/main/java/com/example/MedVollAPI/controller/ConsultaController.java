package com.example.MedVollAPI.controller;

import com.example.MedVollAPI.dto.ConsultaCadastroDTO;
import com.example.MedVollAPI.dto.ConsultaDetalhesDTO;
import com.example.MedVollAPI.model.Consulta;
import com.example.MedVollAPI.service.ValidacoesConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consulta")
public class ConsultaController {
    @Autowired
    private ValidacoesConsultaService validacoesConsultaService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid ConsultaCadastroDTO dados) {
        System.out.println(dados);
        Consulta consulta =  validacoesConsultaService.validarDados(dados);
        return ResponseEntity.ok(new ConsultaDetalhesDTO(consulta.getId(), consulta.getMedico().getId(), consulta.getData()));
    }
}
