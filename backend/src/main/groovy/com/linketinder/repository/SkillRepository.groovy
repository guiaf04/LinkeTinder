package com.linketinder.repository


import com.linketinder.model.Skill
import org.springframework.data.jpa.repository.JpaRepository

interface SkillRepository extends JpaRepository <Skill, Long>{
    Optional<Skill> findByName(String name)
}