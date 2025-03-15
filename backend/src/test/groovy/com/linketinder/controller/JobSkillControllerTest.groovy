package com.linketinder.controller

import com.linketinder.exception.EntityNotFound
import com.linketinder.model.Skill
import com.linketinder.service.JobSkillService
import spock.lang.Specification
import spock.lang.Subject

class JobSkillControllerTest extends Specification {

    JobSkillService jobSkillService = Mock()
    @Subject
    JobSkillController jobSkillController = new JobSkillController(jobSkillService)

    def "should get all job skills"() {
        given:
        Long idJob = 1L
        List<Skill> skills = [new Skill(name: "Java")]
        jobSkillService.findAllJobSkills(idJob) >> skills

        when:
        def result = jobSkillController.getAllJobSkills(idJob)

        then:
        result == skills
    }

    def "should add job skills"() {
        given:
        Long idJob = 1L
        List<Skill> skills = [new Skill(name: "Java")]
        jobSkillService.addJobSkills(idJob, skills) >> skills

        when:
        def result = jobSkillController.addJobSkill(idJob, skills)

        then:
        result == skills
    }

    def "should return empty list when no skills found for job"() {
        given:
        Long idJob = 1L
        jobSkillService.findAllJobSkills(idJob) >> []

        when:
        def result = jobSkillController.getAllJobSkills(idJob)

        then:
        result.isEmpty()
    }

    def "should handle exception when adding skills to non-existent job"() {
        given:
        Long idJob = 1L
        List<Skill> skills = [new Skill(name: "Java")]
        jobSkillService.addJobSkills(idJob, skills) >> { throw new EntityNotFound("") }

        when:
        jobSkillController.addJobSkill(idJob, skills)

        then:
        thrown(EntityNotFound)
    }
}