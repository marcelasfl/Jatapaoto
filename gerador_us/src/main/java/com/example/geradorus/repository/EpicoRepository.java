package com.example.geradorus.repository;

import com.example.geradorus.model.Epico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpicoRepository extends JpaRepository<Epico, Long> {
}