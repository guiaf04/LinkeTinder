package com.linketinder.controller

import com.linketinder.dto.EmployeeDTO
import com.linketinder.model.Employee
import com.linketinder.service.EmployeeService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController()
@RequestMapping("/employees")
class EmployeeController {

    private final EmployeeService employeeService

    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<EmployeeDTO> findAll()  {
        return employeeService.listEmployees()
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<EmployeeDTO> createEmployee(@RequestBody Employee employee){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployee(employee))
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    EmployeeDTO editEmployee(@RequestBody Employee employee){
        return employeeService.editEmployee(employee)
    }

    @DeleteMapping(value = "/{cnpj}")
    ResponseEntity<?> deleteEmployee(@PathVariable("cnpj") String cnpj){
        employeeService.deleteEmployee(cnpj)
        return ResponseEntity.noContent().build()
    }
}
