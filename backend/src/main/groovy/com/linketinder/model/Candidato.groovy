package com.linketinder.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

import java.time.LocalDate

@Entity
@Table(name="candidato")
class Candidato implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Column(name = "nome", nullable = false, length = 50)
    String firstName

    @Column(name = "sobrenome", nullable = false, length = 100)
    String lastName

    @Column(name="data_nascimento", nullable = false)
    LocalDate birthday

    @Column(name = "email", nullable = false, unique = true)
    String email

    @Column(name = "cpf", nullable = false, length = 20, unique = true)
    String cpf

    @Column(name = "pais", nullable = false, length = 100)
    String country

    @Column(name = "cep", nullable = false, length = 20)
    String cep

    @Column(name="descricao_pessoal", nullable = true)
    String personalDescription

    @Column(name = "senha", nullable = false, length = 40)
    String password

    @Override
    String toString() {
        return "Candidato{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dataNascimento='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", country='" + country + '\'' +
                ", cep='" + cep + '\'' +
                ", descricaoPessoal='" + personalDescription + '\'' +
                ", password='" + password + '\'' +
                '}'
    }
}

