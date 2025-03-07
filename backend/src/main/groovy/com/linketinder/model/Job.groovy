package com.linketinder.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name="vaga")
class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id
    @Column(name= "nome", nullable = false, length = 255)
    String name
    @Column(name= "descricao", nullable = false, length = 255)
    String description
    @Column(name= "local", nullable = false, length = 255)
    String local
    @Column(name= "id_empresa", nullable = false)
    Long idEmployee
}
