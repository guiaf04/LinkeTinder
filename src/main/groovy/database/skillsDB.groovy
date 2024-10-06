package database

import groovy.sql.Sql

class skillsDB extends DatabaseSample {
    @Override
    def criar(List<String> values) {
        assert values.size() != 0

        Sql conn = connect()

        String query = "INSERT INTO competencia " +
                "(nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) " +
                "VALUES ("

        for(int i =0; i<values.size(); i++){
            query += (i == 0 ? "" : ", ")
            query += "'${values[i]}'"
        }

        query += ")"

        conn.executeInsert(query)

        desconectar(conn)
    }

    @Override
    def listar(String query) {
        Sql sql = connect()
        sql.eachRow("SELECT * FROM competencia") { row ->
            println(row.toString())
        }
    }

    @Override
    def atualizar(List<String> fields, List<String> values, int id) {
        return null
    }

    @Override
    def deletar(int id) {
        return null
    }

}
