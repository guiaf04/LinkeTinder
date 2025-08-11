package com.linketinder.repository;

import com.linketinder.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  public abstract Employee getElementByCnpj(String cnpj);

  public abstract Optional<Employee> findByCnpj(String cnpj);
}
