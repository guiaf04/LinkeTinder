package com.linketinder.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name="candidato_competencia")
class CandidateSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @ManyToOne
    @JoinColumn(name = "id_candidato", nullable = false)
    Candidate candidate

    @ManyToOne
    @JoinColumn(name = "id_competencia", nullable = false)
    Skill skill
}
