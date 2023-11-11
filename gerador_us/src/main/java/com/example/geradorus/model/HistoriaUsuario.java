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

    @ManyToOne
    @JoinColumn(name = "epico_id")
    private Epico epico;

    @OneToMany(mappedBy = "historiaUsuario")
    private List<Tarefa> tarefa;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_tarefa_id")
    private TipoTarefa tipoTarefa;

}
