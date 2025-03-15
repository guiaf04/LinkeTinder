package com.linketinder.controller

import com.linketinder.exception.EntityNotFound
import com.linketinder.model.Skill
import com.linketinder.service.CandidateSkillService
import spock.lang.Specification
import spock.lang.Subject

class CandidateSkillControllerTest extends Specification {

    CandidateSkillService candidateSkillService = Mock()
    @Subject
    CandidateSkillController candidateSkillController = new CandidateSkillController(candidateSkillService)

    def "should get all candidate skills"() {
        given:
        Long idCandidate = 1L
        List<Skill> skills = [new Skill(name: "Java")]
        candidateSkillService.findAllCandidateSkills(idCandidate) >> skills

        when:
        def result = candidateSkillController.getAllCandidateSkills(idCandidate)

        then:
        result == skills
    }

    def "should add candidate skills"() {
        given:
        Long idCandidate = 1L
        List<Skill> skills = [new Skill(name: "Java")]
        candidateSkillService.addCandidateSkills(idCandidate, skills) >> skills

        when:
        def result = candidateSkillController.addCandidateSkill(idCandidate, skills)

        then:
        result == skills
    }

    def "should return empty list when no skills found for candidate"() {
        given:
        Long idCandidate = 1L
        candidateSkillService.findAllCandidateSkills(idCandidate) >> []

        when:
        def result = candidateSkillController.getAllCandidateSkills(idCandidate)

        then:
        result.isEmpty()
    }

    def "should handle exception when adding skills to non-existent candidate"() {
        given:
        Long idCandidate = 1L
        List<Skill> skills = [new Skill(name: "Java")]
        candidateSkillService.addCandidateSkills(idCandidate, skills) >> { throw new EntityNotFound() }

        when:
        candidateSkillController.addCandidateSkill(idCandidate, skills)

        then:
        thrown(EntityNotFound)
    }
}