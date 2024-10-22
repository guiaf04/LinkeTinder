package dao

import dao.enums.JDBCDatabases
import dao.factorys.JDBCDatabaseFactory
import dao.interfaces.JDBCInterface
import groovy.sql.Sql
import model.Empresa
import model.Empresa
import dao.interfaces.ISampleDAO
import dao.sample.PostgreSampleDAO

import java.sql.SQLException

class EmployeeDAO{
    JDBCDatabaseFactory databaseFactory = new JDBCDatabaseFactory()
    JDBCInterface jdbcInterface = databaseFactory.getDatabase(JDBCDatabases.PostgreSQL)

    boolean criar(Empresa empresa) {
        Sql dbConnection = jdbcInterface.connect()

        List<String> values = empresa.getProperties().findAll {!it.key.toString().equalsIgnoreCase("class")}.values() as List<String>

        String query = "INSERT INTO empresa " +
                "(nome, email, cnpj, pais, cep, descricao, senha) " +
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
            conn.eachRow("SELECT * FROM empresa") { row ->
                result.add(row.toString())
            }
        } catch (SQLException e) {
            println(e.stackTrace)
            println "Não foi possível executar a query, verifique se os valores passados foram corretos"
        }

        jdbcInterface.disconnect(conn)

        return result
    }

    String getElementByCNPJ(Empresa empresa) {
        Sql conn = jdbcInterface.connect()

        String query = "SELECT * FROM " +
                "empresa " +
                "WHERE cnpj='${empresa.getCnpj()}'"

        String result = ""

        try {
            conn.eachRow(query) { row ->
                result = row.toString()
            }
        }catch (SQLException e){
            e.printStackTrace()
        }

        return result
    }

    boolean atualizar(Empresa empresa) {
        Sql conn = jdbcInterface.connect()

        String setClause = "nome='${empresa.getNome()}', cep='${empresa.getCep()}'" +
                ", pais='${empresa.getPais()}', email='${empresa.getEmail()}'" +
                ", descricao='${empresa.getDescricao()}', senha='${empresa.getSenha()}'"

        String query = "UPDATE empresa SET ${setClause} WHERE cnpj='${empresa.getCnpj()}'"

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

    boolean deletar(Empresa empresa) {
        Sql conn = jdbcInterface.connect()

        String query = "DELETE FROM empresa WHERE cnpj=${empresa.getCnpj()}"

        try {
            conn.execute(query)
        } catch (SQLException e) {
            e.printStackTrace()
            println "Não foi possível executar a query, verifique se os valores passados foram corretos"
            return false
        }
        println "Empresa was deleted!"

        jdbcInterface.disconnect(conn)
        return true
    }
}
