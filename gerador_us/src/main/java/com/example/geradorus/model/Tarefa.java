package com.example.geradorus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "historia_usuario_id")
    @JsonIgnore
    private HistoriaUsuario historiaUsuario;

    @ManyToOne
    @JoinColumn(name = "tipo_tarefa_id", nullable = false)
    private TipoTarefa tipoTarefa;

}