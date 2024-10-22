package controller

import model.Empresa
import service.EmployeeService

class EmployeeController {
    EmployeeService employeeService

    List<String> showEmployees(){
        return employeeService.listEmployees()
    }

    boolean addEmployee(Empresa empresa){
        return employeeService.addEmployee(empresa)
    }

    boolean editEmployee(Empresa empresa){
        return employeeService.editEmployee(empresa)
    }

    boolean deleteEmployee(Empresa empresa){
        return employeeService.deleteEmployee(empresa)
    }
}
