package com.example.geradorus.dto;

import jakarta.validation.constraints.NotBlank;

public record TipoEpicoInputDTO(@NotBlank String descricao) { }
