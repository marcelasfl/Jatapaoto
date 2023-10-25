package com.example.geradorus.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TipoUS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String verbo;

    //@OneToMany(mappedBy="tipoTarefa")
   // private TipoTarefa tipoTarefa;

}
