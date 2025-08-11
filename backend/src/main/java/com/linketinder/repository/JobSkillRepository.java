package com.linketinder.repository;

import com.linketinder.model.Job;
import com.linketinder.model.JobSkill;
import com.linketinder.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobSkillRepository extends JpaRepository<JobSkill, Long> {
  public abstract Optional<Job> findByJob(Job job);

  public abstract List<JobSkill> findByJobId(Long jobId);

  public abstract List<Skill> findAllSkillByJobId(Long jobId);

  public abstract Optional<JobSkill> findByJobAndSkill(Job job, Skill skill);
}
