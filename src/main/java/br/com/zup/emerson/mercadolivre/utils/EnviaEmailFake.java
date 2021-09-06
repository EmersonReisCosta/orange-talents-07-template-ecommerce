package br.com.zup.emerson.mercadolivre.utils;

import org.springframework.stereotype.Component;

@Component
public class EnviaEmailFake implements EnviaEmail{

    @Override
    public void send(String corpoEmail, String titulo, String nameFrom, String from, String to) {
        System.out.println(corpoEmail);
        System.out.println(titulo);
        System.out.println(nameFrom);
        System.out.println(from);
        System.out.println(to);
    }
}