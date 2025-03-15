package com.linketinder.service

import com.linketinder.exception.DuplicateEntity
import com.linketinder.exception.EntityNotFound
import com.linketinder.model.Skill
import com.linketinder.repository.SkillRepository
import org.springframework.stereotype.Service

@Service
class SkillsService {

    SkillRepository skillRepository

    SkillsService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository
    }

    Skill addSkill(Skill skill){
        if (skillRepository.findByName(skill.getName())) {
            throw new DuplicateEntity("There is another skill with this name!")
        }

        return skillRepository.save(skill)
    }

    List<Skill> listSkills(){
        return skillRepository.findAll()
    }

    Skill editSkill(Skill skill){
        Skill edit = skillRepository.findById(skill.getId()).orElseThrow(
                () -> new EntityNotFound("No record for this ID")
        )

        edit.name = skill.name

        return skillRepository.save(edit)
    }

    void deleteSkill(Long id){
        Skill skill = skillRepository.findById(id).orElseThrow(
                () -> new EntityNotFound("No record for this ID")
        )

        skillRepository.delete(skill)
    }
}
