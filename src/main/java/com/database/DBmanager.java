package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;

public class DBmanager {
	public Connection getConnection() throws ClassNotFoundException, SQLException, IOException {

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbUrl = "jdbc:mysql://localhost:3306/TrainingDB";
            String dbUser = "root";
            String dbPassword = "P@ssw0rd2006";

            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return connection;
    }

}
