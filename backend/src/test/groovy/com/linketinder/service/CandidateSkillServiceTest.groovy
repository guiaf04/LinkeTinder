package com.linketinder.service

import com.linketinder.exception.DuplicateEntity
import com.linketinder.exception.EntityNotFound
import com.linketinder.model.Candidate
import com.linketinder.model.CandidateSkill
import com.linketinder.model.Skill
import com.linketinder.repository.CandidateRepository
import com.linketinder.repository.CandidateSkillRepository
import com.linketinder.repository.SkillRepository
import spock.lang.Specification

class CandidateSkillServiceTest extends Specification {

    CandidateRepository candidateRepository = Mock()
    SkillRepository skillRepository = Mock()
    CandidateSkillRepository candidateSkillRepository = Mock()
    CandidateSkillService candidateSkillService = new CandidateSkillService(candidateRepository, skillRepository, candidateSkillRepository)

    void setup() {
    }

    def "should find all candidate skills"() {
        given:
        Long candidateId = 1L
        Skill skill = new Skill(name: "Java")
        CandidateSkill candidateSkill = new CandidateSkill(candidate: new Candidate(id: candidateId), skill: skill)
        candidateSkillRepository.findByCandidateId(candidateId) >> [candidateSkill]

        when:
        def result = candidateSkillService.findAllCandidateSkills(candidateId)

        then:
        result == [skill]
    }

    def "should throw exception when no candidate skills found"() {
        given:
        Long candidateId = 1L
        candidateSkillRepository.findByCandidateId(candidateId) >> []

        when:
        candidateSkillService.findAllCandidateSkills(candidateId)

        then:
        thrown(EntityNotFound)
    }

    def "should add candidate skills"() {
        given:
        Long candidateId = 1L
        Candidate candidate = new Candidate(id: candidateId)
        Skill skill = new Skill(name: "Java")
        candidateRepository.findById(candidateId) >> Optional.of(candidate)
        skillRepository.findByName(skill.name) >> Optional.of(skill)
        candidateSkillRepository.findByCandidateAndSkill(candidate, skill) >> Optional.empty()

        when:
        def result = candidateSkillService.addCandidateSkills(candidateId, [skill])

        then:
        result == [skill]
    }

    def "should throw exception when candidate not found"() {
        given:
        Long candidateId = 1L
        Skill skill = new Skill(name: "Java")
        candidateRepository.findById(candidateId) >> Optional.empty()

        when:
        candidateSkillService.addCandidateSkills(candidateId, [skill])

        then:
        thrown(EntityNotFound)
    }

    def "should throw exception when skill not found"() {
        given:
        Long candidateId = 1L
        Candidate candidate = new Candidate(id: candidateId)
        Skill skill = new Skill(name: "Java")
        candidateRepository.findById(candidateId) >> Optional.of(candidate)
        skillRepository.findByName(skill.name) >> Optional.empty()

        when:
        candidateSkillService.addCandidateSkills(candidateId, [skill])

        then:
        thrown(EntityNotFound)
    }

    def "should throw exception when skill already added to candidate"() {
        given:
        Long candidateId = 1L
        Candidate candidate = new Candidate(id: candidateId)
        Skill skill = new Skill(name: "Java")
        CandidateSkill candidateSkill = new CandidateSkill(candidate: candidate, skill: skill)
        candidateRepository.findById(candidateId) >> Optional.of(candidate)
        skillRepository.findByName(skill.name) >> Optional.of(skill)
        candidateSkillRepository.findByCandidateAndSkill(candidate, skill) >> Optional.of(candidateSkill)

        when:
        candidateSkillService.addCandidateSkills(candidateId, [skill])

        then:
        thrown(DuplicateEntity)
    }
}
