package com.example.geradorus.controller;
import com.example.geradorus.model.TipoEpico;
import com.example.geradorus.repository.TipoEpicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class TipoEpicoController {

    @Autowired
    private TipoEpicoRepository tipoEpicoRepository;
    @GetMapping("/")
    public Iterable<TipoEpico> index() {
        return tipoEpicoRepository.findAll();
    }


    @GetMapping("/{id}")
    public TipoEpico getUser(@PathVariable Long id) {
        return tipoEpicoRepository.findById(id).get();
    }

    @PostMapping("/")
    public TipoEpico create(@RequestBody TipoEpico epico) {
        return tipoEpicoRepository.save(epico);
    }

    @PutMapping("/{id}")
    public TipoEpico update(@PathVariable long id, @RequestBody TipoEpico proximotipoEpico) {
        TipoEpico tipoEpico = tipoEpicoRepository.findById(id).get();
        tipoEpico.setDescricao(proximotipoEpico.getDescricao());
        return tipoEpicoRepository.save(tipoEpico);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        tipoEpicoRepository.deleteById(id);
        return "Sucesso!";
    }

}

