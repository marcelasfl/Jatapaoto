package com.example.geradorus.controller;
import com.example.geradorus.model.Epico;
import com.example.geradorus.repository.EpicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class EpicoController {

    @Autowired
    private EpicoRepository epicoRepository;
    @GetMapping("/")
    public Iterable<Epico> index() {
        return epicoRepository.findAll();
    }


    @GetMapping("/{id}")
    public Epico getUser(@PathVariable Long id) {
        return epicoRepository.findById(id).get();
    }

    @PostMapping("/")
    public Epico create(@RequestBody Epico epico) {
        return epicoRepository.save(epico);
    }

    @PutMapping("/{id}")
    public Epico update(@PathVariable long id, @RequestBody Epico proximoEpico) {
        Epico epico = epicoRepository.findById(id).get();
        epico.setTitulo(proximoEpico.getTitulo());
        epico.setDescricao(proximoEpico.getDescricao());
        epico.setRelevancia(proximoEpico.getRelevancia());
        epico.setCategoria(proximoEpico.getCategoria());
        return epicoRepository.save(epico);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        epicoRepository.deleteById(id);
        return "Sucesso!";
    }

}

