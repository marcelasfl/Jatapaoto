package com.example.geradorus.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

//import java.util.List;

import java.util.Set;

@Entity
@Table
@Data

public class Epico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo; //Nome do projeto

    private String descricao; //Epico Escrito

    private String relevancia; //Epico Escrito

    private String categoria; //Epico Escrito

    @ManyToOne
    @JoinColumn(name = "tipo_epico_id")
    private TipoEpico tipoEpico;

    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    @OneToOne(mappedBy="epico")
    private HistoriaUsuario historiaUsuario;


}
