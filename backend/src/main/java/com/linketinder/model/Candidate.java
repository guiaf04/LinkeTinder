package com.linketinder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "candidato")
public class Candidate implements Serializable {
  @Override
  public String toString() {
    return "Candidato{"
        + "firstName='" + firstName + "'"
        + ", lastName='" + lastName + "'"
        + ", dataNascimento='" + birthday + "'"
        + ", email='" + email + "'"
        + ", cpf='" + cpf + "'"
        + ", country='" + country + "'"
        + ", cep='" + cep + "'"
        + ", descricaoPessoal='" + personalDescription + "'"
        + ", password='" + password + "'"
        + "}";
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "nome", nullable = false, length = 50)
  private String firstName;
  @Column(name = "sobrenome", nullable = false, length = 100)
  private String lastName;
  @Column(name = "data_nascimento", nullable = false)
  private LocalDate birthday;
  @Column(name = "email", nullable = false, unique = true)
  private String email;
  @Column(name = "cpf", nullable = false, length = 20, unique = true)
  private String cpf;
  @Column(name = "pais", nullable = false, length = 100)
  private String country;
  @Column(name = "cep", nullable = false, length = 20)
  private String cep;
  @Column(name = "descricao_pessoal", nullable = true)
  private String personalDescription;
  @Column(name = "senha", nullable = false, length = 40)
  private String password;
}
