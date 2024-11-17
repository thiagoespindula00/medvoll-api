package com.example.MedVollAPI.controller;

import com.example.MedVollAPI.dto.MedicoAtualizarDTO;
import com.example.MedVollAPI.dto.MedicoDTO;
import com.example.MedVollAPI.dto.MedicoListagemDTO;
import com.example.MedVollAPI.model.Medico;
import com.example.MedVollAPI.repository.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid MedicoDTO medico) {
        medicoRepository.save(new Medico(medico));
    }

    @GetMapping
    //@PageableDefault, caso o cliente não mande na requisição os parametros de paginação, vai assumir como padrão 10 registros ordenados pelo nome
    public Page<MedicoListagemDTO> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return medicoRepository.findAllByAtivoTrue(paginacao).map(MedicoListagemDTO::new);
    }

    @PutMapping("/{idMedico}")
    @Transactional
    public void atualizar(@PathVariable Long idMedico, @RequestBody @Valid MedicoAtualizarDTO dados) {
        var medico = medicoRepository.getReferenceById(idMedico);
        medico.atualizarDados(dados);
    }

    @DeleteMapping("/{idMedico}")
    @Transactional
    public void deletar(@PathVariable Long idMedico) {
        //medicoRepository.deleteById(idMedico);
        var medico = medicoRepository.getReferenceById(idMedico);
        medico.desativar();
    }
}
