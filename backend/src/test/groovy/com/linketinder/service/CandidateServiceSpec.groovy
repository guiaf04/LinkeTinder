package com.linketinder.service

import com.linketinder.dto.CandidateDTO
import com.linketinder.dto.UnmatchedCandidateDTO
import com.linketinder.exception.DuplicateEntity
import com.linketinder.exception.EntityNotFound
import com.linketinder.model.Candidate
import com.linketinder.model.Skill
import com.linketinder.repository.CandidateRepository
import com.linketinder.repository.CandidateSkillRepository
import com.linketinder.repository.MatchRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class CandidateServiceSpec extends Specification {

    @Autowired
    CandidateRepository candidateRepository = Mock(CandidateRepository)
    CandidateSkillRepository candidateSkillRepository = Mock(CandidateSkillRepository)
    MatchRepository matchRepository = Mock(MatchRepository)

    @Autowired
    CandidateService candidateService = new CandidateService(candidateRepository, matchRepository, candidateSkillRepository)

    def "should add a candidate"() {
        given:
        Candidate candidate = new Candidate(cpf: "123456789", firstName: "John", lastName: "Doe")
        CandidateDTO candidateDTO = new CandidateDTO(cpf: "123456789", firstName: "John", lastName: "Doe")
        candidateRepository.getElementByCpf(candidate.cpf) >> null
        candidateRepository.save(candidate) >> candidate

        when:
        def result = candidateService.addCandidate(candidate)

        then:
        result == candidateDTO
    }

    def "should not add a duplicate candidate"() {
        given:
        Candidate candidate = new Candidate(cpf: "123456789", firstName: "John", lastName: "Doe")
        candidateRepository.getElementByCpf(candidate.cpf) >> candidate

        when:
        candidateService.addCandidate(candidate)

        then:
        thrown(DuplicateEntity)
    }

    def "should list all candidates"() {
        given:

        Skill skill = new Skill(name: "Java")
        List<Candidate> candidates = [
                new Candidate(cpf: "123456789", firstName: "John", lastName: "Doe", id: 1),
                new Candidate(cpf: "987654321", firstName: "Jane", lastName: "Smith", id: 2, personalDescription: "test2", country: "Brazil", cep: "123456789")]

        List<UnmatchedCandidateDTO> candidateDTOs = [
                new CandidateDTO(cpf: "123456789", firstName: "John", lastName: "Doe", id: 1, skills: [skill]),
                new UnmatchedCandidateDTO(id: 2, personalDescription: "test2", country: "Brazil", cep: "123456789", skills: [skill])
        ]

        candidateRepository.findAll() >> candidates
        candidateSkillRepository.findAllSkillByCandidateId(1) >> [skill]
        candidateSkillRepository.findAllSkillByCandidateId(2) >> [skill]
        matchRepository.existsByCandidateIdAndEmployeeId(1, 0) >> true
        matchRepository.existsByCandidateIdAndEmployeeId(2, 0) >> false

        when:
        def result = candidateService.listCandidates(0)

        then:
        result == candidateDTOs
    }

    def "should edit a candidate"() {
        given:
        Candidate candidate = new Candidate(cpf: "123456789", firstName: "John", lastName: "Doe")
        Candidate edit = new Candidate(cpf: "123456789", firstName: "Jane", lastName: "Smith")
        CandidateDTO candidateDTO = new CandidateDTO(cpf: "123456789", firstName: "John", lastName: "Doe")
        candidateRepository.getElementByCpf(candidate.cpf) >> edit
        candidateRepository.save(edit) >> candidate

        when:
        def result = candidateService.editCandidate(candidate)

        then:
        result == candidateDTO
    }

    def "should not edit a non-existent candidate"() {
        given:
        Candidate candidate = new Candidate(cpf: "123456789", firstName: "John", lastName: "Doe")
        candidateRepository.getElementByCpf(candidate.cpf) >> null

        when:
        candidateService.editCandidate(candidate)

        then:
        thrown(EntityNotFound)
    }

    def "should delete a candidate"() {
        given:
        Candidate candidate = new Candidate(cpf: "123456789", firstName: "John", lastName: "Doe")
        candidateRepository.getElementByCpf(candidate.cpf) >> candidate

        when:
        candidateService.deleteCandidate(candidate.cpf)

        then:
        1 * candidateRepository.delete(candidate)
    }

    def "should not delete a non-existent candidate"() {
        given:
        String cpf = "123456789"
        candidateRepository.getElementByCpf(cpf) >> null

        when:
        candidateService.deleteCandidate(cpf)

        then:
        thrown(EntityNotFound)
    }

}