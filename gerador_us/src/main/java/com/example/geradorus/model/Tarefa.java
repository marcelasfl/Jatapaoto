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

    private String nome;

    //@OneToMany(mappedBy="historiaUsuario")
    //private HistoriaUsuario historiaUsuario;


}
