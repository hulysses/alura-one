package br.com.literalura.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DataAuthorDTO(
        String name,
        @JsonAlias("birth_year") String birthDate,
        @JsonAlias("death_year") String deathDate
) {
}
