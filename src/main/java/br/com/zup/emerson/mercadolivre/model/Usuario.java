package br.com.zup.emerson.mercadolivre.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Email
    @Column(unique = true)
    private String login;
    @NotBlank
    @Size(min = 6)
    private String senha;
    @CreationTimestamp
    private LocalDate horaCadastro;

    @Deprecated
    public Usuario() {
    }
}
