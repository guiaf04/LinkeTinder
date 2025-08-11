package com.linketinder.model;

import jakarta.persistence.*;

@Entity
@Table(name = "candidato_competencia")
public class CandidateSkill {
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Candidate getCandidate() {
    return candidate;
  }

  public void setCandidate(Candidate candidate) {
    this.candidate = candidate;
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
  @JoinColumn(name = "id_candidato", nullable = false)
  private Candidate candidate;
  @ManyToOne
  @JoinColumn(name = "id_competencia", nullable = false)
  private Skill skill;
}
