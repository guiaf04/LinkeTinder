package view

import controller.EmployeeController
import model.Empresa
import service.EmployeeService

class EmployeeView {
    EmployeeController employeeController = new EmployeeController(employeeService: new EmployeeService())

    Scanner scanner = new Scanner(System.in).useDelimiter('\n')

    Empresa createEmployee(){
        Empresa empresa = new Empresa()

        println("Write your Name:")
        empresa.setNome(scanner.next())
        println("Write your email:")
        empresa.setEmail(scanner.next())
        println("Write your CNPJ:")
        empresa.setCnpj(scanner.next())
        println("Write your Country:")
        empresa.setPais(scanner.next())
        println("Write your CEP:")
        empresa.setCep(scanner.next())
        println("Write your Description:")
        empresa.setDescricao(scanner.next())
        println("Write your Password:")
        empresa.setSenha(scanner.next())

        return empresa
    }

    void menu(){
        println(
                """
        Type:
          a) Create
          b) Read
          c) Update
          d) Delete
        """)

        String option = scanner.next()

        switch (option){
            case "a":
                Empresa empresa = createEmployee()
                employeeController.addEmployee(empresa)
                break
            case "b":
                employeeController.showEmployees()
                break
            case "c":
                employeeController.editEmployee(createEmployee())
                break
            case "d":
                employeeController.deleteEmployee(createEmployee())
                break
            default :
                println("Invalid Option")
                break
        }
    }
}
