package com.linketinder.service

import com.linketinder.dto.EmployeeDTO
import com.linketinder.exception.DuplicateEntity
import com.linketinder.exception.EntityNotFound
import com.linketinder.model.Employee
import com.linketinder.repository.EmployeeRepository
import org.springframework.stereotype.Service

import static com.linketinder.mapper.ObjectMapper.parseListObject
import static com.linketinder.mapper.ObjectMapper.parseObject

@Service
class EmployeeService {

    EmployeeRepository employeeRepository

    EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository
    }

    EmployeeDTO addEmployee(Employee employee){
        if (employeeRepository.getElementByCnpj(employee.getCnpj())){
            throw new DuplicateEntity("Bad request on employee body!")
        }

        return parseObject(employeeRepository.save(employee), EmployeeDTO.class)
    }

    List<EmployeeDTO> listEmployees(){
        return parseListObject(employeeRepository.findAll(), EmployeeDTO.class)
    }

    EmployeeDTO editEmployee(Employee employee){
        Employee edit = employeeRepository.findByCnpj(employee.getCnpj()).orElseThrow(
                () -> new EntityNotFound("No record for this CNPJ")
        )

        edit.name = employee.name
        edit.email = employee.email
        edit.country = employee.country
        edit.cep = employee.cep
        edit.description = employee.description
        edit.password = employee.password

        return parseObject(employeeRepository.save(edit), EmployeeDTO.class)
    }

    void deleteEmployee(String cnpj){
        Employee employee = employeeRepository.findByCnpj(cnpj).orElseThrow(
                () -> new EntityNotFound("No record for this CNPJ")
        )

        employeeRepository.delete(employee)
    }
}
