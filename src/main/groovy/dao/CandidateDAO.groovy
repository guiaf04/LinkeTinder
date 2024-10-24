package dao

import dao.enums.JDBCDatabases
import dao.factorys.JDBCDatabaseFactory
import dao.interfaces.JDBCInterface
import groovy.sql.Sql
import model.Candidato

import java.sql.SQLException

class CandidateDAO  {
    JDBCDatabaseFactory databaseFactory = new JDBCDatabaseFactory()
    JDBCInterface jdbcInterface = databaseFactory.getDatabase(JDBCDatabases.PostgreSQL)

    boolean criar(Candidato candidato) {
        Sql dbConnection = jdbcInterface.connect()

        List<String> values = candidato.getProperties().findAll {!it.key.toString().equalsIgnoreCase("class")}.values() as List<String>

        String query = "INSERT INTO candidato " +
                "(nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha) " +
                "VALUES (${values.collect { "'${it}'" }.join(", ")})"

        try {
            dbConnection.executeInsert(query)
        } catch (SQLException e) {
            println(e.stackTrace)
            println("Não foi possível executar a query, verifique os parâmetros")
            return false
        }

        jdbcInterface.disconnect(dbConnection)

        return true
    }

    List<String> listar() {
        Sql conn = jdbcInterface.connect()
        List<String> result = new ArrayList<>()

        try {
            conn.eachRow("SELECT * FROM candidato") { row ->
                result.add(row.toString())
            }
        } catch (SQLException e) {
            println(e.stackTrace)
            println "Não foi possível executar a query, verifique se os valores passados foram corretos"
        }

        jdbcInterface.disconnect(conn)

        return result
    }

    String getElementByCPF(Candidato candidato) {
        Sql conn = jdbcInterface.connect()

        String query = "SELECT * FROM " +
                "candidato " +
                "WHERE cpf='${candidato.getCpf()}'"

        String result = ""

        try {
            conn.eachRow(query) { row ->
                println(row.toString())
                result = row.toString()
            }
        }catch (SQLException e){
            e.printStackTrace()
        }

        return result
    }

    boolean atualizar(Candidato candidato) {
        Sql conn = jdbcInterface.connect()

        String setClause = "nome='${candidato.getNome()}', sobrenome='${candidato.getSobrenome()}'" +
                ", pais='${candidato.getPais()}', email='${candidato.getEmail()}'" +
                ", data_nascimento='${candidato.getDatanascimento()}' cep='${candidato.getCep()}'" +
                ", descricao_pessoal='${candidato.getDescricaopessoal()}', senha='${candidato.getSenha()}'"

        String query = "UPDATE candidato SET ${setClause} WHERE cpf='${candidato.getCpf()}'"

        try {
            println query
            conn.executeUpdate query
        } catch (SQLException e) {
            e.printStackTrace()
            println "Não foi possível executar a query, verifique se os valores passados foram corretos"
            return false
        }

        jdbcInterface.disconnect(conn)
        return true
    }

    boolean deletar(Candidato candidato) {
        Sql conn = jdbcInterface.connect()

        String query = "DELETE FROM candidato WHERE cpf=${candidato.getCpf()}"

        try {
            conn.execute(query)
        } catch (SQLException e) {
            e.printStackTrace()
            println "Não foi possível executar a query, verifique se os valores passados foram corretos"
            return false
        }
        println "Candidato was deleted!"

        jdbcInterface.disconnect(conn)
        return true
    }
}
