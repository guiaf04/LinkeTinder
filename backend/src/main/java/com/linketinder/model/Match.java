package com.linketinder.model;

import jakarta.persistence.*;

@Entity
@Table(name = "candidato_vaga_match")
public class Match {
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

  public Job getJob() {
    return job;
  }

  public void setJob(Job job) {
    this.job = job;
  }

  public boolean getCandidateLiked() {
    return candidateLiked;
  }

  public boolean isCandidateLiked() {
    return candidateLiked;
  }

  public void setCandidateLiked(boolean candidateLiked) {
    this.candidateLiked = candidateLiked;
  }

  public boolean getEmployerLiked() {
    return employerLiked;
  }

  public boolean isEmployerLiked() {
    return employerLiked;
  }

  public void setEmployerLiked(boolean employerLiked) {
    this.employerLiked = employerLiked;
  }

  public boolean getMatch() {
    return match;
  }

  public boolean isMatch() {
    return match;
  }

  public void setMatch(boolean match) {
    this.match = match;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "id_candidato", nullable = false)
  private Candidate candidate;
  @ManyToOne
  @JoinColumn(name = "id_vaga", nullable = true)
  private Job job;
  @Column(name = "candidato_liked")
  private boolean candidateLiked;
  @Column(name = "empresa_liked")
  private boolean employerLiked;
  @Column(name = "match")
  private boolean match;
}
