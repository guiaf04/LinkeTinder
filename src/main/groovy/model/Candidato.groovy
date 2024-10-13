package model

class Candidato extends Pessoa {
    String cpf, idade

    @Override
    String toString() {
        return "model.Candidato{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", state=" + state +
                ", cep='" + cep + '\'' +
                ", description='" + description + '\'' +
                ", cpf='" + cpf + '\'' +
                ", idade='" + idade + '\'' +
//                ", competencias=" + competencias +
                '}'
    }
}

