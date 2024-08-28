import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Oracle {
    private String user = " ";
    private String password = " ";
    private String server = "oracle.canoas.ifrs.edu.br";
    private int port = 1521;

    private Connection connection = null;

    public Oracle(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Connection getConnection(){
        if(connection == null){
            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@" + this.server + ":" + this.port + ":XE",
                    this.user, this.password);
            } catch(ClassNotFoundException e){
                e.printStackTrace();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        return connection;
    }
}
