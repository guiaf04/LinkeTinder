package com.linketinder.service;

import com.linketinder.exception.DuplicateEntity;
import com.linketinder.exception.EntityNotFound;
import com.linketinder.model.Job;
import com.linketinder.model.JobSkill;
import com.linketinder.model.Skill;
import com.linketinder.repository.JobRepository;
import com.linketinder.repository.JobSkillRepository;
import com.linketinder.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobSkillService {

    private final JobRepository jobRepository;
    private final SkillRepository skillRepository;
    private final JobSkillRepository jobSkillRepository;

    public JobSkillService(
            final JobRepository jobRepository,
            final SkillRepository skillRepository,
            final JobSkillRepository jobSkillRepository
    ) {
        this.jobRepository = jobRepository;
        this.skillRepository = skillRepository;
        this.jobSkillRepository = jobSkillRepository;
    }

    public List<Skill> findAllJobSkills(final Long jobId) {
        final List<JobSkill> jobSkills = jobSkillRepository.findByJobId(jobId);
        if (jobSkills.isEmpty()) {
            throw new EntityNotFound("There's no job with this ID or no skills associated");
        }

        return jobSkills.stream()
                .map(JobSkill::getSkill)
                .collect(Collectors.toList());
    }

    public List<Skill> addJobSkills(final Long jobId, final List<Skill> skillList) {
        final Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new EntityNotFound("Job not found"));

        final List<Skill> addedSkills = new ArrayList<>();

        for (final Skill skill : skillList) {
            final Skill skillValidate = skillRepository.findByName(skill.getName())
                    .orElseThrow(() -> new EntityNotFound("Skill not found"));

            final JobSkill jobSkill = new JobSkill(job, skillValidate);

            jobSkillRepository.findByJobAndSkill(job, skillValidate)
                    .ifPresentOrElse(
                            js -> { throw new DuplicateEntity("Skill already added to this job"); },
                            () -> jobSkillRepository.save(jobSkill)
                    );

            addedSkills.add(skill);
        }

        return addedSkills;
    }
}
