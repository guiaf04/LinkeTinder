package view

import controller.VacancyController
import model.Vaga
import service.VacancyService

class VacancyView {
    VacancyController vacancyController = new VacancyController(vacancyService: new VacancyService())

    Scanner scanner = new Scanner(System.in).useDelimiter('\n')

    Vaga createVacancy(){
        Vaga vaga = new Vaga()

        println("Write your Name:")
        vaga.setNome(scanner.next())
        println("Write your Description:")
        vaga.setDescricao(scanner.next())
        println("Write your Local:")
        vaga.setLocal(scanner.next())
        println("Write what employee id that vacancy pertences:")
        vaga.setIdempresa(scanner.next())

        return vaga
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
                Vaga vaga = createVacancy()
                vacancyController.addVacancy(vaga)
                break
            case "b":
                println vacancyController.showVacancies().join('\n')
                break
            case "c":
                vacancyController.editVacancy(createVacancy())
                break
            case "d":
                vacancyController.deleteVacancy(createVacancy())
                break
            default :
                println("Invalid Option")
                break
        }
    }
}
