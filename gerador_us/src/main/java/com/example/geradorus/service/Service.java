package com.example.geradorus.service;

import com.example.geradorus.model.*;
import com.example.geradorus.repository.HistoriaUsuarioRepository;
import com.example.geradorus.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    HistoriaUsuarioRepository historiaUsuarioRepository;

    @Autowired
    TarefaRepository tarefaRepository;

    public HistoriaUsuario salvarHistoriaUsuario(Epico epico, String descricao) {
        HistoriaUsuario historiaUsuario = new HistoriaUsuario();

        historiaUsuario.setCategoria(epico.getCategoria());
        historiaUsuario.setDescricao(descricao);
        historiaUsuario.setRelevancia(epico.getRelevancia());
        historiaUsuario.setTitulo(epico.getTitulo());

        return historiaUsuarioRepository.save(historiaUsuario);
    }

    public void criarTarefa(HistoriaUsuario historiaUsuario, TipoHU tipoHistoriaUsuario, String entidade) {
        Tarefa tarefa = new Tarefa();
        TipoTarefa tipoTarefa = tipoHistoriaUsuario.getTipoTarefa();
        tarefa.setTitulo(tipoTarefa.getDescricao());
        tarefa.setDescricao(tipoTarefa.getDescricao().concat(" de ").concat(entidade));
        tarefa.setHistoriaUsuario(historiaUsuario);
        tarefa.setTipoTarefa(tipoTarefa);

        tarefaRepository.save(tarefa);
    }

}
