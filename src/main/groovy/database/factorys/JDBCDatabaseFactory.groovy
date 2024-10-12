package database.factorys

import database.enums.JDBCDatabases
import database.interfaces.JDBCInterface
import database.sample.PostgresJDBCSample

class JDBCDatabaseFactory {
    static JDBCInterface getDatabase(JDBCDatabases database){
        if (database == null)
            return null

        switch (database){
            case JDBCDatabases.PostgreSQL:
                return new PostgresJDBCSample()
            default:
                println("Banco inválido")
                return null
        }
    }
}
