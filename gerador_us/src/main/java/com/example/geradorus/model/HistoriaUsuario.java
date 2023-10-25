package com.example.geradorus.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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

    @OneToOne
    @JoinColumn(name = "epico_id", referencedColumnName = "id")
    private Epico epico;

    @OneToMany(mappedBy="historiaUsuario")
    private List<Tarefa> tarefa;


}
