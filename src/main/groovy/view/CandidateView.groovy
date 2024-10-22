package view

import controller.CandidateController
import model.Candidato
import service.CandidateService

class CandidateView {
    CandidateController candidateController

    Scanner scanner = new Scanner(System.in).useDelimiter('\n')

    Candidato createCandidate(){
        Candidato candidato = new Candidato()
        println("Write your First Name:")
        candidato.setNome(scanner.next())
        println("Write your Last Name:")
        candidato.setSobrenome(scanner.next())
        println("Write your birthday:")
        candidato.setDatanascimento(scanner.next())
        println("Write your email:")
        candidato.setEmail(scanner.next())
        println("Write your CPF:")
        candidato.setCpf(scanner.next())
        println("Write your Country:")
        candidato.setPais(scanner.next())
        println("Write your CEP:")
        candidato.setCep(scanner.next())
        println("Write your Personal Description:")
        candidato.setDescricaopessoal(scanner.next())
        println("Write your Password:")
        candidato.setSenha(scanner.next())

        return candidato
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
                Candidato candidato = createCandidate()
                candidateController.addCandidate(candidato)
                break
            case "b":
                println candidateController.showUsers().join("\n")
                break
            case "c":
                candidateController.editCandidate(createCandidate())
                break
            case "d":
                candidateController.deleteCandidate(createCandidate())
                break
            default :
                println("Invalid Option")
                break
        }
    }
}
