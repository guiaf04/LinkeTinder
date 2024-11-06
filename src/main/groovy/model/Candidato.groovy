package model

class Candidato{
    String nome
    String sobrenome
    String  datanascimento
    String email
    transient String cpf
    String pais
    String cep
    String descricaopessoal

    transient String senha


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

