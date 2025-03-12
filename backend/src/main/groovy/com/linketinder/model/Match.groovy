package com.linketinder.model

import jakarta.persistence.*

@Entity
@Table(name = "candidato_vaga_match")
class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id
    @ManyToOne
    @JoinColumn(name = "id_candidato", nullable = false)
    Candidate candidate
    @ManyToOne
    @JoinColumn(name = "id_vaga", nullable = true)
    Job job
    @Column(name = "candidato_liked")
    boolean candidateLiked
    @Column(name= "empresa_liked")
    boolean employerLiked
    @Column(name = "match")
    boolean match
}
