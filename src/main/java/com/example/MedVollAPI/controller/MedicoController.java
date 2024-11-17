package com.example.MedVollAPI.controller;

import com.example.MedVollAPI.dto.*;
import com.example.MedVollAPI.model.Medico;
import com.example.MedVollAPI.repository.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid MedicoDTO medicoDTO, UriComponentsBuilder uriComponentsBuilder) {
        var medico = new Medico(medicoDTO);
        medicoRepository.save(medico);

        var uri = uriComponentsBuilder.path("/medicos/{idMedico}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicoDetalhesDTO(medico));
    }

    @GetMapping
    //@PageableDefault, caso o cliente não mande na requisição os parametros de paginação, vai assumir como padrão 10 registros ordenados pelo nome
    public ResponseEntity<Page<MedicoListagemDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(medicoRepository.findAllByAtivoTrue(paginacao).map(MedicoListagemDTO::new));
    }

    @GetMapping("/{idMedico}")
    public ResponseEntity<MedicoDetalhesDTO> detalhar(@PathVariable Long idMedico) {
        var medico = medicoRepository.findById(idMedico);

        return ResponseEntity.ok(new MedicoDetalhesDTO(medico.get()));
    }

    @PutMapping("/{idMedico}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long idMedico, @RequestBody @Valid MedicoAtualizarDTO dados) {
        var medico = medicoRepository.getReferenceById(idMedico);
        medico.atualizarDados(dados);
        return ResponseEntity.ok(new MedicoDetalhesDTO(medico));
    }

    @DeleteMapping("/{idMedico}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long idMedico) {
        //medicoRepository.deleteById(idMedico);
        var medico = medicoRepository.getReferenceById(idMedico);
        medico.desativar();

        return ResponseEntity.noContent().build();
    }
}
