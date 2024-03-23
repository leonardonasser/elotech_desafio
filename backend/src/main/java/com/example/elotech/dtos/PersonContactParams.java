package com.example.elotech.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonContactParams(Long id, @NotBlank String name, @NotBlank String phone,
                                  @NotNull String email) {
}
