package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;


public class Conexao {
    private static String url;
    private static String user;
    private static String password;


    static {
        try (InputStream in = Conexao.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties p = new Properties();
            if (in == null) throw new RuntimeException("db.properties não encontrado em resources");
            p.load(in);
            url = p.getProperty("db.url");
            user = p.getProperty("db.user");
            password = p.getProperty("db.password");
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}