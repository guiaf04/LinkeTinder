package com.linketinder.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "empresa")
class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id
    @Column(name= "nome", nullable = false, length = 100)
    String name
    @Column(name= "email", nullable = false, length = 100)
    String email
    @Column(name= "cnpj", nullable = false, length = 20)
    String cnpj
    @Column(name= "pais", nullable = false, length = 100)
    String country
    @Column(name= "cep", nullable = false, length = 20)
    String cep
    @Column(name= "descricao", nullable = false, length = 255)
    String description
    @Column(name= "senha", nullable = false, length = 40)
    String password

}
