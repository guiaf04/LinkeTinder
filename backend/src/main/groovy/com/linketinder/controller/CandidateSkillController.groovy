package com.linketinder.controller


import com.linketinder.model.Skill
import com.linketinder.service.CandidateSkillService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/candidates/{idCandidate}/skill")
class CandidateSkillController {

    CandidateSkillService candidateSkillService

    CandidateSkillController(CandidateSkillService candidateSkillService) {
        this.candidateSkillService = candidateSkillService
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<Skill> getAllCandidateSkills(@PathVariable("idCandidate") Long idCandidate){
        candidateSkillService.findAllCandidateSkills(idCandidate)
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<Skill> addCandidateSkill(@PathVariable("idCandidate") Long idCandidate, @RequestBody List<Skill> skills){
        return candidateSkillService.addCandidateSkills(idCandidate, skills)
    }

}
