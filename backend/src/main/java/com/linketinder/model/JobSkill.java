package com.linketinder.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vaga_competencia")
public class JobSkill {
  public JobSkill() {

  }

  public Long getId() {
    return id;
  }

  public JobSkill(Skill skill, Job job) {
    this.skill = skill;
    this.job = job;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Job getJob() {
    return job;
  }

  public void setJob(Job job) {
    this.job = job;
  }

  public Skill getSkill() {
    return skill;
  }

  public void setSkill(Skill skill) {
    this.skill = skill;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "id_vaga", nullable = false)
  private Job job;
  @ManyToOne
  @JoinColumn(name = "id_competencia", nullable = false)
  private Skill skill;
}
