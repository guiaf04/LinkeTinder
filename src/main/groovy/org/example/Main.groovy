package org.example

import controller.CandidateController
import controller.EmployeeController
import controller.VacancyController
import model.Candidato
import model.Empresa
import model.Vaga
import service.CandidateService
import service.EmployeeService
import service.VacancyService


//static def suboptionOperation(PostgresCRUDTables d, Scanner scanner){
//  println(
//          """
//        Type:
//          a) Create
//          b) Read
//          c) Update
//          d) Delete
//        """)
//
//  String suboption = scanner.next()
//
//  switch (suboption) {
//    case "a":
//      List<String> values = List.of('Pedro', 'Pasquim', '1990-10-05',
//              'pe@coal.com', '12555678901', 'Brasil', '12225-678',
//              'Desenvolvedor com 20 anos de experiência', 'senha123')
//      d.criar(values)
//      break
//    case "b":
//      d.listar()
//      break
//    case "c":
//      List<String> fields = List.of("nome", "sobrenome")
//      List<String> values = List.of("fulano", "detal")
//
//      d.atualizar(fields, values, 6)
//      break
//    case "d":
//      d.deletar(5)
//      break
//    default:
//          println("Opção inválida")
//          break
//  }
//
//}
static void main(String[] args) {
//  LinkeTinder system = new LinkeTinder()

  Scanner scanner = new Scanner(System.in)

  String option = ""

  String message = """
  Welcome to Linketinder!
  Type: 
    0 for exit the program
    1-4 to show candidates operations of the system
    5-8 to show company's operations of the system
    9-12 to show vacancy's operations of the system
    13-16 to show skill's operations of the system
  """

  CandidateController candidateController = new CandidateController(candidateService: new CandidateService())
  Candidato candidato = new Candidato()
  candidato.setNome('Testinho')
  candidato.setSobrenome('Da Silva')
  candidato.setDatanascimento('2010-10-10')
  candidato.setEmail('teste@123.com')
  candidato.setCpf('teste')
  candidato.setPais('Terra dos Testes')
  candidato.setCep('11111-111')
  candidato.setDescricaopessoal('sou um teste')
  candidato.setSenha('teste123')

  EmployeeController employeeController = new EmployeeController(employeeService: new EmployeeService())
  Empresa empresa = new Empresa()
  empresa.setNome('Testes dos Bons')
  empresa.setEmail('teste@empresa.com')
  empresa.setCnpj("11111121212")
  empresa.setPais('Terra dos Testes')
  empresa.setCep('11111-111')
  empresa.setDescricao("Sou uma empresa teste")
  empresa.setSenha('teste123')

  VacancyController vacancyController = new VacancyController(vacancyService: new VacancyService())
  Vaga vaga = new Vaga()
  vaga.setNome('Testes dos Bons')
  vaga.setDescricao("Sou uma vaga teste")
  vaga.setLocal('Rua dos Testes')
  vaga.setIdempresa('3')

  while(option != "0"){
    println(message)
    option = scanner.next()

    switch (option){
      case "0" :
        break
      case "1" :
        candidateController.showUsers()
        break
      case "2" :
        candidateController.addCandidate(candidato)
        break
      case "3":
        candidateController.editCandidate(candidato)
        break
      case "4":
        candidateController.deleteCandidate(candidato)
        break

      case "5":
        employeeController.showEmployees()
        break
      case "6":
        employeeController.addEmployee(empresa)
        break
      case "7":
        employeeController.editEmployee(empresa)
        break
      case "8":
        employeeController.deleteEmployee(empresa)
        break

      case "9":
        vacancyController.showVacancies()
        break
      case "10":
        vacancyController.addVacancy(vaga)
        break
      case "11":
        vacancyController.editVacancy(vaga)
        break
      case "12":
        vacancyController.deleteVacancy(vaga)
        break
      default : println("Incorrect option, try again")
    }
  }
}