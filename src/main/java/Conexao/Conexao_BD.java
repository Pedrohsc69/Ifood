package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao_BD {
    private static final String url = "jdbc:mysql://localhost:3306/ifood";
    private static final String user = "root";
    private static final String password = "C@valcanti123#";

    private static Connection conn;

    public static Connection getConexao(){
        try {
            if (conn == null){
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            } else {
                return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
