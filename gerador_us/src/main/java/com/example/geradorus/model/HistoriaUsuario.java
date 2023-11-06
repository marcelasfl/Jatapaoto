package com.example.geradorus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
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

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_tarefa_id")
    private TipoTarefa tipoTarefa;
    
}
