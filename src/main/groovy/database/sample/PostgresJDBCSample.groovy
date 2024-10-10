package database.sample

import database.interfaces.JDBCInterface
import groovy.sql.Sql

import java.sql.SQLException

class PostgresJDBCSample implements JDBCInterface{
    Sql connect() {
        Map dbParams = [url       : "jdbc:postgresql://localhost:5432/linketinder"
                         , user    : "postgres"
                         , password: "postgres"
                         , driver  : "org.postgresql.Driver"]

        try {
            println("Banco conectado!")
            return Sql.newInstance(dbParams)
        } catch (Exception e) {
            e.printStackTrace()
            if (e instanceof ClassNotFoundException) {
                System.err.println("Verifique o driver de conexão.")
            } else {
                System.err.println("Verifique se o servidor está ativo")
            }

            System.exit(-42)
            return null
        }
    }

    void disconnect(Sql conn) {
        if (conn != null) {
            try {
                conn.close()
            } catch (SQLException e) {
                e.printStackTrace()
            }
        }
    }

}
