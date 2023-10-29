package com.example.geradorus.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Data
public class Epico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;

    private String descricao;

    private String relevancia; //pode ser do tipo int? pode ser enum?

    private String categoria; //pode ser enum?

    @ManyToOne
    @JoinColumn(name = "tipo_epico_id")
    private TipoEpico tipoEpico;

    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    @OneToOne(mappedBy="epico")
    private HistoriaUsuario historiaUsuario;

}
