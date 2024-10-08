package org.example

import database.*
import database.interfaces.PostgresCRUDTables

static def suboptionOperation(PostgresCRUDTables d, Scanner scanner){
  println(
          """
        Type:
          a) Create
          b) Read
          c) Update
          d) Delete
        """)

  String suboption = scanner.next()

  switch (suboption) {
    case "a":
      List<String> values = List.of('Pedro', 'Pasquim', '1990-10-05',
              'pe@coal.com', '12555678901', 'Brasil', '12225-678',
              'Desenvolvedor com 20 anos de experiência', 'senha123')
      d.criar(values)
      break
    case "b":
      d.listar()
      break
    case "c":
      List<String> fields = List.of("nome", "sobrenome")
      List<String> values = List.of("fulano", "detal")

      d.atualizar(fields, values, 6)
      break
    case "d":
      d.deletar(5)
      break
  }

}
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

  candidateDB candidateDB = new candidateDB()
  employeeDB employeeDB = new employeeDB()
  skillsDB skillsDB = new skillsDB()
  vacancyDB vacancyDB = new vacancyDB()

  while(option != "0"){
    println(message)
    option = scanner.next()

    switch (option){
      case "0" :
        break
      case "1" :
        suboptionOperation(candidateDB, scanner)
        break
      case "2" :
        suboptionOperation(employeeDB, scanner)
        break
      case "3":
        suboptionOperation(vacancyDB, scanner)
        break
      case "4":
        suboptionOperation(skillsDB, scanner)
        break
      default : println("Incorrect option, try again")
    }
  }
}