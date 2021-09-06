package br.com.zup.emerson.mercadolivre.utils;

public interface EnviaEmail {

    void send(String corpoEmail, String titulo, String nameFrom, String from, String to);

}
