package com.linketinder.repository

import com.linketinder.model.Job
import org.springframework.data.jpa.repository.JpaRepository

interface JobRepository extends JpaRepository <Job, Long>{
    List<Job> findAllByIdEmployee(Long employeeId)
}