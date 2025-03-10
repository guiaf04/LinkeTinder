package com.linketinder.model

import jakarta.persistence.*

@Entity
@Table(name="vaga_competencia")
class JobSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @ManyToOne
    @JoinColumn(name = "id_vaga", nullable = false)
    Job job

    @ManyToOne
    @JoinColumn(name = "id_competencia", nullable = false)
    Skill skill
}
