package view

import controller.SkillsController
import model.Competencia
import service.SkillsService

class SkillView {
    SkillsController skillController = new SkillsController(skillsService: new SkillsService())

    Scanner scanner = new Scanner(System.in).useDelimiter('\n')

    Competencia createSkill(){
        Competencia competencia = new Competencia()

        println("Write your Name:")
        competencia.setNome(scanner.next())

        return competencia
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
                Competencia competencia = createSkill()
                skillController.addSkills(competencia)
                break
            case "b":
                skillController.showSkills()
                break
            case "c":
                skillController.editSkills(createSkill())
                break
            case "d":
                skillController.deleteSkills(createSkill())
                break
            default :
                println("Invalid Option")
                break
        }
    }
}
