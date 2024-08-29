import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Oracle database = new Oracle("a2023000972", "a1234");
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n  -- Menu -- ");
        System.out.println("\n1 - Adicionar funcionário\n2 - Buscar funcionário\n3 - Listar todos funcionários\n4 - Remover funcionário\n5 - Sair\n");
        int option = scanner.nextInt();

        switch (option) {

        
            default:
                break;
        }
    }
}
