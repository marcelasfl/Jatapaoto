package com.example.geradorus.controller;

import com.example.geradorus.codes.StatusCodes;
import com.example.geradorus.dto.TipoUSInputDTO;
import com.example.geradorus.model.TipoUS;
import com.example.geradorus.repository.ProjetoRepository;
import com.example.geradorus.repository.TipoUSRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipoUS")
public class TipoUSController {

    @Autowired
    TipoUSRepository tipoUSRepository;

    @PostMapping
    public ResponseEntity<TipoUS> create(@RequestBody @Valid TipoUSInputDTO tipoUSInputDTO) { //@Valid faz a validação
        var tipoUS = new TipoUS();
        BeanUtils.copyProperties(tipoUSInputDTO, tipoUS); //O beanUtil converte o dto no model
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoUSRepository.save(tipoUS)); //O status é 201 junto com o body -- o Repositorio já possui os métodos JPAs salvos
    }

    @GetMapping
    public ResponseEntity<List<TipoUS>> getAllTipoUS(){
        return ResponseEntity.status(HttpStatus.OK).body(tipoUSRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTipoUSById(@PathVariable(value="id") long id){ //O get One utiliza do getMapping -- o responseEntity representa toda resposta do HTTP e aqui será um objeto
        Optional<TipoUS> tipoUS = tipoUSRepository.findById(id); //Optional é um método para trabalhar com já prontos
        if(tipoUS.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.US_TYPE_NOT_FOUND.getCode());
        }
        return ResponseEntity.status(HttpStatus.OK).body(tipoUS.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTipoUS(@PathVariable(value="id") long id,
                                                @RequestBody @Valid TipoUSInputDTO tipoUSInputDTO) {
        Optional<TipoUS> tipoUS = tipoUSRepository.findById(id);
        if(tipoUS.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.US_TYPE_NOT_FOUND.getCode()); //Não encontra e retorna o HttpStatus
        }
        var tiposUS = tipoUS.get(); //Aqui ele pega o produto pelo id e atriibui ele a uma variavel
        BeanUtils.copyProperties(tipoUSInputDTO, tiposUS); //Converterndo o dto em model
        return ResponseEntity.status(HttpStatus.OK).body(tipoUSRepository.save(tiposUS));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTipoUS(@PathVariable(value="id") long id) {
        Optional<TipoUS> tipoUS = tipoUSRepository.findById(id);
        if(tipoUS.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StatusCodes.US_TYPE_NOT_FOUND.getCode());
        }
        tipoUSRepository.delete(tipoUS.get()); //O jpa já possui o delete
        return ResponseEntity.status(HttpStatus.OK).body(StatusCodes.US_TYPE_REMOVED.getCode());
    }


}
