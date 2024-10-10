package database.interfaces

interface IJdbcCRUDSample {
    void create(List<String> fields, List<String> values, String database);

    void read(String database);

    void update(List<String> fields, List<String> values, int id, String database);

    void delete(int id, String database);
}