package database

import groovy.sql.Sql

class employeeDB extends DatabaseSample {
    @Override
    def criar(List<String> values) {
        assert values.size() != 0

        Sql conn = connect()

        String query = "INSERT INTO empresa " +
                "(nome, email, cnpj, pais, cep, descricao, senha) " +
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
        sql.eachRow("SELECT * FROM empresa") { row ->
            println(row.toString())
        }
    }

    @Override
    def atualizar(List<String> fields, List<String> values, int id) {
        Sql conn = connect()
        assert fields.size() == values.size()

        String query = "UPDATE empresa SET "

        for(int i =0; i<fields.size(); i++){
            query += (i == 0 ? "" : ", ")
            query += "${fields[i]}='${values[i]}'"
        }

        query += " WHERE id=${id}"

        conn.executeUpdate query
    }

    @Override
    def deletar(int id) {
        Sql conn = connect()

        String query = "DELETE FROM empresa WHERE id=${id}"

        conn.execute(query)

        println "Employee with id = ${id} was deleted!"
    }
}
