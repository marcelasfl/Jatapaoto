package com.example.geradorus.repository;

import com.example.geradorus.model.Tarefa;

import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    //List<Tarefa> findAllByTipoTarefaId(@Param("value") Long value);
    
}
