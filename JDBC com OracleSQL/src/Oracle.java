import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

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

    /**
     * Método que conecta ao banco de dados Oracle utilizando JDBC.
     * @return Retorna conexão.
     */

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

    /**
     * Método que insere um item no banco de dados.
     * @param id : ID do item.
     * @param name : nome do item.
     * @param quantity : quantidade do item.
     * @param value : valor do item.
     */
    public void insert(Integer id, String name, Integer quantity, Double value){
        Oracle database = new Oracle(" ", " ");
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
            System.out.println("Item inserido com sucesso!");

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

    /**
     * Método que consulta item por ID no banco de dados.
     * @param id : Chave primária para buscar item no banco de dados.
     * @return Se item for encontrado, o retorna. Caso contrário, retorna null.
     */
    public Items getOne(int id){
        Oracle database = new Oracle(" ", " ");
        Connection connection = database.getConnection();
        PreparedStatement preparedStatement = null;
        Items items = null;

        String selectSQL = "SELECT * FROM Items WHERE id = ?";

        try{
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                items = new Items();
                items.setId(resultSet.getInt("id"));
                items.setName(resultSet.getString("name"));
                items.setQuantity(resultSet.getInt("quantity"));
                items.setValue(resultSet.getDouble("value"));
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

        return items;
    }

    /**
     * Método que retorna todos os itens do banco de dados.
     * @return Lista com todos itens.
     */
    public List<Items> getAll(){
        Oracle database = new Oracle(" ", " ");
        Connection connection = database.getConnection();
        Statement statement = null;

        List<Items> listItems = new ArrayList<>();
        String selectSQL = "SELECT * FROM Items ORDER BY id ASC";

        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSQL);
            while(resultSet.next()){
                Items items = new Items();
                items.setId(resultSet.getInt("id"));
                items.setName(resultSet.getString("name"));
                items.setQuantity(resultSet.getInt("quantity"));
                items.setValue(resultSet.getDouble("value"));
                listItems.add(items);
            }

        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            try{
                if(connection != null) connection.close();

            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        return listItems;
    }

    /**
     * Método que atualiza um item no banco de dados.
     * @param id : ID do item que será atualizado.
     * @param name : nome a ser atualizado do item.
     * @param quantity : quantidade a ser atualizada do item.
     * @param value : valor a ser atualizado do item.
     */
    public void update(Integer id, String name, Integer quantity, Double value){
        Oracle database = new Oracle(" ", " ");
        Connection connection = database.getConnection();
        PreparedStatement preparedStatement = null;

        String updateSQL = "UPDATE Items SET name = ?, quantity = ?, value = ? WHERE id = ?";

        try{
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setInt(4, id);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, quantity);
            preparedStatement.setDouble(3, value);
            preparedStatement.executeUpdate();

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

    /**
     * Método que remove itens do banco de dados.
     * @param id : Chave primária para buscar item no banco de dados.
     * @return Retorna se removeu item com êxito.
     */

    public boolean delete(int id){
        Oracle database = new Oracle(" ", " ");
        Connection connection = database.getConnection();
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "DELETE FROM Items WHERE id = ?";

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
