package com.linketinder.controller;

import com.linketinder.dto.CandidateDTO;
import com.linketinder.dto.UnmatchedCandidateDTO;
import com.linketinder.model.Candidate;
import com.linketinder.service.CandidateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {
  public CandidateController(CandidateService candidateService) {
    this.candidateService = candidateService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<UnmatchedCandidateDTO> findAll(@RequestBody(required = false) Long employeeId) {
    if (employeeId == null) {
      employeeId = 0L;
    }

    return candidateService.listCandidates(employeeId);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CandidateDTO> createCandidate(@RequestBody Candidate candidate) {
    return ResponseEntity.status(HttpStatus.CREATED).body(candidateService.addCandidate(candidate));
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public CandidateDTO editCandidate(@RequestBody Candidate candidate) {
    return candidateService.editCandidate(candidate);
  }

  @DeleteMapping(value = "/{cpf}")
  public ResponseEntity<?> deleteCandidate(@PathVariable("cpf") String cpf) {
    candidateService.deleteCandidate(cpf);
    return ResponseEntity.noContent().build();
  }

  public CandidateService getCandidateService() {
    return candidateService;
  }

  public void setCandidateService(CandidateService candidateService) {
    this.candidateService = candidateService;
  }

  private CandidateService candidateService;
}
