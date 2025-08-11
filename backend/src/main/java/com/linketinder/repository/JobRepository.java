package com.linketinder.repository;

import com.linketinder.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
  public abstract List<Job> findAllByIdEmployee(Long employeeId);

  public abstract boolean existsByNameAndDescriptionAndIdEmployeeAndLocal(String name, String description, Long idEmployee, String local);
}
