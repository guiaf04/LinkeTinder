package dao

import dao.enums.JDBCDatabases
import dao.factorys.JDBCDatabaseFactory
import dao.interfaces.JDBCInterface
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import model.Candidato
import model.Competencia

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class CandidateDAO  {
    JDBCDatabaseFactory databaseFactory = new JDBCDatabaseFactory()
    JDBCInterface jdbcInterface = databaseFactory.getDatabase(JDBCDatabases.PostgreSQL)

    int getSkillsID(Competencia competencia){
        Sql dbConnection = jdbcInterface.connect()

        GroovyRowResult row = dbConnection.firstRow("SELECT id FROM competencia WHERE nome = ?", [competencia.getNome()])
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

    List<Candidato> listar() {
        Sql conn = jdbcInterface.connect()
        List<Candidato> result = new ArrayList<>()

        try {
            conn.eachRow("SELECT * FROM candidato") { row ->
                Candidato candidato = new Candidato()
                candidato.setNome(row[1] as String)
                candidato.setSobrenome(row[2] as String)
                candidato.setDatanascimento(row[3] as String)
                candidato.setEmail(row[4] as String)
                candidato.setCpf(row[5] as String)
                candidato.setPais(row[6] as String)
                candidato.setCep(row[7] as String)
                candidato.setDescricaopessoal(row[8] as String)
                candidato.setSenha(row[9] as String)

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
