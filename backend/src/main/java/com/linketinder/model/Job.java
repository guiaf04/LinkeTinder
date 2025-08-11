package com.linketinder.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vaga")
public class Job {
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLocal() {
    return local;
  }

  public void setLocal(String local) {
    this.local = local;
  }

  public Long getIdEmployee() {
    return idEmployee;
  }

  public void setIdEmployee(Long idEmployee) {
    this.idEmployee = idEmployee;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "nome", nullable = false, length = 255)
  private String name;
  @Column(name = "descricao", nullable = false, length = 255)
  private String description;
  @Column(name = "local", nullable = false, length = 255)
  private String local;
  @Column(name = "id_empresa", nullable = false)
  private Long idEmployee;
}
