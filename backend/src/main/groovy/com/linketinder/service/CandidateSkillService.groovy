package com.linketinder.service

import com.linketinder.exception.DuplicateEntity
import com.linketinder.exception.EntityNotFound
import com.linketinder.model.Candidate
import com.linketinder.model.CandidateSkill
import com.linketinder.model.Skill
import com.linketinder.repository.CandidateRepository
import com.linketinder.repository.CandidateSkillRepository
import com.linketinder.repository.SkillRepository
import org.springframework.stereotype.Service

@Service
class CandidateSkillService {
    final CandidateRepository candidateRepository
    final SkillRepository skillRepository
    final CandidateSkillRepository candidateSkillRepository

    CandidateSkillService(
            CandidateRepository candidateRepository,
            SkillRepository skillRepository,
            CandidateSkillRepository candidateSkillRepository
    ) {
        this.candidateRepository = candidateRepository
        this.skillRepository = skillRepository
        this.candidateSkillRepository = candidateSkillRepository
    }

    List<Skill> findAllCandidateSkills(Long candidateId){
        List<CandidateSkill> candidateSkills = candidateSkillRepository.findByCandidateId(candidateId)
        if (candidateSkills.isEmpty()) {
            throw new EntityNotFound("There's no candidate with this ID or no skills associated")
        }

        return candidateSkills.collect { it.skill }
    }

    List<Skill> addCandidateSkills(Long candidateId, List<Skill> skillList){
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new EntityNotFound("Candidate not found"))
        List<Skill> addedSkills = new ArrayList<>()
        skillList.each {
            skill ->
                {
                    Skill skillValidate = skillRepository.findByName(skill.name).orElseThrow(() -> new EntityNotFound
                            ("Skill not found"))
                    CandidateSkill candidateSkill = new CandidateSkill(candidate: candidate, skill: skillValidate)

                    candidateSkillRepository.findByCandidateAndSkill(candidate, skillValidate).ifPresentOrElse(
                            { throw new DuplicateEntity("Skill already added to this candidate") },
                            { candidateSkillRepository.save(candidateSkill) }
                    )

                    addedSkills.add(skill)
                }
        }

        return addedSkills
    }


}
