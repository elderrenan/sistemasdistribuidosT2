package CDB;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author E
 */
public class Conectar {

    private static final String user = "postgres";
    private static final String password = "esg@2704";
    private static final String database = "WebService";
    private static final String host = "localhost";
    //private static final String port = "5432";
    //private static final String driver = "com.psql.jdbc.Driver";

    /**
     * Método responsável por abrir uma conexão com o banco de dados postgres
     *
     * @return
     */
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://" + host +"/"+database;//+ ":" + port + "/" + database + "?user=" + user + "&password=" + password;

            con = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("FALHA AO SE CONECTAR AO DEBIAN");
        }

        return con;
    }
}
