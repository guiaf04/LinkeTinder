package com.linketinder.controller

import com.linketinder.model.Job
import com.linketinder.service.JobService
import org.springframework.http.HttpStatus
import spock.lang.Specification
import spock.lang.Subject

class JobControllerTest extends Specification {

    JobService jobService = Mock()
    @Subject
    JobController jobController = new JobController(jobService: jobService)

    def "should find all jobs"() {
        given:
        List<Job> jobs = [new Job(id: 1L, name: "Developer", description: "Develops software", local: "Remote", idEmployee: 1L)]
        jobService.listJobs() >> jobs

        when:
        def result = jobController.findAll()

        then:
        result == jobs
    }

    def "should create a job"() {
        given:
        Job job = new Job(name: "Developer", description: "Develops software", local: "Remote", idEmployee: 1L)
        Job savedJob = new Job(id: 1L, name: "Developer", description: "Develops software", local: "Remote", idEmployee: 1L)
        jobService.addJob(job) >> savedJob

        when:
        def response = jobController.createJob(job)

        then:
        response.statusCode == HttpStatus.CREATED
        response.body == savedJob
    }

    def "should edit a job"() {
        given:
        Job job = new Job(id: 1L, name: "Developer", description: "Develops software", local: "Remote", idEmployee: 1L)
        Job updatedJob = new Job(id: 1L, name: "Senior Developer", description: "Develops software", local: "Remote", idEmployee: 1L)
        jobService.editJob(job) >> updatedJob

        when:
        def result = jobController.editJob(job)

        then:
        result == updatedJob
    }

    def "should delete a job"() {
        given:
        Long jobId = 1L

        when:
        def response = jobController.deleteJob(jobId)

        then:
        1 * jobService.deleteJob(jobId)
        response.statusCode == HttpStatus.NO_CONTENT
    }

    def "should return empty list when no jobs found"() {
        given:
        jobService.listJobs() >> []

        when:
        def result = jobController.findAll()

        then:
        result.isEmpty()
    }

    def "should handle exception when creating job with invalid data"() {
        given:
        Job job = new Job(name: "", description: "", local: "", idEmployee: 1L)
        jobService.addJob(job) >> { throw new IllegalArgumentException("Invalid job data") }

        when:
        jobController.createJob(job)

        then:
        thrown(IllegalArgumentException)
    }
}