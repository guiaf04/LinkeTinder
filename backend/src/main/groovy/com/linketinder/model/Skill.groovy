package com.linketinder.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name="competencia")
class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id
    @Column(name= "nome", nullable = false, length = 100)
    String name
}