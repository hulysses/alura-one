package br.com.literalura.services.interfaces;

public interface IConvertData {
    <T> T obterDados(String json, Class<T> classe);
}
