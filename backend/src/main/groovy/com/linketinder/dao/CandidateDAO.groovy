package com.linketinder.dao


import com.linketinder.dao.enums.JDBCDatabases
import com.linketinder.dao.factorys.JDBCDatabaseFactory
import groovy.sql.Sql
import com.linketinder.model.Candidato
import com.linketinder.model.Competencia
import org.springframework.stereotype.Repository

import java.sql.SQLException

@Repository
class CandidateDAO {
    JDBCDatabaseFactory databaseFactory = new JDBCDatabaseFactory()
    com.linketinder.dao.interfaces.JDBCInterface jdbcInterface = databaseFactory.getDatabase(JDBCDatabases.PostgreSQL)

    int getSkillsID(Competencia competencia){
        Sql dbConnection = jdbcInterface.connect()

        def row = dbConnection.firstRow("SELECT id FROM competencia WHERE firstName = ?", [competencia.getNome()])
        if (row) {
            return row.id as int
        } else {
            throw new SQLException("Competência não encontrada: $competencia")
        }
    }

    boolean insertSkill(Competencia competencia, int candidatoID){
        Sql dbConnection = jdbcInterface.connect()

        String query = "INSERT INTO candidato_competencia (id_candidato, id_competencia) VALUES (${candidatoID}, ${getSkillsID(competencia)})"

        try{
            dbConnection.executeInsert(query)
        }catch (Exception e){
            e.printStackTrace()
            return false
        }

        return true
    }

    boolean criar(Candidato candidato) {
        Sql dbConnection = jdbcInterface.connect()

        List<String> values = candidato.getProperties().findAll {!it.key.toString().equalsIgnoreCase("class")}.values() as List<String>

        String query = "INSERT INTO candidato " +
                "(firstName, lastName, data_nascimento, email, cpf, country, cep, descricao_pessoal, password) " +
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

    List<Candidato> listar() {
        Sql conn = jdbcInterface.connect()
        List<Candidato> result = new ArrayList<>()

        try {
            conn.eachRow("SELECT * FROM candidato") { row ->
                Candidato candidato = new Candidato()
                candidato.setFirstName(row[1] as String)
                candidato.setLastName(row[2] as String)
                candidato.setBirthday(row[3] as String)
                candidato.setEmail(row[4] as String)
                candidato.setCpf(row[5] as String)
                candidato.setCountry(row[6] as String)
                candidato.setCep(row[7] as String)
                candidato.setPersonalDescription(row[8] as String)
                candidato.setPassword(row[9] as String)

                result.add(candidato)
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

        String setClause = "firstName='${candidato.getFirstName()}', lastName='${candidato.getLastName()}'" +
                ", country='${candidato.getCountry()}', email='${candidato.getEmail()}'" +
                ", data_nascimento='${candidato.getBirthday()}' cep='${candidato.getCep()}'" +
                ", descricao_pessoal='${candidato.getPersonalDescription()}', password='${candidato.getPassword()}'"

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
