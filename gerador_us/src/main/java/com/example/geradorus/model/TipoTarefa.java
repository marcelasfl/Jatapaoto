package com.example.geradorus.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TipoTarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String descricao;

}
