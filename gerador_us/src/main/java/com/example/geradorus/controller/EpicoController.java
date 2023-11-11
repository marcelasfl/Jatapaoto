package com.example.geradorus.controller;

import com.example.geradorus.codes.StatusCodes;
import com.example.geradorus.dto.EpicoInputDTO;
import com.example.geradorus.model.Epico;
import com.example.geradorus.repository.EpicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/epico")
public class EpicoController {
    @Autowired
    EpicoRepository epicoRepository;

    @PostMapping
    public ResponseEntity<Object> createEpico(@RequestBody @Valid EpicoInputDTO epicoInputDTO){
        var epico = new Epico();
        BeanUtils.copyProperties(epicoInputDTO, epico);

        return ResponseEntity.status(HttpStatus.CREATED).body(epicoRepository.save(epico));
    }

    @GetMapping
    public ResponseEntity<List<Epico>> getAllEpicos(){return ResponseEntity.status(HttpStatus.OK).body(epicoRepository.findAll());}

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEpicoById(@PathVariable int id){
        Optional<Epico> epicoOptional = epicoRepository.findById((long) id);

        return epicoOptional.<ResponseEntity<Object>>map(epico ->
                ResponseEntity.status(HttpStatus.OK).body(epico)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.PROJECT_NOT_FOUND.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEpico(@PathVariable int id){
        Optional<Epico> epicoOptional = epicoRepository.findById((long) id);
        if(epicoOptional.isEmpty()){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.PROJECT_NOT_FOUND);}
        epicoRepository.deleteById((long) id);

        return ResponseEntity.status(HttpStatus.OK).body(StatusCodes.PROJECT_REMOVED.getCode());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEpico(@PathVariable int id, @RequestBody @Valid EpicoInputDTO epicoInputDTO){
        Optional<Epico> epicoOptional = epicoRepository.findById((long) id);
        if(epicoOptional.isEmpty()){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.PROJECT_NOT_FOUND);}

        var epico = new Epico();
        BeanUtils.copyProperties(epicoInputDTO, epico);
        epico.setId(epicoOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(epicoRepository.save(epico));

    }

}
