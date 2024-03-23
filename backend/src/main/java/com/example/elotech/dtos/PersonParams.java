package com.example.elotech.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public record PersonParams(@NotBlank String name, @NotBlank String cpf,
                           @NotNull LocalDate birthDate, @NotEmpty List<PersonContactParams> contactParamsList) {
}
