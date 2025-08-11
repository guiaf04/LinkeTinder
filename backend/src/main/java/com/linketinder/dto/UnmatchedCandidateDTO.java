package com.linketinder.dto;

import com.linketinder.model.Skill;
import java.util.List;
import java.util.Objects;

public class UnmatchedCandidateDTO {

    private Long id;
    private String country;
    private String cep;
    private String personalDescription;
    private List<Skill> skills;

    // Constructors
    public UnmatchedCandidateDTO() {}

    public UnmatchedCandidateDTO(Long id, String country, String cep, String personalDescription, List<Skill> skills) {
        this.id = id;
        this.country = country;
        this.cep = cep;
        this.personalDescription = personalDescription;
        this.skills = skills;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPersonalDescription() {
        return personalDescription;
    }

    public void setPersonalDescription(String personalDescription) {
        this.personalDescription = personalDescription;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnmatchedCandidateDTO that = (UnmatchedCandidateDTO) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(country, that.country) &&
               Objects.equals(cep, that.cep) &&
               Objects.equals(personalDescription, that.personalDescription) &&
               Objects.equals(skills, that.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, cep, personalDescription, skills);
    }

    @Override
    public String toString() {
        return "UnmatchedCandidateDTO{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", cep='" + cep + '\'' +
                ", personalDescription='" + personalDescription + '\'' +
                ", skills=" + skills +
                '}';
    }
}
