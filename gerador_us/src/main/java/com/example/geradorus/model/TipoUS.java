package com.example.geradorus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class TipoUS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "tipo_tarefa_id",nullable = false)
    private TipoTarefa tipoTarefa;

    @ManyToOne
    @JoinColumn(name = "tipo_epico_id")
    private TipoEpico tipoEpico;
}