package com.linketinder.model;

import jakarta.persistence.*;

@Entity
@Table(name = "empresa")
public class Employee {
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "nome", nullable = false, length = 100)
  private String name;
  @Column(name = "email", nullable = false, length = 100)
  private String email;
  @Column(name = "cnpj", nullable = false, length = 20)
  private String cnpj;
  @Column(name = "pais", nullable = false, length = 100)
  private String country;
  @Column(name = "cep", nullable = false, length = 20)
  private String cep;
  @Column(name = "descricao", nullable = false, length = 255)
  private String description;
  @Column(name = "senha", nullable = false, length = 40)
  private String password;
}
