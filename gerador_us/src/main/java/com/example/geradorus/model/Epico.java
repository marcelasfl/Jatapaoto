package com.example.geradorus.model;

import jakarta.persistence.*;
import lombok.*;

//import java.util.List;

import java.util.Set;

@Entity
@Table
@Data

public class Epico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome; //Nome do projeto

    private String texto; //Epico Escrito

    @ManyToOne
    @JoinColumn(name = "tipo_epico_id")
    private TipoEpico tipoEpico;

    @OneToOne(mappedBy="epico")
    private HistoriaUsuario historiaUsuario;


}
