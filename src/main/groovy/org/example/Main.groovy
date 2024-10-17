package org.example

import view.CandidateView
import view.EmployeeView
import view.SkillView
import view.VacancyView

static void main(String[] args) {
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

  CandidateView candidateView = new CandidateView()
  EmployeeView employeeView = new EmployeeView()
  SkillView skillView = new SkillView()
  VacancyView vacancyView = new VacancyView()

  while(option != "0"){
    println(message)
    option = scanner.next()

    switch (option){
      case "0" :
        break
      case "1" :
        candidateView.menu()
        break
      case "2" :
        employeeView.menu()
        break
      case "3":
        skillView.menu()
        break
      case "4":
        vacancyView.menu()
        break
      default : println("Incorrect option, try again")
    }
  }
}