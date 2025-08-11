package com.linketinder.controller;

import com.linketinder.model.Skill;
import com.linketinder.service.SkillsService;
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
@RequestMapping("/skills")
public class SkillsController {
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Skill> findAll() {
    return skillService.listSkills();
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
    return ResponseEntity.status(HttpStatus.CREATED).body(skillService.addSkill(skill));
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Skill editSkill(@RequestBody Skill skill) {
    return skillService.editSkill(skill);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> deleteSkill(@PathVariable("id") Long id) {
    skillService.deleteSkill(id);
    return ResponseEntity.noContent().build();
  }

  public SkillsService getSkillService() {
    return skillService;
  }

  public void setSkillService(SkillsService skillService) {
    this.skillService = skillService;
  }

  @Autowired
  private SkillsService skillService;
}
