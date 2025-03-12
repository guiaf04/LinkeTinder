package com.linketinder.dto

import com.linketinder.model.Skill

import java.time.LocalDate

class CandidateDTO extends UnmatchedCandidateDTO{

    Long id
    String firstName
    String lastName
    LocalDate birthday
    String cpf
    String country
    String cep
    String personalDescription
    List<Skill> skills

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        CandidateDTO that = (CandidateDTO) o

        if (birthday != that.birthday) return false
        if (cep != that.cep) return false
        if (country != that.country) return false
        if (cpf != that.cpf) return false
        if (firstName != that.firstName) return false
        if (id != that.id) return false
        if (lastName != that.lastName) return false
        if (personalDescription != that.personalDescription) return false
        if (skills != that.skills) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0)
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0)
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0)
        result = 31 * result + (cpf != null ? cpf.hashCode() : 0)
        result = 31 * result + (country != null ? country.hashCode() : 0)
        result = 31 * result + (cep != null ? cep.hashCode() : 0)
        result = 31 * result + (personalDescription != null ? personalDescription.hashCode() : 0)
        result = 31 * result + (skills != null ? skills.hashCode() : 0)
        return result
    }
}

