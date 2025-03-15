package com.linketinder.controller

import com.linketinder.dto.CandidateDTO
import com.linketinder.dto.UnmatchedCandidateDTO
import com.linketinder.model.Candidate
import com.linketinder.service.CandidateService
import org.springframework.http.HttpStatus
import spock.lang.Specification

class CandidateControllerTest extends Specification {
    CandidateService candidateService = Mock()
    CandidateController candidateController = new CandidateController(candidateService)

    def "should find all candidates"() {
        given:
        Long employeeId = 1L
        List<UnmatchedCandidateDTO> candidates = [new UnmatchedCandidateDTO(id: 1L, skills: [])]
        candidateService.listCandidates(employeeId) >> candidates

        when:
        def result = candidateController.findAll(employeeId)

        then:
        result == candidates
    }

    def "should create a candidate"() {
        given:
        Candidate candidate = new Candidate(cpf: "12345678900", firstName: "John", lastName: "Doe")
        CandidateDTO candidateDTO = new CandidateDTO(cpf: "12345678900", firstName: "John", lastName: "Doe")
        candidateService.addCandidate(candidate) >> candidateDTO

        when:
        def response = candidateController.createCandidate(candidate)

        then:
        response.statusCode == HttpStatus.CREATED
        response.body == candidateDTO
    }

    def "should edit a candidate"() {
        given:
        Candidate candidate = new Candidate(cpf: "12345678900", firstName: "John", lastName: "Doe")
        CandidateDTO candidateDTO = new CandidateDTO(cpf: "12345678900", firstName: "John", lastName: "Doe")
        candidateService.editCandidate(candidate) >> candidateDTO

        when:
        def result = candidateController.editCandidate(candidate)

        then:
        result == candidateDTO
    }

    def "should delete a candidate"() {
        given:
        String cpf = "12345678900"

        when:
        def response = candidateController.deleteCandidate(cpf)

        then:
        1 * candidateService.deleteCandidate(cpf)
        response.statusCode == HttpStatus.NO_CONTENT
    }
}
