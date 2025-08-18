package br.com.literalura.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataAuthorDTO(
        String name,
        @JsonAlias("birth_year") Integer birthDate,
        @JsonAlias("death_year") Integer deathDate
) {
}