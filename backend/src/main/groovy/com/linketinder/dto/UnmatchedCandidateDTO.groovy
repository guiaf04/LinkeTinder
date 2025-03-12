package com.linketinder.dto

import com.linketinder.model.Skill

class UnmatchedCandidateDTO {
    Long id
    String country
    String cep
    String personalDescription
    List<Skill> skills

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        UnmatchedCandidateDTO that = (UnmatchedCandidateDTO) o

        if (cep != that.cep) return false
        if (country != that.country) return false
        if (id != that.id) return false
        if (personalDescription != that.personalDescription) return false
        if (skills != that.skills) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (country != null ? country.hashCode() : 0)
        result = 31 * result + (cep != null ? cep.hashCode() : 0)
        result = 31 * result + (personalDescription != null ? personalDescription.hashCode() : 0)
        result = 31 * result + (skills != null ? skills.hashCode() : 0)
        return result
    }
}
