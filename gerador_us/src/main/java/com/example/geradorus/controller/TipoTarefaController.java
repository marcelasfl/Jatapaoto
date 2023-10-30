package com.example.geradorus.controller;

import com.example.geradorus.codes.StatusCodes;
import com.example.geradorus.dto.TipoTarefaDTO;
import com.example.geradorus.model.TipoTarefa;
import com.example.geradorus.repository.TipoTarefaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipotarefa")
public class TipoTarefaController {

    @Autowired
    TipoTarefaRepository tipoTarefaRepository;

    @PostMapping
    public ResponseEntity<Object> createTipoTarefa(@RequestBody @Valid TipoTarefaDTO tipoTarefaDTO){
        var tipoTarefa = new TipoTarefa();
        BeanUtils.copyProperties(tipoTarefaDTO, tipoTarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoTarefaRepository.save(tipoTarefa));
    }

    @GetMapping
    public ResponseEntity<List<TipoTarefa>> getAllTipoTarefa(){
        return ResponseEntity.status(HttpStatus.OK).body(tipoTarefaRepository.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getTipoTarefaById(@PathVariable int id){
        Optional<TipoTarefa> tipoTarefaOptional = tipoTarefaRepository.findById((long) id);
        return tipoTarefaOptional.<ResponseEntity<Object>>map(tipoTarefa ->
                ResponseEntity.status(HttpStatus.OK).body(tipoTarefa)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.PROJECT_NOT_FOUND.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTipoTarefa(@PathVariable int id){
        Optional<TipoTarefa> tipoTarefaOptional = tipoTarefaRepository.findById((long) id);
        if(tipoTarefaOptional.isEmpty()){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.PROJECT_NOT_FOUND);}
        tipoTarefaRepository.deleteById((long) id);
        return ResponseEntity.status(HttpStatus.OK).body(StatusCodes.PROJECT_REMOVED.getCode());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTipoTarefa(@PathVariable int id, @RequestBody @Valid TipoTarefaDTO tipoTarefaDTO){
        Optional<TipoTarefa> tipoTarefaOptional = tipoTarefaRepository.findById((long) id);
        if(tipoTarefaOptional.isEmpty()){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.PROJECT_NOT_FOUND);}

        var tipoTarefa = new TipoTarefa();
        BeanUtils.copyProperties(tipoTarefaDTO, tipoTarefa);
        tipoTarefa.setId(tipoTarefaOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(tipoTarefaRepository.save(tipoTarefa));

    }

}
