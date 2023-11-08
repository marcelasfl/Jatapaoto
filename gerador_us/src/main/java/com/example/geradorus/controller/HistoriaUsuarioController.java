package com.example.geradorus.controller;


import com.example.geradorus.dto.HistoriaUsuarioInputDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.example.geradorus.dto.TipoUSInputDTO;
import com.example.geradorus.dto.HistoriaUsuarioInputDTO;

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


import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




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

    @GetMapping("/{id}")
    public ResponseEntity<Object> getHUById(@PathVariable(value="id") long id){
        Optional<HistoriaUsuario> hu = historiaUsuarioRepository.findById(id);
        if(hu.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.US_NOT_FOUND.getCode());
        }
        return ResponseEntity.status(HttpStatus.OK).body(hu.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateHU(@PathVariable(value="id") long id,
                                           @RequestBody @Valid HistoriaUsuarioInputDTO historiaUsuarioInputDTO) {
        Optional<HistoriaUsuario> hu = historiaUsuarioRepository.findById(id);
        if(hu.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.US_NOT_FOUND.getCode()); //Não encontra e retorna o HttpStatus
        }
        var hus = hu.get();
        BeanUtils.copyProperties(historiaUsuarioInputDTO, hu); //Converterndo o dto em model
        return ResponseEntity.status(HttpStatus.OK).body(historiaUsuarioRepository.save(hus));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getHUById(@PathVariable(value="id") long id){
        Optional<HistoriaUsuario> hu = historiaUsuarioRepository.findById(id);
        if(hu.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.US_NOT_FOUND.getCode());
        }
        return ResponseEntity.status(HttpStatus.OK).body(hu.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateHU(@PathVariable(value="id") long id,
                                           @RequestBody @Valid HistoriaUsuarioInputDTO historiaUsuarioInputDTO) {
        Optional<HistoriaUsuario> hu = historiaUsuarioRepository.findById(id);
        if(hu.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.US_NOT_FOUND.getCode()); //Não encontra e retorna o HttpStatus
        }
        var hus = hu.get();
        BeanUtils.copyProperties(historiaUsuarioInputDTO, hu); //Converterndo o dto em model
        return ResponseEntity.status(HttpStatus.OK).body(historiaUsuarioRepository.save(hus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteHU(@PathVariable(value="id") long id) {
        Optional<HistoriaUsuario> hu = historiaUsuarioRepository.findById(id);

        if(hu.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.US_NOT_FOUND.getCode());
        }
        historiaUsuarioRepository.delete(hu.get());
        return ResponseEntity.status(HttpStatus.OK).body(StatusCodes.US_TYPE_REMOVED.getCode());
    }

}
