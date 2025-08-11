package com.linketinder.service;

import com.linketinder.exception.DuplicateEntity;
import com.linketinder.exception.EntityNotFound;
import com.linketinder.model.Job;
import com.linketinder.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(final JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job addJob(final Job job) {
        if (jobRepository.existsByNameAndDescriptionAndIdEmployeeAndLocal(
                job.getName(),
                job.getDescription(),
                job.getIdEmployee(),
                job.getLocal()
        )) {
            throw new DuplicateEntity("Job with the same name, description, and company already exists!");
        }

        return jobRepository.save(job);
    }

    public List<Job> listJobs() {
        return jobRepository.findAll();
    }

    public Job editJob(final Job job) {
        final Job edit = jobRepository.findById(job.getId()).orElseThrow(
                () -> new EntityNotFound("No records for this id")
        );

        edit.setName(job.getName());
        edit.setDescription(job.getDescription());
        edit.setLocal(job.getLocal());

        return jobRepository.save(edit);
    }

    public void deleteJob(final Long id) {
        final Job job = jobRepository.findById(id).orElseThrow(
                () -> new EntityNotFound("No records for this id")
        );

        jobRepository.delete(job);
    }
}
