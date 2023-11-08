package com.example.geradorus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.geradorus.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}