package com.linketinder.controller


import com.linketinder.model.Skill
import com.linketinder.service.JobSkillService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/jobs/{idJob}/skill")
class JobSkillController {

    JobSkillService jobSkillService

    JobSkillController(JobSkillService jobSkillService) {
        this.jobSkillService = jobSkillService
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<Skill> getAllJobSkills(@PathVariable("idJob") Long idJob){
        jobSkillService.findAllJobSkills(idJob)
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<Skill> addJobSkill(@PathVariable("idJob") Long idJob, @RequestBody List<Skill> skills){
        jobSkillService.addJobSkills(idJob, skills)
    }

}
