package com.linketinder.controller;

import com.linketinder.model.Skill;
import com.linketinder.service.CandidateSkillService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates/{idCandidate}/skill")
public class CandidateSkillController {
  public CandidateSkillController(CandidateSkillService candidateSkillService) {
    this.candidateSkillService = candidateSkillService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Skill> getAllCandidateSkills(@PathVariable("idCandidate") Long idCandidate) {
    return candidateSkillService.findAllCandidateSkills(idCandidate);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Skill> addCandidateSkill(@PathVariable("idCandidate") Long idCandidate, @RequestBody List<Skill> skills) {
    return candidateSkillService.addCandidateSkills(idCandidate, skills);
  }

  public CandidateSkillService getCandidateSkillService() {
    return candidateSkillService;
  }

  public void setCandidateSkillService(CandidateSkillService candidateSkillService) {
    this.candidateSkillService = candidateSkillService;
  }

  private CandidateSkillService candidateSkillService;
}
