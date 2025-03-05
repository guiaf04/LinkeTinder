package com.linketinder.dao

import com.linketinder.dao.enums.JDBCDatabases
import com.linketinder.dao.factorys.JDBCDatabaseFactory
import com.linketinder.dao.interfaces.JDBCInterface
import groovy.sql.Sql
import com.linketinder.model.Empresa

import java.sql.SQLException

class EmployeeDAO{
    JDBCDatabaseFactory databaseFactory = new JDBCDatabaseFactory()
    JDBCInterface jdbcInterface = databaseFactory.getDatabase(JDBCDatabases.PostgreSQL)

    boolean criar(Empresa empresa) {
        Sql dbConnection = jdbcInterface.connect()

        List<String> values = empresa.getProperties().findAll {!it.key.toString().equalsIgnoreCase("class")}.values() as List<String>

        String query = "INSERT INTO empresa " +
                "(firstName, email, cnpj, country, cep, descricao, password) " +
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

    List<Empresa> listar() {
        Sql conn = jdbcInterface.connect()
        List<Empresa> result = new ArrayList<>()

        try {
            conn.eachRow("SELECT * FROM empresa") { row ->
                Empresa empresa = new Empresa()
                empresa.setNome(row[1] as String)
                empresa.setEmail(row[2] as String)
                empresa.setCnpj(row[3] as String)
                empresa.setPais(row[4] as String)
                empresa.setCep(row[5] as String)
                empresa.setDescricao(row[6] as String)
                empresa.setSenha(row[7] as String)

                result.add(empresa)
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

        String setClause = "firstName='${empresa.getNome()}', cep='${empresa.getCep()}'" +
                ", country='${empresa.getPais()}', email='${empresa.getEmail()}'" +
                ", descricao='${empresa.getDescricao()}', password='${empresa.getSenha()}'"

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
