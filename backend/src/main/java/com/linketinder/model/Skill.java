package com.linketinder.model;

import jakarta.persistence.*;

@Entity
@Table(name = "competencia")
public class Skill {
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

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "nome", nullable = false, length = 100)
  private String name;
}
