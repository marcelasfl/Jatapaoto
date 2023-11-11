package com.example.geradorus.controller;

import com.example.geradorus.codes.StatusCodes;
import com.example.geradorus.dto.TipoEpicoInputDTO;
import com.example.geradorus.model.TipoEpico;
import com.example.geradorus.repository.TipoEpicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipoEpico")
public class TipoEpicoController {

    @Autowired
    private TipoEpicoRepository tipoEpicoRepository;

    @PostMapping
    public ResponseEntity<Object> criarTipoEpico(@RequestBody @Valid TipoEpicoInputDTO tipoEpicoInputDTO) {
        var tipoEpico = new TipoEpico();
        BeanUtils.copyProperties(tipoEpicoInputDTO, tipoEpico);

        return ResponseEntity.status(HttpStatus.CREATED).body(tipoEpicoRepository.save(tipoEpico));
    }

    @GetMapping
    public ResponseEntity<List<TipoEpico>> listarTiposEpico() {
        return ResponseEntity.status(HttpStatus.OK).body(tipoEpicoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> tipoEpicoPeloId(@PathVariable int id) {
        Optional<TipoEpico> tipoEpicoOptional = tipoEpicoRepository.findById((long) id);

        return tipoEpicoOptional.<ResponseEntity<Object>>map(tipoEpico ->
                ResponseEntity.status(HttpStatus.OK).body(tipoEpico)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.EPIC_TYPE_NOT_FOUND.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarTipoEpico(@PathVariable int id) {
        Optional<TipoEpico> tipoEpicoOptional = tipoEpicoRepository.findById((long) id);
        if (tipoEpicoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.EPIC_TYPE_NOT_FOUND.getCode());
        }
        tipoEpicoRepository.deleteById((long) id);

        return ResponseEntity.status(HttpStatus.OK).body(StatusCodes.EPIC_TYPE_REMOVED.getCode());

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarTipoEpico(@PathVariable long id, @RequestBody @Valid TipoEpicoInputDTO tipoEpicoInputDTO) {
        Optional<TipoEpico> tipoEpicoOptional = tipoEpicoRepository.findById((long) id);
        if (tipoEpicoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.EPIC_TYPE_NOT_FOUND.getCode());
        }

        var tipoEpico = new TipoEpico();
        BeanUtils.copyProperties(tipoEpicoInputDTO, tipoEpico);
        tipoEpico.setId(tipoEpicoOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(tipoEpicoRepository.save(tipoEpico));
    }

}