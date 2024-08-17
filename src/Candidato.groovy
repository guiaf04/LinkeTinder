import groovy.transform.ToString

@ToString
class Candidato extends Pessoa {
    String cpf, idade, name, email, state, cep, description
    ArrayList<Competencias> competencias = new ArrayList<>()

}

