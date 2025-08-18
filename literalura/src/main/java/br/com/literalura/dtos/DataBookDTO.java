package br.com.literalura.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBookDTO(
        String title,
        List<DataAuthorDTO> author,
        List<String> languages
) {
}
