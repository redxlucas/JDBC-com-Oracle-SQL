import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    public void insert(Integer id, String name, Double price, Integer dept){
        Oracle database = new Oracle("a2023000972", "a1234");
        
        Connection connection = database.getConnection();
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO Func"
            + "(func_id, nome, salario, dept_id) " 
            + "VALUES (?, ?, ?, ?)";

        try{
            preparedStatement = connection.prepareStatement(insertTableSQL);

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setDouble(3, price);
            preparedStatement.setInt(4, dept);

            preparedStatement.executeUpdate();
            System.out.println("Record is inserted into Func table!");

        } catch(SQLException e){
            System.out.println("Erro 1!");
            e.printStackTrace();
        }finally{
            try{
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();

            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void getOne(int id){
        Oracle database = new Oracle("a2023000972", "a1234");
        Connection connection = database.getConnection();
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT * FROM Func WHERE func_id = ?";

        try{
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                /*this.setNome(resultSet.getString("nome"));
                this.setSalario(resultSet.getDouble("salario"));
                this.setDept(resultSet.getInt("dept_id"));*/
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            try{
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();

            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public boolean delete(int id){
        Oracle database = new Oracle("a2023000972", "a1234");
        Connection connection = database.getConnection();
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "DELETE FROM Func WHERE func_id = ?";

        try {
            preparedStatement = connection.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try{
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();

            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return true;
    } 
}
