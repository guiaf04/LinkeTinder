package com.linketinder.repository

import com.linketinder.model.Candidate
import com.linketinder.model.Skill
import org.springframework.data.jpa.repository.JpaRepository

interface SkillRepository extends JpaRepository <Skill, Long>{
    Skill findByName(String name)
}