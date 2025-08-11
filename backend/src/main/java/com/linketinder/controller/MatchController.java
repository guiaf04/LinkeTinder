package com.linketinder.controller;

import com.linketinder.dto.MatchDTO;
import com.linketinder.dto.UnmatchedCandidateDTO;
import com.linketinder.model.Match;
import com.linketinder.service.MatchService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
public class MatchController {

  final MatchService matchService;

  public MatchController(MatchService matchService) {
    this.matchService = matchService;
  }

  @PostMapping(
          value = "/candidate",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public String likeCandidate(@RequestBody MatchDTO matchDTO) {
    matchService.candidateLike(matchDTO);
    return "Candidate liked";
  }

  @PostMapping(
          value =  "/employee",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public String likeEmployee(@RequestBody MatchDTO matchDTO) {
    matchService.employeeLike(matchDTO);
    return "Employee liked";
  }

  @GetMapping(
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public Match findMatch(@RequestBody MatchDTO matchDTO) {
    return matchService.findMatch(matchDTO);
  }

  @GetMapping(
          value = "/{employeeId}",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public List<UnmatchedCandidateDTO> findPossibleMatches(@PathVariable("employeeId") Long employeeId) {
    return matchService.findPotentialMatches(employeeId);

  }
  @PostMapping(
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public String createMatch(@RequestBody MatchDTO matchDTO) {
    matchService.createMatch(matchDTO);
    return "Match created";
  }
}
