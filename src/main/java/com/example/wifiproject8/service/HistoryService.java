package com.example.wifiproject8.service;


import com.example.wifiproject8.domain.HistoryInfo;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.sql.DriverManager.getConnection;

public class HistoryService {
    public void insertHistory(HistoryInfo historyInfo) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.KOREA);

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String dbUrl = "/C:/dev/IntelliJ IDEA 2022.3.1/wifiproject7/wifiservice.db";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection("jdbc:sqlite:" + dbUrl);

            String query = "INSERT INTO HISTORY_INFO(X, Y, INQDATE) VALUES (?, ?, ?);";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, historyInfo.getX());
            preparedStatement.setString(2, historyInfo.getY());
            preparedStatement.setString(3, simpleDateFormat.format(new Date()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<HistoryInfo> historyList() {
        List<HistoryInfo> historyInfoList = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String dbUrl = "/C:/dev/IntelliJ IDEA 2022.3.1/wifiproject7/wifiservice.db";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection("jdbc:sqlite:" + dbUrl);

            String query = "SELECT * FROM HISTORY_INFO ORDER BY ID DESC;";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String x = resultSet.getString("X");
                String y = resultSet.getString("Y");
                String inqDate = resultSet.getString("INQDATE");

                HistoryInfo historyInfo = new HistoryInfo();
                historyInfo.setId(id);
                historyInfo.setX(x);
                historyInfo.setY(y);
                historyInfo.setInqDate(inqDate);

                historyInfoList.add(historyInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return historyInfoList;
    }

    public int deleteHistory(int id) {
        HistoryInfo historyInfo = new HistoryInfo();
        int result = 0;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String dbUrl = "/C:/dev/IntelliJ IDEA 2022.3.1/wifiproject7/wifiservice.db";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbUrl);

            String query = "DELETE FROM HISTORY_INFO WHERE ID = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
