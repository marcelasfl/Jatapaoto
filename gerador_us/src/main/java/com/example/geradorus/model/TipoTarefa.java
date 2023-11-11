package com.example.geradorus.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

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
