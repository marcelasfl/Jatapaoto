package com.example.geradorus.model;

import java.util.Set;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
public class TipoTarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String descricao;




    @OneToMany(mappedBy = "tipoTarefa")
    private Set<TipoUS> tipoHistoriaUsuarios;

    @OneToOne
    @JoinColumn(name = "tipo_us_id")
    private TipoUS tipoUS;
}
