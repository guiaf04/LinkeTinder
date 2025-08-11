package com.linketinder.repository;

import com.linketinder.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {
  public abstract Optional<Skill> findByName(String name);
}
