package com.example.geradorus.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

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

    @OneToMany(mappedBy ="epico")
    private List<HistoriaUsuario> historiaUsuario;

}
