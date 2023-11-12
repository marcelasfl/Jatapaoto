package com.example.geradorus.dto;

import jakarta.validation.constraints.NotBlank;

public record TipoHUInputDTO(@NotBlank String descricao, Long tipoTarefaId) { }

