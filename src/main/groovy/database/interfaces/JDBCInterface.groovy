package database.interfaces

import groovy.sql.Sql

interface JDBCInterface {
    Sql connect();

    void disconnect(Sql conn);
}