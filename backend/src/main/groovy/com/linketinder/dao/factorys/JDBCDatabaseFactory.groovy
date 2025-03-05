package com.linketinder.dao.factorys

class JDBCDatabaseFactory {
    static com.linketinder.dao.interfaces.JDBCInterface getDatabase(com.linketinder.dao.enums.JDBCDatabases database){
        if (database == null)
            return null

        switch (database){
            case com.linketinder.dao.enums.JDBCDatabases.PostgreSQL:
                return new com.linketinder.dao.sample.PostgresJDBCSample()
            default:
                println("Banco inválido")
                return null
        }
    }
}
