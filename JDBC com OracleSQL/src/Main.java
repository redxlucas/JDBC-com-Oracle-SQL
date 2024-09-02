import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Oracle database = new Oracle("a2023000972", "a1234");
        Scanner scanner = new Scanner(System.in);
        Integer id, quantity;
        String name;
        Double value;

        System.out.println("\n1 - Adicionar\n2 - Consultar por ID\n3 - Consultar todos\n4 - Atualizar\n5 - Remover\n");
        int input = scanner.nextInt();
        if(database.getConnection() != null){
           switch(input){
                case 1:
                    id = scanner.nextInt();
                    scanner.nextLine();
                    name = scanner.nextLine();
                    quantity = scanner.nextInt();
                    value = scanner.nextDouble();
                    scanner.nextLine();
                    database.insert(id, name, quantity, value);
                    break;

                case 2:
                    id = scanner.nextInt();
                    Items items = database.getOne(id);
            
                    System.out.println("\nID: " + items.getId()
                    + "\nNome: " + items.getName()
                    + "\nQuantidade: " + items.getQuantity()
                    + "\nValor por unidade: " + items.getValue());
                    break;

                case 3:
                    List<Items> listItems = database.getAll();

                    for (Items allItems : listItems) {
                        System.out.println("\nID: " + allItems.getId()
                        + "\nNome: " + allItems.getName()
                        + "\nQuantidade: " + allItems.getQuantity()
                        + "\nValor por unidade: " + allItems.getValue());
                    }
                    break;

                case 4:
                    id = scanner.nextInt();
                    scanner.nextLine();
                    name = scanner.nextLine();
                    quantity = scanner.nextInt();
                    value = scanner.nextDouble();

                    database.update(id, name, quantity, value);
                    break;

                case 5:
                    id = scanner.nextInt();
                    if(database.delete(id)){
                        System.out.println("Item removido com sucesso!");
                    }
                    else{
                        System.out.println("ID" + id + "não foi encontrado.");
                    }
                    break;
            }
        }

        scanner.close();
    }
}