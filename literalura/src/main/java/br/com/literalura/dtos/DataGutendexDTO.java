package br.com.literalura.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataGutendexDTO(List<DataBookDTO> results) {
}
