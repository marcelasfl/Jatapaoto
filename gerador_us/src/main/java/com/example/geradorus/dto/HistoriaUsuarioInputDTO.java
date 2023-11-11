package com.example.geradorus.dto;

import jakarta.validation.constraints.NotBlank;

public record HistoriaUsuarioInputDTO (String categoria, String descricao, String relevancia, @NotBlank String titulo) { }
