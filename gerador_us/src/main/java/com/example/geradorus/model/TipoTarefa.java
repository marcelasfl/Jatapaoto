package com.example.geradorus.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class TipoTarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String descricao;

    @OneToMany(mappedBy="tipoTarefa")
    private List<Tarefa> tarefa;

    @ManyToOne
    @JoinColumn(name = "us_id")
    private TipoUS tipoUS;

}
