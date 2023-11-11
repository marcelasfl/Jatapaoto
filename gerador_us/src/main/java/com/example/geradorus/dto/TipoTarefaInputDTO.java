package com.example.geradorus.dto;

import jakarta.validation.constraints.NotBlank;

public record TipoTarefaInputDTO(@NotBlank String descricao) { }
