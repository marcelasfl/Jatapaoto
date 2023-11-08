package com.example.geradorus.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table
@Data
public class HistoriaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;

    private String descricao;

    private String relevancia;

    private String categoria;

    @ManyToOne
    @JoinColumn(name = "epico_id")
    private Epico epico;

    
    @OneToMany(mappedBy = "historiaUsuario")
    private List<Tarefa> tarefas;
    
}
