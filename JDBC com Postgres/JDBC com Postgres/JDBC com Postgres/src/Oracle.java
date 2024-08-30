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

    private String name;
    private Integer quantity;
    private Double value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

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

    public void insert(Integer id, String name, Integer quantity, Double value){
        Oracle database = new Oracle("a2023000972", "a1234");
        
        Connection connection = database.getConnection();
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO Items"
            + "(id, name, quantity, value) " 
            + "VALUES (?, ?, ?, ?)";

        try{
            preparedStatement = connection.prepareStatement(insertTableSQL);

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setDouble(4, value);

            preparedStatement.executeUpdate();
            System.out.println("Record is inserted into Items table!");

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

        String selectSQL = "SELECT * FROM Items WHERE id = ?";

        try{
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                this.setName(resultSet.getString("name"));
                this.setQuantity(resultSet.getInt("quantity"));
                this.setValue(resultSet.getDouble("value"));
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