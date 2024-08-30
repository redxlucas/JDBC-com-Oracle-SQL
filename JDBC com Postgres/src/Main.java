import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Oracle database = new Oracle("a2023000972", "a1234");
        Scanner scanner = new Scanner(System.in);

        if(database.getConnection() != null){
            database.getOne(2);

            System.out.println("\nNome: " + database.getName()
            + "\nQuantidade: " + database.getQuantity()
            + "\nValor por unidade: " + database.getValue());
        }
    }
}