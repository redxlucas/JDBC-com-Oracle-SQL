import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Oracle database = new Oracle("a2023000972", "a1234");
        
        Connection connection = database.getConnection();
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO Func"
            + "(func_id, nome, salario, dept_id) VALUES"
            + "(?, ?, ?, ?)";

        try{
            preparedStatement = connection.prepareStatement(insertTableSQL);

            preparedStatement.setInt(1, 6);
            preparedStatement.setString(2, "Java");
            preparedStatement.setDouble(3, 1000);
            preparedStatement.setInt(4, 1);

            preparedStatement.executeUpdate();
            System.out.println("Record is inserted into Func table!");

        } catch(SQLException e){
            System.out.println("Erro 1!");
            e.getStackTrace();
        } finally{
            try{
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();

            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
