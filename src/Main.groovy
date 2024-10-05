import database.*

static void main(String[] args) {
  LinkeTinder system = new LinkeTinder()

  Scanner scanner = new Scanner(System.in)

  String option = ""

  String message = """
  Welcome to Linketinder!
  Type: 
    0 for exit the program
    1 to show the candidates of the system
    2 to show company's of the system
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
        candidateDB.listar("")
        employeeDB.listar("")
        skillsDB.listar("")
        vacancyDB.listar("")
        break
      case "2" :
        println system.listCompanys()
        break
      default : println("Incorrect option, try again")
    }
  }
}