package com.linketinder.service

import com.linketinder.dto.CandidateDTO
import com.linketinder.exception.DuplicateEntity
import com.linketinder.exception.EntityNotFound
import com.linketinder.model.Candidate
import com.linketinder.repository.CandidateRepository
import org.springframework.stereotype.Service

import static com.linketinder.mapper.ObjectMapper.parseListObject
import static com.linketinder.mapper.ObjectMapper.parseObject

@Service
class CandidateService {

    CandidateRepository candidateRepository

    CandidateService(CandidateRepository candidateRepository){
        this.candidateRepository = candidateRepository
    }

    CandidateDTO addCandidate(Candidate candidato){
        if (candidateRepository.getElementByCpf(candidato.getCpf())){
            throw new DuplicateEntity("Bad request on candidate body!")
        }

        return parseObject(candidateRepository.save(candidato), CandidateDTO.class)
    }

    List<CandidateDTO> listCandidates(){
        return parseListObject(candidateRepository.findAll(), CandidateDTO.class)
    }

    CandidateDTO editCandidate(Candidate candidato){
        Candidate edit = candidateRepository.getElementByCpf(candidato.getCpf())
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

        return parseObject(candidateRepository.save(edit), CandidateDTO.class)
    }

    void deleteCandidate(String cpf){
        Candidate candidato = candidateRepository.getElementByCpf(cpf)
        if (!candidato){
            throw new EntityNotFound("No records for this CPF!")
        }

        candidateRepository.delete(candidato)
    }

}
