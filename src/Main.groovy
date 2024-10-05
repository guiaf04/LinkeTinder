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

//  DatabaseSample db = new DatabaseSample()
  candidateDB candidateDB = new candidateDB()
//  db.connect()

  while(option != "0"){
    println(message)
    option = scanner.next()

    switch (option){
      case "0" :
        break
      case "1" :
//        println system.listCandidates()
        candidateDB.listar("")
        break
      case "2" :
        println system.listCompanys()
        break
      default : println("Incorrect option, try again")
    }
  }
}