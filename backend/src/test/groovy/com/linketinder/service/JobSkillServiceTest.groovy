package com.linketinder.service

import com.linketinder.exception.EntityNotFound
import com.linketinder.model.Job
import com.linketinder.model.JobSkill
import com.linketinder.model.Skill
import com.linketinder.repository.JobRepository
import com.linketinder.repository.JobSkillRepository
import com.linketinder.repository.SkillRepository
import spock.lang.Specification

class JobSkillServiceTest extends Specification {

    final JobRepository jobRepository = Mock()
    final SkillRepository skillRepository = Mock()
    final JobSkillRepository jobSkillRepository = Mock()

    final JobSkillService jobSkillService = new JobSkillService(jobRepository, skillRepository, jobSkillRepository)

    def "should find all job skills"() {
        given:
        Long jobId = 1L
        Skill skill = new Skill(name: "Java")
        JobSkill jobSkill = new JobSkill(job: new Job(id: jobId), skill: skill)
        jobSkillRepository.findByJobId(jobId) >> [jobSkill]

        when:
        def result = jobSkillService.findAllJobSkills(jobId)

        then:
        result == [skill]
    }

    def "should throw exception when no job skills found"() {
        given:
        Long jobId = 1L
        jobSkillRepository.findByJobId(jobId) >> []

        when:
        jobSkillService.findAllJobSkills(jobId)

        then:
        thrown(EntityNotFound)
    }

    def "should add job skills"() {
        given:
        Long jobId = 1L
        Job job = new Job(id: jobId)
        Skill skill = new Skill(name: "Java")
        jobRepository.findById(jobId) >> Optional.of(job)
        skillRepository.findByName(skill.name) >> Optional.of(skill)
        jobSkillRepository.findByJobAndSkill(job, skill) >> Optional.empty()

        when:
        def result = jobSkillService.addJobSkills(jobId, [skill])

        then:
        result == [skill]
    }

    def "should throw exception when job not found"() {
        given:
        Long jobId = 1L
        Skill skill = new Skill(name: "Java")
        jobRepository.findById(jobId) >> Optional.empty()

        when:
        jobSkillService.addJobSkills(jobId, [skill])

        then:
        thrown(EntityNotFound)
    }

    def "should throw exception when skill not found"() {
        given:
        Long jobId = 1L
        Job job = new Job(id: jobId)
        Skill skill = new Skill(name: "Java")
        jobRepository.findById(jobId) >> Optional.of(job)
        skillRepository.findByName(skill.name) >> Optional.empty()

        when:
        jobSkillService.addJobSkills(jobId, [skill])

        then:
        thrown(EntityNotFound)
    }

    def "should throw exception when skill already added to job"() {
        given:
        Long jobId = 1L
        Job job = new Job(id: jobId)
        Skill skill = new Skill(name: "Java")
        JobSkill jobSkill = new JobSkill(job: job, skill: skill)
        jobRepository.findById(jobId) >> Optional.of(job)
        skillRepository.findByName(skill.name) >> Optional.of(skill)
        jobSkillRepository.findByJobAndSkill(job, skill) >> Optional.of(jobSkill)

        when:
        jobSkillService.addJobSkills(jobId, [skill])

        then:
        thrown(EntityNotFound)
    }
}
