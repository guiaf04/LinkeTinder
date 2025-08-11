package com.linketinder.service;

import com.linketinder.exception.DuplicateEntity;
import com.linketinder.exception.EntityNotFound;
import com.linketinder.model.Candidate;
import com.linketinder.model.CandidateSkill;
import com.linketinder.model.Skill;
import com.linketinder.repository.CandidateRepository;
import com.linketinder.repository.CandidateSkillRepository;
import com.linketinder.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateSkillService {

    private final CandidateRepository candidateRepository;
    private final SkillRepository skillRepository;
    private final CandidateSkillRepository candidateSkillRepository;

    public CandidateSkillService(
            final CandidateRepository candidateRepository,
            final SkillRepository skillRepository,
            final CandidateSkillRepository candidateSkillRepository
    ) {
        this.candidateRepository = candidateRepository;
        this.skillRepository = skillRepository;
        this.candidateSkillRepository = candidateSkillRepository;
    }

    public List<Skill> findAllCandidateSkills(final Long candidateId) {
        final List<CandidateSkill> candidateSkills = candidateSkillRepository.findByCandidateId(candidateId);
        if (candidateSkills.isEmpty()) {
            throw new EntityNotFound("There's no candidate with this ID or no skills associated");
        }

        return candidateSkills.stream()
                .map(CandidateSkill::getSkill)
                .collect(Collectors.toList());
    }

    public List<Skill> addCandidateSkills(final Long candidateId, final List<Skill> skillList) {
        final Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new EntityNotFound("Candidate not found"));

        final List<Skill> addedSkills = new ArrayList<>();

        for (final Skill skill : skillList) {
            final Skill skillValidate = skillRepository.findByName(skill.getName())
                    .orElseThrow(() -> new EntityNotFound("Skill not found"));

            final CandidateSkill candidateSkill = new CandidateSkill(candidate, skillValidate);

            candidateSkillRepository.findByCandidateAndSkill(candidate, skillValidate)
                    .ifPresentOrElse(
                            cs -> { throw new DuplicateEntity("Skill already added to this candidate"); },
                            () -> candidateSkillRepository.save(candidateSkill)
                    );

            addedSkills.add(skill);
        }

        return addedSkills;
    }
}
