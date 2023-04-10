package client;

import java.util.Scanner;
import java.util.InputMismatchException;

/** OrderDriver - A main client class for user interface.
  * @author Low Zhi Yi
  */

public class OrderDriver {

    private OrderManager manageOrder = new OrderManager();
    private Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        new OrderDriver().runApp();
    }

    public void runApp() {
        int choice = 0;
        do {
            // display choices
            System.out.println("\n< Booking for Catering Service >\n");
            System.out.println("\t-- ORDER --");
            System.out.println(" [1] Add a new order");        // Add a new order & assign menu + pax to order
            System.out.println(" [2] Edit order");             // Edit order details
            System.out.println(" [3] Search for an order");    // Search and display order details by order id
            System.out.println(" [4] Display all orders");     // Display all orders with details
            System.out.println(" [5] Remove order");           // Remove an order record including all details
            System.out.println("\n\t-- MENU --");
            System.out.println(" [6] Add menu");               // Add a new menu
            System.out.println(" [7] Edit menu");              // Edit menu details
            System.out.println(" [8] Search for a menu item"); // Search and display menu details by menu id
            System.out.println(" [9] Display menu list");      // Display all menu
            System.out.println("[10] Remove a menu item");     // Remove a menu item including all details
            System.out.println("\n [0] End system");           // Exit

            // get user's choice
            try {
                System.out.print("\nSelect an option: ");
                choice = input.nextInt();
                input.nextLine();   // clear buffer
            } catch (InputMismatchException e) {
                choice = -1;
                input.nextLine();   // clear buffer
            }

            // call functions based on user's choice
            switch (choice) {
                case 1:
                    // Create order and return the id
                    int orderId = manageOrder.addNewOrder();
                    promptEnterKey();

                    // Assign menu(s) to order
                    char moreMenu;
                    do {
                        manageOrder.addMenuToOrder(orderId);

                        System.out.print("Do you want to add more menu? (Y/N): ");
                        moreMenu = input.nextLine().charAt(0);
                    } while (moreMenu == 'Y' || moreMenu == 'y');

                    System.out.println("\nOrder added successfully!");
                    promptEnterKey();
                    break;
                case 2:
                    manageOrder.editOrder();
                    promptEnterKey();
                    break;
                case 3:
                    manageOrder.searchOrder();
                    promptEnterKey();
                    break;
                case 4:
                    manageOrder.displayOrder();
                    promptEnterKey();
                    break;
                case 5:
                    manageOrder.displayOrder();
                    manageOrder.removeOrder();
                    promptEnterKey();
                    break;
                case 6:
                    manageOrder.addNewMenu();
                    promptEnterKey();
                    break;
                case 7:
                    manageOrder.editMenu();
                    promptEnterKey();
                    break;
                case 8:
                    manageOrder.searchMenu();
                    promptEnterKey();
                    break;
                case 9:
                    manageOrder.displayMenu();
                    promptEnterKey();
                    break;
                case 10:
                    manageOrder.displayMenu();
                    manageOrder.removeMenu();
                    promptEnterKey();
                    break;
                case 0:
                    manageOrder.storeArrayList();
                    System.out.println("-- EXIT SYSTEM --");
                    break;
                default:
                    System.out.println("Invalid choice!\n");
            }
        } while (choice != 0);
    }

    // Method to read a random string from user to proceed the program
    public static void promptEnterKey() {
        Scanner enterKey = new Scanner(System.in);
        System.out.println();
        System.out.print("Press \"ENTER\" to continue...");
        enterKey.nextLine();
        System.out.println();
    }
}
