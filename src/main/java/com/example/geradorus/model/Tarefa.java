package com.example.geradorus.model;


import jakarta.persistence.*;
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
    @JoinColumn(name = "tipo_tarefa_id")
    private TipoTarefa tipoTarefa;

    @ManyToOne
    @JoinColumn(name = "us_id")
    private HistoriaUsuario historiaUsuario;
    //@OneToMany(mappedBy="historiaUsuario")
    //private HistoriaUsuario historiaUsuario;


}
