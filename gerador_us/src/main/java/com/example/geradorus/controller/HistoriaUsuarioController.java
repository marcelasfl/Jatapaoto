package com.example.geradorus.controller;

import java.util.ArrayList;
import java.util.List;

//import com.sun.tools.javac.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.geradorus.codes.StatusCodes;
import com.example.geradorus.dto.GerarHistoriaUsuarioDTO;
import com.example.geradorus.model.Epico;
import com.example.geradorus.model.HistoriaUsuario;
import com.example.geradorus.model.Tarefa;
import com.example.geradorus.model.TipoTarefa;
import com.example.geradorus.model.TipoUS;
import com.example.geradorus.repository.EpicoRepository;
import com.example.geradorus.repository.HistoriaUsuarioRepository;
import com.example.geradorus.repository.TarefaRepository;
import com.example.geradorus.repository.TipoUSRepository;

//import static com.sun.tools.javac.util.StringUtils.toUpperCase;

@RestController
@RequestMapping("/api/us")
public class HistoriaUsuarioController {

    @Autowired
    EpicoRepository epicoRepository;
    @Autowired
    TipoUSRepository tipoHistoriaUsuarioRepository;
    @Autowired
    HistoriaUsuarioRepository historiaUsuarioRepository;
    @Autowired
    TarefaRepository tarefaRepository;


    @GetMapping("")
    public List<HistoriaUsuario> getAllHistoriaUsuario() {
        return historiaUsuarioRepository.findAll();
    }

    @PostMapping("/gerar")
    public ResponseEntity<List<HistoriaUsuario>> gerarHistoriaUsuario(
        @RequestBody GerarHistoriaUsuarioDTO gerarHistoriaUsuarioDTO) {
    Epico epico = epicoRepository.findById(gerarHistoriaUsuarioDTO.epicoId()).get();
    List<TipoUS> tiposHistoriaUsuario = tipoHistoriaUsuarioRepository.findAll();

    String epicoDescricao = epico.getDescricao();
    List<HistoriaUsuario> historias = new ArrayList<HistoriaUsuario>();
    tiposHistoriaUsuario.forEach(tipo -> {
        String entidade = epicoDescricao.substring(epicoDescricao.lastIndexOf(" ") +
                1);
        String palavra = epicoDescricao.replaceAll("(?<=\\bdesejo\\s)\\w+",
                tipo.getDescricao());
        HistoriaUsuario historiaUsuario = salvarHistoriaUsuario(epico, palavra);
        criarTarefa(historiaUsuario, tipo, entidade);
        historias.add(historiaUsuario);
    });

    return ResponseEntity.status(HttpStatus.CREATED).body(historias);
}

    private HistoriaUsuario salvarHistoriaUsuario(Epico epico, String descricao) {
        HistoriaUsuario historiaUsuario = new HistoriaUsuario();

        historiaUsuario.setCategoria(epico.getCategoria());
        historiaUsuario.setDescricao(descricao);
        historiaUsuario.setRelevancia(epico.getRelevancia());
        historiaUsuario.setTitulo(epico.getTitulo());
        return historiaUsuarioRepository.save(historiaUsuario);
    }

    private void criarTarefa(HistoriaUsuario historiaUsuario, TipoUS tipoHistoriaUsuario, String entidade) {
        Tarefa tarefa = new Tarefa();
        TipoTarefa tipoTarefa = tipoHistoriaUsuario.getTipoTarefa();
        tarefa.setTitulo(tipoTarefa.getDescricao());
        tarefa.setDescricao(tipoTarefa.getDescricao().concat(" de ").concat(entidade));
        tarefa.setHistoriaUsuario(historiaUsuario);
        tarefa.setTipoTarefa(tipoTarefa);
        tarefaRepository.save(tarefa);
    }

}