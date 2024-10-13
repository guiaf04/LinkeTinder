package model.dao.interfaces

interface IJdbcCRUDSample {
    boolean create(List<String> fields, List<String> values, String database);

    List<String> read(String database)

    List<String> readGeneric(String database, String field, String value)

    boolean update(List<String> fields, List<String> values, int id, String database);

    boolean delete(int id, String database);
}