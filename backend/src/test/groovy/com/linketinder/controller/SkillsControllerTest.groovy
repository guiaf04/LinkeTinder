package com.linketinder.controller

import com.linketinder.model.Skill
import com.linketinder.service.SkillsService
import org.springframework.http.HttpStatus
import spock.lang.Specification
import spock.lang.Subject

class SkillsControllerTest extends Specification {

    SkillsService skillsService = Mock()
    @Subject
    SkillsController skillsController = new SkillsController(skillService: skillsService)

    def "should find all skills"() {
        given:
        List<Skill> skills = [new Skill(id: 1L, name: "Java")]
        skillsService.listSkills() >> skills

        when:
        def result = skillsController.findAll()

        then:
        result == skills
    }

    def "should create a skill"() {
        given:
        Skill skill = new Skill(name: "Java")
        Skill savedSkill = new Skill(id: 1L, name: "Java")
        skillsService.addSkill(skill) >> savedSkill

        when:
        def response = skillsController.createSkill(skill)

        then:
        response.statusCode == HttpStatus.CREATED
        response.body == savedSkill
    }

    def "should edit a skill"() {
        given:
        Skill skill = new Skill(id: 1L, name: "Java")
        Skill updatedSkill = new Skill(id: 1L, name: "Advanced Java")
        skillsService.editSkill(skill) >> updatedSkill

        when:
        def result = skillsController.editSkill(skill)

        then:
        result == updatedSkill
    }

    def "should delete a skill"() {
        given:
        Long skillId = 1L

        when:
        def response = skillsController.deleteSkill(skillId)

        then:
        1 * skillsService.deleteSkill(skillId)
        response.statusCode == HttpStatus.NO_CONTENT
    }

    def "should return empty list when no skills found"() {
        given:
        skillsService.listSkills() >> []

        when:
        def result = skillsController.findAll()

        then:
        result.isEmpty()
    }

    def "should handle exception when creating skill with invalid data"() {
        given:
        Skill skill = new Skill(name: "")
        skillsService.addSkill(skill) >> { throw new IllegalArgumentException("Invalid skill data") }

        when:
        skillsController.createSkill(skill)

        then:
        thrown(IllegalArgumentException)
    }
}