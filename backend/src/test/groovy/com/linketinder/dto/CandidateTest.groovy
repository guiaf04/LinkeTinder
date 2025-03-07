package com.linketinder.dto

import com.linketinder.mapper.ObjectMapper
import com.linketinder.model.Candidate
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.time.LocalDate

@SpringBootTest
class CandidateTest extends Specification {

    List<CandidateDTO> listDtos = []
    List<Candidate> candidatoList = []
    Candidate candidateSingle
    CandidateDTO candidateSingleDTO

    void setup(){
        candidateSingle = new Candidate(
                id: 10,
                firstName: "Test",
                lastName: "Silva",
                birthday: LocalDate.of(2000, 01, 01),
                email: "test@gmail.com",
                cpf: "11111",
                country: "TestLand",
                cep: "020202",
                personalDescription: "this is a test!",
                password: "secret123"
        )

        candidatoList << candidateSingle
        candidatoList << new Candidate(
                id: 11,
                firstName: "Test",
                lastName: "Silva",
                birthday: LocalDate.of(2000, 01, 01),
                email: "test@gmail.com",
                cpf: "11111",
                country: "TestLand",
                cep: "020202",
                personalDescription: "this is a test!",
                password: "secret123"
        )

        candidateSingleDTO = new CandidateDTO(
                id: 10,
                firstName: "Test",
                lastName: "Silva",
                birthday: LocalDate.of(2000, 01, 01),
                cpf: "11111",
                country: "TestLand",
                cep: "020202",
                personalDescription: "this is a test!"
        )

        listDtos << candidateSingleDTO
        listDtos << new CandidateDTO(
                id: 11,
                firstName: "Test",
                lastName: "Silva",
                birthday: LocalDate.of(2000, 01, 01),
                cpf: "11111",
                country: "TestLand",
                cep: "020202",
                personalDescription: "this is a test!"
        )
    }

    void 'test cast to single dto mapper works'(){
        given:
        CandidateDTO expectedDTO

        when:
        expectedDTO = ObjectMapper.parseObject(candidateSingle, CandidateDTO.class)

        then:
        expectedDTO.id == 10
        expectedDTO.firstName == "Test"
        expectedDTO.lastName == "Silva"
        expectedDTO.birthday == LocalDate.of(2000, 01, 01)
        expectedDTO.cpf == "11111"
        expectedDTO.country == "TestLand"
        expectedDTO.cep == "020202"
        expectedDTO.personalDescription == "this is a test!"
    }

    void 'test cast to list dto mapper works'(){
        given:
        List<CandidateDTO> listDto

        when:
        listDto = ObjectMapper.parseListObject(candidatoList, CandidateDTO.class)

        then:
        listDto.size() == 2
        listDto[0].id == 10
        listDto[0].firstName == "Test"
        listDto[0].lastName == "Silva"
        listDto[0].birthday == LocalDate.of(2000, 01, 01)
        listDto[0].cpf == "11111"
        listDto[0].country == "TestLand"
        listDto[0].cep == "020202"
        listDto[0].personalDescription == "this is a test!"
        listDto[1].id == 11
    }

    void 'test cast dto to single candidate mapper works'(){
        given:
        Candidate candidato

        when:
        candidato = ObjectMapper.parseObject(candidateSingleDTO, Candidate.class)

        then:
        candidato.id == 10
        candidato.firstName == "Test"
        candidato.lastName == "Silva"
        candidato.birthday == LocalDate.of(2000, 01, 01)
        candidato.cpf == "11111"
        candidato.country == "TestLand"
        candidato.cep == "020202"
        candidato.personalDescription == "this is a test!"
        candidato.password == null
        candidato.email == null
    }

    void 'test cast dto to candidate list mapper works'(){
        given:
        List<Candidate> candidatesList

        when:
        candidatesList = ObjectMapper.parseListObject(listDtos, Candidate.class)

        then:
        candidatesList.size() == 2
        candidatesList[0].id == 10
        candidatesList[0].firstName == "Test"
        candidatesList[0].lastName == "Silva"
        candidatesList[0].birthday == LocalDate.of(2000, 01, 01)
        candidatesList[0].cpf == "11111"
        candidatesList[0].country == "TestLand"
        candidatesList[0].cep == "020202"
        candidatesList[0].personalDescription == "this is a test!"
        candidatesList[0].password == null
        candidatesList[0].email == null
        candidatesList[1].id == 11
    }
}
