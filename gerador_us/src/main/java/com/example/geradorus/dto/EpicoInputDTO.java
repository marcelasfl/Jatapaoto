package com.example.geradorus.dto;

import jakarta.validation.constraints.NotBlank;

public record EpicoInputDTO(@NotBlank String titulo, String descricao, String relevancia, String categoria) { }
