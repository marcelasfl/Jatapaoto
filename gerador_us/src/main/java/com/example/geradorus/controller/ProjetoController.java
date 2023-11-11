package com.example.geradorus.controller;

import com.example.geradorus.codes.StatusCodes;
import com.example.geradorus.dto.ProjetoInputDTO;
import com.example.geradorus.model.Projeto;
import com.example.geradorus.repository.ProjetoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projeto")
public class ProjetoController {
    @Autowired
    ProjetoRepository projetoRepository;

    @PostMapping
    public ResponseEntity<Object> criarProjeto(@RequestBody @Valid ProjetoInputDTO projetoInputDTO) {
        var projeto = new Projeto();
        BeanUtils.copyProperties(projetoInputDTO, projeto);

        return ResponseEntity.status(HttpStatus.CREATED).body(projetoRepository.save(projeto));
    }

    @GetMapping
    public ResponseEntity<List<Projeto>> listarProjetos() {return ResponseEntity.status(HttpStatus.OK).body(projetoRepository.findAll());}

    @GetMapping("/{id}")
    public ResponseEntity<Object> projetoPeloId(@PathVariable int id) {
        Optional<Projeto> projetoOptional = projetoRepository.findById((long) id);

        return projetoOptional.<ResponseEntity<Object>>map(projeto ->
                ResponseEntity.status(HttpStatus.OK).body(projeto)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.PROJECT_NOT_FOUND.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarProjeto(@PathVariable int id) {
        Optional<Projeto> projetoOptional = projetoRepository.findById((long) id);
        if (projetoOptional.isEmpty()) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.PROJECT_NOT_FOUND.getCode());}
        projetoRepository.deleteById((long) id);

        return ResponseEntity.status(HttpStatus.OK).body(StatusCodes.PROJECT_REMOVED.getCode());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarProjeto(@PathVariable int id, @RequestBody @Valid ProjetoInputDTO projetoInputDTO) {
        Optional<Projeto> projetoOptional = projetoRepository.findById((long) id);
        if (projetoOptional.isEmpty()) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.PROJECT_NOT_FOUND.getCode());}

        var projeto = new Projeto();
        BeanUtils.copyProperties(projetoInputDTO, projeto);
        projeto.setId(projetoOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(projetoRepository.save(projeto));
    }

}
