package com.example.geradorus.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class HistoriaUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @OneToOne
    @JoinColumn(name = "epico_id", referencedColumnName = "id")
    private Epico epico;


}
