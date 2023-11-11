package com.example.geradorus.repository;

import com.example.geradorus.model.TipoTarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTarefaRepository extends JpaRepository<TipoTarefa, Long> { }

