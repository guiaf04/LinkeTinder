package com.linketinder.dto

import java.time.LocalDate

class CandidateDTO implements Serializable{

    Long id
    String firstName
    String lastName
    LocalDate birthday
    String cpf
    String country
    String cep
    String personalDescription

    @Override
    String toString() {
        return "Candidato{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dataNascimento='" + birthday + '\'' +
                ", cpf='" + cpf + '\'' +
                ", country='" + country + '\'' +
                ", cep='" + cep + '\'' +
                ", descricaoPessoal='" + personalDescription + '\'' +
                '}'
    }
}

