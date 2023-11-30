package com.example.demo.Service;

import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GreetingService {

    public static JSONObject getGreetingService(JSONObject jsonRequest) {
        JSONObject response = new JSONObject();
        Connection connection = null;

        try {
            String name = jsonRequest.optString("name", "Guest");
            String greeting = "Hello, " + name + "!";

            response.put("greeting", greeting);

            // JDBC Connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://mysql.servicediscovery.cloud/bsrv?autoReconnect=true&tcpKeepAlive=true&useUnicode=true&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&useSSL=false";
            String username = "bsrv";
            String password = "bsrv";
            connection = DriverManager.getConnection(url, username, password);

            // Execute SQL query
            String query = "SELECT * FROM TestVasu;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery()) {

                // Process the result set
                JSONArray tableData = new JSONArray();
                System.err.println(resultSet);
                while (resultSet.next()) {
                    // Retrieve data from the result set
                    int id = resultSet.getInt("id");
                    String columnName = resultSet.getString("name");

                    JSONObject rowData = new JSONObject();
                    rowData.put("id", id);
                    rowData.put("name", columnName);
                    tableData.put(rowData);
                }

                response.put("tableData", tableData);
            }

        } catch (Exception e) {
            System.err.println("Error in GreetingService: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.err.println("Error closing database connection: " + e.getMessage());
            }
        }

        return response;
    }
}
