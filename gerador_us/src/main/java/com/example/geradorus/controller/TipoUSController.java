package com.example.geradorus.controller;

import com.example.geradorus.codes.StatusCodes;
import com.example.geradorus.dto.TipoUSInputDTO;
import com.example.geradorus.model.TipoTarefa;
import com.example.geradorus.model.TipoUS;
import com.example.geradorus.repository.TipoTarefaRepository;
import com.example.geradorus.repository.TipoUSRepository;
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
public class TipoUSController {

    @Autowired
    TipoUSRepository tipoUSRepository;

    @Autowired
    TipoTarefaRepository tipoTarefaRepository;

    @PostMapping
    public ResponseEntity<TipoUS> create(@RequestBody @Valid TipoUSInputDTO tipoUSInputDTO) {
        TipoUS tipoUS = new TipoUS();
        TipoTarefa tipoTarefa = tipoTarefaRepository.findById(tipoUSInputDTO.tipoTarefaId()).get();
        tipoUS.setTipoTarefa(tipoTarefa);
        BeanUtils.copyProperties(tipoUSInputDTO, tipoUS);

        return ResponseEntity.status(HttpStatus.CREATED).body(tipoUSRepository.save(tipoUS));
    }

    @GetMapping
    public ResponseEntity<List<TipoUS>> getAllTipoUS() {
        return ResponseEntity.status(HttpStatus.OK).body(tipoUSRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTipoUSById(@PathVariable(value = "id") long id) {

        Optional<TipoUS> tipoUS = tipoUSRepository.findById(id);
        if (tipoUS.isEmpty()) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.US_TYPE_NOT_FOUND.getCode());}

        return ResponseEntity.status(HttpStatus.OK).body(tipoUS.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTipoUS(@PathVariable(value = "id") long id,
                                               @RequestBody @Valid TipoUSInputDTO tipoUSInputDTO) {
        Optional<TipoUS> tipoUS = tipoUSRepository.findById(id);
        if (tipoUS.isEmpty()) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.US_TYPE_NOT_FOUND.getCode());}

        var tiposUS = tipoUS.get();
        BeanUtils.copyProperties(tipoUSInputDTO, tiposUS);

        return ResponseEntity.status(HttpStatus.OK).body(tipoUSRepository.save(tiposUS));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTipoUS(@PathVariable(value = "id") long id) {
        Optional<TipoUS> tipoUS = tipoUSRepository.findById(id);
        if (tipoUS.isEmpty()) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.US_TYPE_NOT_FOUND.getCode());}

        tipoUSRepository.delete(tipoUS.get());
        return ResponseEntity.status(HttpStatus.OK).body(StatusCodes.US_TYPE_REMOVED.getCode());
    }

}
