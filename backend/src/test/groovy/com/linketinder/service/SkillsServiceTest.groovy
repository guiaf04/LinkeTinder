package com.linketinder.service

import com.linketinder.exception.DuplicateEntity
import com.linketinder.exception.EntityNotFound
import com.linketinder.model.Skill
import com.linketinder.repository.SkillRepository
import spock.lang.Specification

class SkillsServiceTest extends Specification {
    SkillRepository skillRepository = Mock()
    SkillsService skillsService = new SkillsService(skillRepository)

    def "should add a skill"() {
        given:
        Skill skill = new Skill(name: "Java")
        skillRepository.findByName(skill.name) >> Optional.empty()
        skillRepository.save(skill) >> skill

        when:
        def result = skillsService.addSkill(skill)

        then:
        result == skill
    }

    def "should not add a duplicate skill"() {
        given:
        Skill skill = new Skill(name: "Java")
        skillRepository.findByName(skill.name) >> Optional.of(skill)

        when:
        skillsService.addSkill(skill)

        then:
        thrown(DuplicateEntity)
    }

    def "should list all skills"() {
        given:
        List<Skill> skills = [new Skill(name: "Java")]
        skillRepository.findAll() >> skills

        when:
        def result = skillsService.listSkills()

        then:
        result == skills
    }

    def "should edit a skill"() {
        given:
        Skill skill = new Skill(id: 1L, name: "Java")
        Skill edit = new Skill(id: 1L, name: "Kotlin")
        skillRepository.findById(skill.id) >> Optional.of(skill)
        skillRepository.save(skill) >> edit

        when:
        def result = skillsService.editSkill(edit)

        then:
        result == edit
    }

    def "should not edit a non-existent skill"() {
        given:
        Skill skill = new Skill(id: 1L, name: "Java")
        skillRepository.findById(skill.id) >> Optional.empty()

        when:
        skillsService.editSkill(skill)

        then:
        thrown(EntityNotFound)
    }

    def "should delete a skill"() {
        given:
        Skill skill = new Skill(id: 1L, name: "Java")
        skillRepository.findById(skill.id) >> Optional.of(skill)

        when:
        skillsService.deleteSkill(skill.id)

        then:
        1 * skillRepository.delete(skill)
    }

    def "should not delete a non-existent skill"() {
        given:
        Long skillId = 1L
        skillRepository.findById(skillId) >> Optional.empty()

        when:
        skillsService.deleteSkill(skillId)

        then:
        thrown(EntityNotFound)
    }
}
