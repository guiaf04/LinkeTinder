package controller

import model.Empresa
import service.EmployeeService

class EmployeeController {
    EmployeeService employeeService

    void showEmployees(){
        println employeeService.listEmployees().join("\n")
    }

    boolean addEmployee(Empresa empresa){
        if (!employeeService.addEmployee(empresa)) {
            println("Não foi possível adicionar o empresa")
            return false
        }

        println("Empresa inserido com sucesso!")
        return true
    }

    boolean editEmployee(Empresa empresa){
        if (!employeeService.editEmployee(empresa)) {
            println("Não foi possível editar o empresa")
            return false
        }

        println("Empresa editado com sucesso!")
        return true
    }

    boolean deleteEmployee(Empresa empresa){
        if (!employeeService.deleteEmployee(empresa)) {
            println("Não foi possível deletar o empresa")
            return false
        }

        println("Empresa deletado com sucesso!")
        return true
    }
}
