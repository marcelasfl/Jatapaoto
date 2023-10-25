package com.example.geradorus.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class TipoEpico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @OneToMany(mappedBy="tipoEpico")
    private List<Epico> epico;


    //@OneToMany(mappedBy="tipoUS")
    //private TipoUS tipoUS;

}
