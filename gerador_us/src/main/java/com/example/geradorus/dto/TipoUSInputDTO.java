package com.example.geradorus.dto;

import jakarta.validation.constraints.NotBlank;

public record TipoUSInputDTO(@NotBlank String descricao, Long tipoTarefaId) { }

