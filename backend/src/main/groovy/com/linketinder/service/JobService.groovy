package com.linketinder.service

import com.linketinder.exception.DuplicateEntity
import com.linketinder.exception.EntityNotFound
import com.linketinder.model.Job
import com.linketinder.repository.JobRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class JobService {

    @Autowired
    JobRepository jobRepository

    Job addJob(Job job){
        jobRepository.findById(job.getId()).orElseThrow(
                ()  ->  new DuplicateEntity("Bad request on job body!")
        )

        return jobRepository.save(job)
    }

    List<Job> listJobs(){
        return jobRepository.findAll()
    }

    Job editJob(Job job){
        Job edit = jobRepository.findById(job.getId()).orElseThrow(
                ()  ->  new EntityNotFound("No records for this id")
        )

        edit.name = job.name
        edit.description = job.description
        edit.local = job.local

        return jobRepository.save(edit)
    }

    void deleteJob(Long id){
        Job job = jobRepository.findById(id).orElseThrow(
                ()  ->  new EntityNotFound("No records for this id")
        )

        jobRepository.delete(job)
    }
}
