package br.com.literalura.services;

import br.com.literalura.services.interfaces.IConvertData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ConvertDataService implements IConvertData {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T convert(String json, Class<T> clazz) {
        System.out.println(json);
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
