package com.example.geradorus.controller;

import com.example.geradorus.codes.StatusCodes;
import com.example.geradorus.dto.TipoHUInputDTO;
import com.example.geradorus.model.TipoTarefa;
import com.example.geradorus.model.TipoHU;
import com.example.geradorus.repository.TipoTarefaRepository;
import com.example.geradorus.repository.TipoHURepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipoUS")
public class TipoHUController {

    @Autowired
    TipoHURepository tipoHURepository;

    @Autowired
    TipoTarefaRepository tipoTarefaRepository;

    @PostMapping
    public ResponseEntity<TipoHU> criarTipoHU(@RequestBody @Valid TipoHUInputDTO tipoHUInputDTO) {
        TipoHU tipoHU = new TipoHU();
        TipoTarefa tipoTarefa = tipoTarefaRepository.findById(tipoHUInputDTO.tipoTarefaId()).get();
        tipoHU.setTipoTarefa(tipoTarefa);
        BeanUtils.copyProperties(tipoHUInputDTO, tipoHU);

        return ResponseEntity.status(HttpStatus.CREATED).body(tipoHURepository.save(tipoHU));
    }

    @GetMapping
    public ResponseEntity<List<TipoHU>> listarTipoHU() {
        return ResponseEntity.status(HttpStatus.OK).body(tipoHURepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> tipoHUPeloId(@PathVariable(value = "id") long id) {
        Optional<TipoHU> tipoHUOptional = tipoHURepository.findById(id);
        return tipoHUOptional.<ResponseEntity<Object>>map(tipoUS ->
                ResponseEntity.status(HttpStatus.OK).body(tipoUS)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.US_TYPE_NOT_FOUND.getCode()));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarTipoHU(@PathVariable(value = "id") long id, @RequestBody @Valid TipoHUInputDTO tipoHUInputDTO) {
        Optional<TipoHU> tipoHUOptional = tipoHURepository.findById(id);
        if (tipoHUOptional.isEmpty()) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.US_TYPE_NOT_FOUND.getCode());}

        var tiposHU = tipoHUOptional.get();
        BeanUtils.copyProperties(tipoHUInputDTO, tiposHU);

        return ResponseEntity.status(HttpStatus.OK).body(tipoHURepository.save(tiposHU));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarTipoHU(@PathVariable(value = "id") long id) {
        Optional<TipoHU> tipoHUOptional = tipoHURepository.findById(id);
        if (tipoHUOptional.isEmpty()) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.US_TYPE_NOT_FOUND.getCode());}

        tipoHURepository.delete(tipoHUOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(StatusCodes.US_TYPE_REMOVED.getCode());
    }

}
