package service

import model.Empresa
import dao.EmployeeDAO

class EmployeeService {
    EmployeeDAO employeeDAO

    boolean addEmployee(Empresa empresa){
        if (employeeDAO.getElementByCNPJ(empresa) != ""){
            println("Essa empresa já está cadastrado, tente usar informações diferentes para cadastrar uma nova empresa")
            return false
        }

        return employeeDAO.criar(empresa)
    }

    List<String> listEmployees(){
        return employeeDAO.listar()
    }
    
    boolean editEmployee(Empresa empresa){
        if (employeeDAO.getElementByCNPJ(empresa) == ""){
            println "Essa empresa não está cadastrada, então não é possível editá-la"
            return false
        }

        return employeeDAO.atualizar(empresa)
    }

    boolean deleteEmployee(Empresa empresa){
        if (employeeDAO.getElementByCNPJ(empresa) == ""){
            println("Esse usuário não está cadastrado, tente novamente")
            return false
        }

        return employeeDAO.deletar(empresa)
    }
}
