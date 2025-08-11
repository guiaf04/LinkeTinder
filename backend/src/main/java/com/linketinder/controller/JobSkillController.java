package com.linketinder.controller;

import com.linketinder.model.Skill;
import com.linketinder.service.JobSkillService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs/{idJob}/skill")
public class JobSkillController {
  public JobSkillController(JobSkillService jobSkillService) {
    this.jobSkillService = jobSkillService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Skill> getAllJobSkills(@PathVariable("idJob") Long idJob) {
    return jobSkillService.findAllJobSkills(idJob);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Skill> addJobSkill(@PathVariable("idJob") Long idJob, @RequestBody List<Skill> skills) {
    return jobSkillService.addJobSkills(idJob, skills);
  }

  public JobSkillService getJobSkillService() {
    return jobSkillService;
  }

  public void setJobSkillService(JobSkillService jobSkillService) {
    this.jobSkillService = jobSkillService;
  }

  private JobSkillService jobSkillService;
}
