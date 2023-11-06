package com.example.geradorus.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
public class TipoTarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String descricao;


    //@ManyToOne
    //@JoinColumn(name = "tipoUS_id") // Nome da coluna na tabela TipoTarefa que representa a chave estrangeira para TipoUS
    //private TipoUS tipoUS;

    @OneToMany(mappedBy = "tipoTarefa")
    @JsonIgnore
    private List<HistoriaUsuario> historiaUsuario;

}
