package com.linketinder.repository

import com.linketinder.model.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository extends JpaRepository <Employee, Long>{
    Employee getElementByCnpj(String cnpj)
    Optional<Employee> findByCnpj(String cnpj)
}