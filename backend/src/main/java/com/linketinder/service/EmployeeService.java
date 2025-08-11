package com.linketinder.service;

import com.linketinder.dto.EmployeeDTO;
import com.linketinder.exception.DuplicateEntity;
import com.linketinder.exception.EntityNotFound;
import com.linketinder.model.Employee;
import com.linketinder.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.linketinder.mapper.ObjectMapper.parseListObject;
import static com.linketinder.mapper.ObjectMapper.parseObject;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO addEmployee(final Employee employee) {
        if (employeeRepository.getElementByCnpj(employee.getCnpj()) != null) {
            throw new DuplicateEntity("Bad request on employee body!");
        }

        return parseObject(employeeRepository.save(employee), EmployeeDTO.class);
    }

    public List<EmployeeDTO> listEmployees() {
        return parseListObject(employeeRepository.findAll(), EmployeeDTO.class);
    }

    public EmployeeDTO editEmployee(final Employee employee) {
        final Employee edit = employeeRepository.findByCnpj(employee.getCnpj()).orElseThrow(
                () -> new EntityNotFound("No record for this CNPJ")
        );

        edit.setName(employee.getName());
        edit.setEmail(employee.getEmail());
        edit.setCountry(employee.getCountry());
        edit.setCep(employee.getCep());
        edit.setDescription(employee.getDescription());
        edit.setPassword(employee.getPassword());

        return parseObject(employeeRepository.save(edit), EmployeeDTO.class);
    }

    public void deleteEmployee(final String cnpj) {
        final Employee employee = employeeRepository.findByCnpj(cnpj).orElseThrow(
                () -> new EntityNotFound("No record for this CNPJ")
        );

        employeeRepository.delete(employee);
    }
}
