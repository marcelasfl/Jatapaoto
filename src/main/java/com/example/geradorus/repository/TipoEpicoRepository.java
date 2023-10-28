package com.example.geradorus.repository;

import com.example.geradorus.model.Epico;
import com.example.geradorus.model.TipoEpico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoEpicoRepository extends JpaRepository<TipoEpico, Long> {

}