package database

import groovy.sql.Sql

class candidateDB extends PostgresJDBCSample {
    def criar(List<String> values) {
        List<String> fields =
                List.of("nome", "sobrenome", "data_nascimento",
                "email", "cpf", "pais", "cep",
                "descricao_pessoal", "senha")

        super.criar(fields, values, "candidato")
    }

    def listar() {
        super.listar("candidato")
    }

    static def atualizar(List<String> fields, List<String> values, int id) {
        atualizar(fields, values, id, "candidato")
    }

    def deletar(int id) {
        super.deletar(id, "candidato")
    }
}
