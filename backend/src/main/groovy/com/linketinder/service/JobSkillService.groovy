package com.linketinder.service

import com.linketinder.exception.EntityNotFound
import com.linketinder.model.Job
import com.linketinder.model.JobSkill
import com.linketinder.model.Skill
import com.linketinder.repository.JobRepository
import com.linketinder.repository.JobSkillRepository
import com.linketinder.repository.SkillRepository
import org.springframework.stereotype.Service

@Service
class JobSkillService {
    final JobRepository jobRepository
    final SkillRepository skillRepository
    final JobSkillRepository jobSkillRepository

    JobSkillService(
            JobRepository jobRepository,
            SkillRepository skillRepository,
            JobSkillRepository jobSkillRepository
    ) {
        this.jobRepository = jobRepository
        this.skillRepository = skillRepository
        this.jobSkillRepository = jobSkillRepository
    }

    List<Skill> findAllJobSkills(Long jobId){
        List<JobSkill> jobSkills = jobSkillRepository.findByJobId(jobId)
        if (jobSkills.isEmpty()) {
            throw new EntityNotFound("There's no job with this ID or no skills associated")
        }

        return jobSkills.collect { it.skill }
    }

    List<Skill> addJobSkills(Long jobId, List<Skill> skillList){
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new EntityNotFound("Job not found"))
        List<Skill> addedSkills = new ArrayList<>()
        skillList.each {
            skill ->
                {
                    Skill skillValidate = skillRepository.findByName(skill.name).orElseThrow(() -> new EntityNotFound
                            ("Skill not found"))
                    JobSkill jobSkill = new JobSkill(job: job, skill: skillValidate)

                    jobSkillRepository.findByJobAndSkill(job, skillValidate).ifPresentOrElse(
                            { throw new EntityNotFound("Skill already added to this job") },
                            { jobSkillRepository.save(jobSkill) }
                    )

                    addedSkills.add(skill)
                }
        }

        return addedSkills
    }


}
