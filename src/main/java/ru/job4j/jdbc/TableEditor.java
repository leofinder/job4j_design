package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("driver_class"));
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, username, password);
    }

    private void executeStatement(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s(%s);",
                tableName,
                "id SERIAL PRIMARY KEY");
        executeStatement(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format(
                "DROP TABLE %s;",
                tableName);
        executeStatement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s ADD COLUMN %s %s;",
                tableName,
                columnName,
                type);
        executeStatement(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s;",
                tableName,
                columnName);
        executeStatement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName,
                columnName,
                newColumnName);
        executeStatement(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
            try (TableEditor tableEditor = new TableEditor(config)) {
                String tableName = "users";
                tableEditor.createTable(tableName);
                System.out.println(tableEditor.getTableScheme(tableName));
                tableEditor.addColumn(tableName, "name", "text");
                System.out.println(tableEditor.getTableScheme(tableName));
                tableEditor.renameColumn(tableName, "name", "username");
                System.out.println(tableEditor.getTableScheme(tableName));
                tableEditor.dropColumn(tableName, "username");
                System.out.println(tableEditor.getTableScheme(tableName));
                tableEditor.dropTable(tableName);
            }
        }
    }
}