package com.linketinder.service

import com.linketinder.exception.DuplicateEntity
import com.linketinder.exception.EntityNotFound
import com.linketinder.model.Job
import com.linketinder.repository.JobRepository
import spock.lang.Specification

class JobServiceTest extends Specification {
    JobRepository jobRepository = Mock()

    JobService jobService = new JobService(jobRepository)

    def "should add a job"() {
        given:
        Job job = new Job(id: 1L, name: "Developer", description: "Develop software", local: "Remote")
        jobRepository.existsByNameAndDescriptionAndIdEmployeeAndLocal(
                job.name,
                job.description,
                job.idEmployee,
                job.local
        ) >> Optional.empty()
        jobRepository.save(job) >> job

        when:
        def result = jobService.addJob(job)

        then:
        result == job
    }

    def "should not add a duplicate job"() {
        given:
        Job job = new Job(id: 1L, name: "Developer", description: "Develop software", local: "Remote")
        jobRepository.existsByNameAndDescriptionAndIdEmployeeAndLocal(
                job.name,
                job.description,
                job.idEmployee,
                job.local
        ) >> Optional.of(job)

        when:
        jobService.addJob(job)

        then:
        thrown(DuplicateEntity)
    }

    def "should list all jobs"() {
        given:
        List<Job> jobs = [new Job(id: 1L, name: "Developer", description: "Develop software", local: "Remote")]
        jobRepository.findAll() >> jobs

        when:
        def result = jobService.listJobs()

        then:
        result == jobs
    }

    def "should edit a job"() {
        given:
        Job job = new Job(id: 1L, name: "Developer", description: "Develop software", local: "Remote")
        Job edit = new Job(id: 1L, name: "Senior Developer", description: "Develop and design software", local: "Remote")
        jobRepository.findById(job.id) >> Optional.of(job)
        jobRepository.save(job) >> edit

        when:
        def result = jobService.editJob(edit)

        then:
        result == edit
    }

    def "should not edit a non-existent job"() {
        given:
        Job job = new Job(id: 1L, name: "Developer", description: "Develop software", local: "Remote")
        jobRepository.findById(job.id) >> Optional.empty()

        when:
        jobService.editJob(job)

        then:
        thrown(EntityNotFound)
    }

    def "should delete a job"() {
        given:
        Job job = new Job(id: 1L, name: "Developer", description: "Develop software", local: "Remote")
        jobRepository.findById(job.id) >> Optional.of(job)

        when:
        jobService.deleteJob(job.id)

        then:
        1 * jobRepository.delete(job)
    }

    def "should not delete a non-existent job"() {
        given:
        Long jobId = 1L
        jobRepository.findById(jobId) >> Optional.empty()

        when:
        jobService.deleteJob(jobId)

        then:
        thrown(EntityNotFound)
    }
}
