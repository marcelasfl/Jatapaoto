package com.example.geradorus.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TipoUS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "tipo_tarefa_id")
    private TipoTarefa tipoTarefa;

    @ManyToOne
    private TipoEpico tipoEpico;



    //@OneToMany(mappedBy="tipoTarefa")
   // private TipoTarefa tipoTarefa;

}
