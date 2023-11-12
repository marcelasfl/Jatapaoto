package com.example.geradorus.controller;

import com.example.geradorus.dto.GeraTarefaInputDTO;
import com.example.geradorus.model.HistoriaUsuario;
import com.example.geradorus.model.TipoTarefa;
import com.example.geradorus.repository.HistoriaUsuarioRepository;
import com.example.geradorus.repository.TipoTarefaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tarefa")
public class TarefaController {

    @Autowired
    TipoTarefaRepository tipoTarefaRepository;

    @Autowired
    HistoriaUsuarioRepository HURepository;

    @PostMapping("/gerar/tarefa")
    public void gerarTarefa(@RequestBody @Valid GeraTarefaInputDTO geraTarefaInputDTO) {
        HistoriaUsuario historiaUsuario = HURepository.findById(geraTarefaInputDTO.HistoriaUsuarioId()).get();
        TipoTarefa tipoTarefa = tipoTarefaRepository.findById(geraTarefaInputDTO.TipoTarefaId()).get();
        historiaUsuario.setTipoTarefa(tipoTarefa);
        HURepository.save(historiaUsuario);
    }

}

