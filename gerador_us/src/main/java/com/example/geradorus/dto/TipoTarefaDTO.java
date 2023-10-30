package com.example.geradorus.dto;

import jakarta.validation.constraints.NotBlank;

public record TipoTarefaDTO(@NotBlank String descricao) {
}
