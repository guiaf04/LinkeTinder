package com.linketinder.controller

import com.linketinder.dto.EmployeeDTO
import com.linketinder.model.Employee
import com.linketinder.service.EmployeeService
import org.springframework.http.HttpStatus
import spock.lang.Specification
import spock.lang.Subject

class EmployeeControllerTest extends Specification {

    EmployeeService employeeService = Mock()
    @Subject
    EmployeeController employeeController = new EmployeeController(employeeService)

    def "should find all employees"() {
        given:
        List<EmployeeDTO> employees = [new EmployeeDTO(cnpj: "12345678000100", name: "Company A")]
        employeeService.listEmployees() >> employees

        when:
        def result = employeeController.findAll()

        then:
        result == employees
    }

    def "should create an employee"() {
        given:
        Employee employee = new Employee(cnpj: "12345678000100", name: "Company A")
        EmployeeDTO employeeDTO = new EmployeeDTO(cnpj: "12345678000100", name: "Company A")
        employeeService.addEmployee(employee) >> employeeDTO

        when:
        def response = employeeController.createEmployee(employee)

        then:
        response.statusCode == HttpStatus.CREATED
        response.body == employeeDTO
    }

    def "should edit an employee"() {
        given:
        Employee employee = new Employee(cnpj: "12345678000100", name: "Company A")
        EmployeeDTO employeeDTO = new EmployeeDTO(cnpj: "12345678000100", name: "Company A")
        employeeService.editEmployee(employee) >> employeeDTO

        when:
        def result = employeeController.editEmployee(employee)

        then:
        result == employeeDTO
    }

    def "should delete an employee"() {
        given:
        String cnpj = "12345678000100"

        when:
        def response = employeeController.deleteEmployee(cnpj)

        then:
        1 * employeeService.deleteEmployee(cnpj)
        response.statusCode == HttpStatus.NO_CONTENT
    }
}