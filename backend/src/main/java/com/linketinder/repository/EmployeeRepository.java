package com.linketinder.repository;

import com.linketinder.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  Employee getElementByCnpj(String cnpj);

  Optional<Employee> findByCnpj(String cnpj);
}
