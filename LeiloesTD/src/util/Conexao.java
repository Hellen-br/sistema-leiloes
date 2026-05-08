package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL    = "jdbc:mysql://localhost:3306/uc11";
    private static final String USUARIO = "root";
    private static final String SENHA   = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL não encontrado.", e);
        }
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
