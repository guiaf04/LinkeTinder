package model

class Candidato{
    String nome, sobrenome, datanascimento, email, cpf, pais, cep, descricaopessoal, senha


    @Override
    String toString() {
        return "Candidato{" +
                "nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", dataNascimento='" + datanascimento + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", pais='" + pais + '\'' +
                ", cep='" + cep + '\'' +
                ", descricaoPessoal='" + descricaopessoal + '\'' +
                ", senha='" + senha + '\'' +
                '}'
    }
}

