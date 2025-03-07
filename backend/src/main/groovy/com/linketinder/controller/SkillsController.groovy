package com.linketinder.controller

import com.linketinder.model.Skill
import com.linketinder.service.SkillsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/skills")
class SkillsController {

    @Autowired
    SkillsService skillService

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<Skill> findAll()  {
        return skillService.listSkills()
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Skill> createSkill(@RequestBody Skill skill){
        return ResponseEntity.status(HttpStatus.CREATED).body(skillService.addSkill(skill))
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Skill editSkill(@RequestBody Skill skill){
        return skillService.editSkill(skill)
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deleteSkill(@PathVariable("id") Long id){
        skillService.deleteSkill(id)
        return ResponseEntity.noContent().build()
    }
}
