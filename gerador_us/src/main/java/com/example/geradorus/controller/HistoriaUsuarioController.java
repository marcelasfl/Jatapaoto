package com.example.geradorus.controller;

import com.example.geradorus.codes.StatusCodes;
import com.example.geradorus.dto.GeraHUInputDTO;
import com.example.geradorus.dto.HistoriaUsuarioInputDTO;
import com.example.geradorus.model.Epico;
import com.example.geradorus.model.HistoriaUsuario;
import com.example.geradorus.model.TipoHU;
import com.example.geradorus.repository.EpicoRepository;
import com.example.geradorus.repository.HistoriaUsuarioRepository;
import com.example.geradorus.repository.TipoHURepository;
import com.example.geradorus.service.Service;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/us")
public class HistoriaUsuarioController {

    @Autowired
    EpicoRepository epicoRepository;
    @Autowired
    TipoHURepository tipoHURepository;
    @Autowired
    HistoriaUsuarioRepository HURepository;
    @Autowired
    Service service;

    @PostMapping("/gerar/hu")
    public ResponseEntity<List<HistoriaUsuario>> gerarHU(@RequestBody GeraHUInputDTO geraHUInputDTO) {
        Epico epico = epicoRepository.findById(geraHUInputDTO.epicoId()).get();
        List<TipoHU> TipoHU = tipoHURepository.findAll();

        String descricaoEpico = epico.getDescricao();
        List<HistoriaUsuario> hu = new ArrayList<HistoriaUsuario>();
        TipoHU.forEach(tipo -> {
            String entidade = descricaoEpico.substring(descricaoEpico.lastIndexOf(" ") + 1);
            String palavra = descricaoEpico.replaceAll("(?<=\\bdesejo\\s)\\w+", tipo.getDescricao());
            HistoriaUsuario historiaUsuario = service.salvarHistoriaUsuario(epico, palavra);
            service.criarTarefa(historiaUsuario, tipo, entidade);
            hu.add(historiaUsuario);
        });

        return ResponseEntity.status(HttpStatus.CREATED).body(hu);
    }

    @GetMapping
    public ResponseEntity<List<HistoriaUsuario>> listarHUS(){return ResponseEntity.status(HttpStatus.OK).body(HURepository.findAll());}

    @GetMapping("/{id}")
    public ResponseEntity<Object> HUPeloId(@PathVariable(value = "id") long id) {
        Optional<HistoriaUsuario> HUOptional = HURepository.findById(id);

        return HUOptional.<ResponseEntity<Object>>map(historiaUsuario -> ResponseEntity.status(HttpStatus.OK).body(historiaUsuario)).
                orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.US_NOT_FOUND.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarHU(@PathVariable(value = "id") long id) {
        Optional<HistoriaUsuario> HUOptional = HURepository.findById(id);
        if (HUOptional.isEmpty()) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.US_NOT_FOUND.getCode());}

        HURepository.delete(HUOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body(StatusCodes.US_TYPE_REMOVED.getCode());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarHU(@PathVariable(value = "id") long id, @RequestBody @Valid HistoriaUsuarioInputDTO historiaUsuarioInputDTO) {
        Optional<HistoriaUsuario> HUOptional = HURepository.findById(id);
        if (HUOptional.isEmpty()) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.US_NOT_FOUND.getCode());}

        var hu = HUOptional.get();
        BeanUtils.copyProperties(historiaUsuarioInputDTO, hu);

        return ResponseEntity.status(HttpStatus.OK).body(HURepository.save(hu));
    }

}