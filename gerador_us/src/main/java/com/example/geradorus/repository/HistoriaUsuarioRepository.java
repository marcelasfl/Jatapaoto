package com.example.geradorus.repository;

import com.example.geradorus.model.HistoriaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriaUsuarioRepository extends JpaRepository<HistoriaUsuario, Long> {

}
