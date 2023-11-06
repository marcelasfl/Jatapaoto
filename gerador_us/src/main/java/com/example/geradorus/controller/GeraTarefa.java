package com.example.geradorus.controller;
import com.example.geradorus.dto.GerarTarefaGerarInputDTO;
import com.example.geradorus.model.HistoriaUsuario;
import com.example.geradorus.model.Tarefa;
import com.example.geradorus.model.TipoTarefa;
import com.example.geradorus.repository.HistoriaUsuarioRepository;
import com.example.geradorus.repository.TarefaRepository;
import com.example.geradorus.repository.TipoTarefaRepository;


import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/tarefa")
public class GeraTarefa {

    @Autowired
    TipoTarefaRepository tipoTarefaRepository;
    
    @Autowired
    HistoriaUsuarioRepository historiaUsuarioRepository;
    
    @Autowired
    TarefaRepository tarefaRepository;


    @PostMapping("/gerar")
    public void criarGerarTarefa(@RequestBody @Valid GerarTarefaGerarInputDTO gerarTarefaGerarInputDTO){
        //HistoriaUsuario historiausuario = historiaUsuarioRepository.findById(gerarTarefaGerarInputDTO.HistoriaUsuarioId()).get();
        //TipoTarefa tipoTarefa = tipoTarefaRepository.findById(gerarTarefaGerarInputDTO.TipoTarefaId()).get();
        //historiausuario.setTipoTarefa(tipoTarefa);
        //historiaUsuarioRepository.save(historiausuario);

        
    }
  }

