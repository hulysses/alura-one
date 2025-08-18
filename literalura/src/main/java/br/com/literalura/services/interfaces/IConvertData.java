package br.com.literalura.services.interfaces;

public interface IConvertData {
    <T> T convert(String json, Class<T> clazz);
}
