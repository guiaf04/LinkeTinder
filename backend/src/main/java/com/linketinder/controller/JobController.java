package com.linketinder.controller;

import com.linketinder.model.Job;
import com.linketinder.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Job> findAll() {
    return jobService.listJobs();
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Job> createJob(@RequestBody Job job) {
    return ResponseEntity.status(HttpStatus.CREATED).body(jobService.addJob(job));
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Job editJob(@RequestBody Job job) {
    return jobService.editJob(job);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> deleteJob(@PathVariable("id") Long id) {
    jobService.deleteJob(id);
    return ResponseEntity.noContent().build();
  }

  public JobService getJobService() {
    return jobService;
  }

  public void setJobService(JobService jobService) {
    this.jobService = jobService;
  }

  @Autowired
  private JobService jobService;
}
