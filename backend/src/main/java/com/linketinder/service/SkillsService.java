package com.linketinder.service;

import com.linketinder.exception.DuplicateEntity;
import com.linketinder.exception.EntityNotFound;
import com.linketinder.model.Skill;
import com.linketinder.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillsService {

    private final SkillRepository skillRepository;

    public SkillsService(final SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill addSkill(final Skill skill) {
        if (skillRepository.findByName(skill.getName()) != null) {
            throw new DuplicateEntity("There is another skill with this name!");
        }

        return skillRepository.save(skill);
    }

    public List<Skill> listSkills() {
        return skillRepository.findAll();
    }

    public Skill editSkill(final Skill skill) {
        final Skill edit = skillRepository.findById(skill.getId()).orElseThrow(
                () -> new EntityNotFound("No record for this ID")
        );

        edit.setName(skill.getName());

        return skillRepository.save(edit);
    }

    public void deleteSkill(final Long id) {
        final Skill skill = skillRepository.findById(id).orElseThrow(
                () -> new EntityNotFound("No record for this ID")
        );

        skillRepository.delete(skill);
    }
}
