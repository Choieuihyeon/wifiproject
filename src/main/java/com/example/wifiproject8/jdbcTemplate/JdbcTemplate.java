package com.example.wifiproject8.jdbcTemplate;

import java.sql.*;

public class JdbcTemplate {
    public Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        String dbUrl = "/C:/dev/IntelliJ IDEA 2022.3.1/wifiproject7/wifiservice.db";

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void connectionClose(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void preparedStatementClose(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resultSetClose(ResultSet resultSet) {
        try {
            if (resultSet != null && !resultSet.isClosed()) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
