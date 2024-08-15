package PasswordManagerClasses;

import java.sql.*;

public class Conn {

    Connection c;
    Statement s;

    public Conn() {
        try {
            // Establishing connection
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/PasswordManager", "root", "Feroz123@");
            // Creating a statement
            s = c.createStatement();
        } catch (SQLException e) {
            // Print error message if connection fails
            e.printStackTrace();
        }
    }
}
 