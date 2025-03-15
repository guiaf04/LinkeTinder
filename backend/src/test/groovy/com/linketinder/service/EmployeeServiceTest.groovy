package com.linketinder.service

import com.linketinder.dto.EmployeeDTO
import com.linketinder.exception.DuplicateEntity
import com.linketinder.exception.EntityNotFound
import com.linketinder.model.Employee
import com.linketinder.repository.EmployeeRepository
import spock.lang.Specification

class EmployeeServiceTest extends Specification {
    EmployeeRepository employeeRepository = Mock()
    EmployeeService employeeService  = new EmployeeService(employeeRepository)

    def "should add an employee"() {
        given:
        Employee employee = new Employee(cnpj: "123456789", name: "Company A")
        EmployeeDTO employeeDTO = new EmployeeDTO(cnpj: "123456789", name: "Company A")
        employeeRepository.getElementByCnpj(employee.cnpj) >> null
        employeeRepository.save(employee) >> employee

        when:
        def result = employeeService.addEmployee(employee)

        then:
        result == employeeDTO
    }

    def "should not add a duplicate employee"() {
        given:
        Employee employee = new Employee(cnpj: "123456789", name: "Company A")
        employeeRepository.getElementByCnpj(employee.cnpj) >> employee

        when:
        employeeService.addEmployee(employee)

        then:
        thrown(DuplicateEntity)
    }

    def "should list all employees"() {
        given:
        List<Employee> employees = [new Employee(cnpj: "123456789", name: "Company A")]
        List<EmployeeDTO> employeeDTOs = [new EmployeeDTO(cnpj: "123456789", name: "Company A")]
        employeeRepository.findAll() >> employees

        when:
        def result = employeeService.listEmployees()

        then:
        result == employeeDTOs
    }

    def "should edit an employee"() {
        given:
        Employee employee = new Employee(cnpj: "123456789", name: "Company A")
        Employee editEmployee = new Employee(cnpj: "123456789", name: "Company B")
        EmployeeDTO employeeDTO = new EmployeeDTO(cnpj: "123456789", name: "Company B")

        employeeRepository.findByCnpj(editEmployee.cnpj) >> Optional.of(employee)
        employeeRepository.save(employee) >> editEmployee

        when:
        def result = employeeService.editEmployee(editEmployee)

        then:
        result == employeeDTO
    }

    def "should not edit a non-existent employee"() {
        given:
        Employee employee = new Employee(cnpj: "123456789", name: "Company A")
        employeeRepository.findByCnpj(employee.cnpj) >> Optional.empty()

        when:
        employeeService.editEmployee(employee)

        then:
        thrown(EntityNotFound)
    }

    def "should delete an employee"() {
        given:
        Employee employee = new Employee(cnpj: "123456789", name: "Company A")
        employeeRepository.findByCnpj(employee.cnpj) >> Optional.of(employee)

        when:
        employeeService.deleteEmployee(employee.cnpj)

        then:
        1 * employeeRepository.delete(employee)
    }

    def "should not delete a non-existent employee"() {
        given:
        String cnpj = "123456789"
        employeeRepository.findByCnpj(cnpj) >> Optional.empty()

        when:
        employeeService.deleteEmployee(cnpj)

        then:
        thrown(EntityNotFound)
    }
}
