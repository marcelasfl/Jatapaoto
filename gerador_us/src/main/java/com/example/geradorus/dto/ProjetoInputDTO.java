package com.example.geradorus.dto;

import jakarta.validation.constraints.NotBlank;

public record ProjetoInputDTO(@NotBlank String nome) {
}
