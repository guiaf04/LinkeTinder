package org.example

import controller.CandidateController
import model.Candidato
import service.CandidateService


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
    1 to show candidates operations of the system
    2 to show company's operations of the system
    3 to show skill's operations of the system
    4 to show vacancy's operations of the system
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
      default : println("Incorrect option, try again")
    }
  }
}