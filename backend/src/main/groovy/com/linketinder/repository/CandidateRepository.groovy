package com.linketinder.repository

import com.linketinder.model.Candidato
import org.springframework.data.jpa.repository.JpaRepository

interface CandidateRepository extends JpaRepository <Candidato, Long>{
    Candidato getElementByCpf(String cpf)

}