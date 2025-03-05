package com.linketinder.service

import com.linketinder.exception.DuplicateEntity
import com.linketinder.exception.EntityNotFound
import com.linketinder.model.Candidato
import com.linketinder.model.Competencia
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.linketinder.repository.CandidateRepository

import java.sql.SQLException

@Service
class CandidateService {

    @Autowired
    CandidateRepository candidateRepository

    Candidato addCandidate(Candidato candidato){
        if (candidateRepository.getElementByCpf(candidato.getCpf())){
            throw new DuplicateEntity("Bad request on candidate body!")
        }

        return candidateRepository.save(candidato)
    }

    List<Candidato> listCandidates(){
        return candidateRepository.findAll()
    }

    boolean addSkills(List<Competencia> competencias, int candidatoID){
        try {
            competencias.forEach {
                candidateRepository.insertSkill((Competencia) it, candidatoID)
            }
            return true
        }catch (SQLException e){
            return false
        }
    }

    Candidato editCandidate(Candidato candidato){
        Candidato edit = candidateRepository.getElementByCpf(candidato.getCpf())
        if (!edit){
            throw new EntityNotFound("No records for this CPF!")
        }

        edit.firstName = candidato.firstName
        edit.lastName = candidato.lastName
        edit.birthday = candidato.birthday
        edit.email = candidato.email
        edit.country = candidato.country
        edit.cep = candidato.cep
        edit.personalDescription = candidato.personalDescription
        edit.password = candidato.password

        candidateRepository.save(edit)
    }

    void deleteCandidate(String cpf){
        Candidato candidato = candidateRepository.getElementByCpf(cpf)
        if (!candidato){
            throw new EntityNotFound("No records for this CPF!")
        }

        candidateRepository.delete(candidato)
    }
}
