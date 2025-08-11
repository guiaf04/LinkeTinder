package com.linketinder.dto;

import com.linketinder.model.Skill;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CandidateDTO extends UnmatchedCandidateDTO {

    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String cpf;

    // Constructors
    public CandidateDTO() {
        super();
    }

    public CandidateDTO(Long id, String country, String cep, String personalDescription, List<Skill> skills,
                       String firstName, String lastName, LocalDate birthday, String cpf) {
        super(id, country, cep, personalDescription, skills);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.cpf = cpf;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CandidateDTO that = (CandidateDTO) o;
        return Objects.equals(firstName, that.firstName) &&
               Objects.equals(lastName, that.lastName) &&
               Objects.equals(birthday, that.birthday) &&
               Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, birthday, cpf);
    }

    @Override
    public String toString() {
        return "CandidateDTO{" +
                "id=" + getId() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", cpf='" + cpf + '\'' +
                ", country='" + getCountry() + '\'' +
                ", cep='" + getCep() + '\'' +
                ", personalDescription='" + getPersonalDescription() + '\'' +
                ", skills=" + getSkills() +
                '}';
    }
}
