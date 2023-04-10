package client;

import adt.SortedArrayList;
import adt.SortedListInterface;
import entity.Menu;
import entity.Order;
import entity.Pax;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * OrderManager - A sub client class to implement operation on entity and ADT
 *
 * @author Low Zhi Yi
 */
public class OrderManager {

    private SortedListInterface<Order> orderList = new SortedArrayList<>();
    private SortedListInterface<Menu> menuList = new SortedArrayList<>();
    private SortedListInterface<Pax> paxList = new SortedArrayList<>();

    private Scanner input = new Scanner(System.in);
    private DecimalFormat dp2 = new DecimalFormat("0.00");

    // Constructor: dummy data for menu
    public OrderManager() {
        databaseInit();
        loadList();

        // for the first time running program
        if (menuList.getNumberOfEntries() == 0) {
            System.out.println("No product in the database");
            System.out.println("Default data is use to display");
            // cuisine
            menuList.add(new Menu(7, "Barbeque", 30.5));
            menuList.add(new Menu(8, "Chinese", 38.8));
            menuList.add(new Menu(9, "Indian", 26));
            menuList.add(new Menu(10, "Japanese", 36.5));
            menuList.add(new Menu(11, "Malay", 35));
            menuList.add(new Menu(12, "Thai", 36.5));
            menuList.add(new Menu(13, "Western", 45.9));
            menuList.add(new Menu(14, "Finger Food", 20));
            // occasion
            menuList.add(new Menu(1, "Baby Full Moon", 25.9));
            menuList.add(new Menu(2, "Birthday", 35));
            menuList.add(new Menu(3, "Chinese New Year", 27));
            menuList.add(new Menu(4, "Christmas", 29));
            menuList.add(new Menu(5, "Ramadan / Raya", 32));
            menuList.add(new Menu(6, "Wedding", 45));
        }
    }

    // Create database
    private void databaseInit() {
        try {
            File file1 = new File("database/order.txt");
            if (!file1.exists()) {
                file1.createNewFile();
            }
            File file2 = new File("database/menu.txt");
            if (!file2.exists()) {
                file2.createNewFile();
            }
            File file3 = new File("database/pax.txt");
            if (!file3.exists()) {
                file3.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadList() {
        File[] files = new File("database").listFiles();
        for (File file : files) {
            System.out.println(file.getName());

            // If the files exists, load all data into driver program
            if (file.exists()) {
                try {
                    FileInputStream fileInput = new FileInputStream("database/" + file.getName());
                    ObjectInputStream objInput = new ObjectInputStream(fileInput);

                    switch (file.getName()) {
                        case "order.txt" ->
                            orderList = (SortedArrayList<Order>) objInput.readObject();
                        case "menu.txt" ->
                            menuList = (SortedArrayList<Menu>) objInput.readObject();
                        case "pax.txt" ->
                            paxList = (SortedArrayList<Pax>) objInput.readObject();
                        default ->
                            System.out.println("Unknown file to load into arraylist " + file.getName());
                    }
                    objInput.close();
                    System.out.println("The progress of the programs is loaded from the database.");
                } catch (EOFException e) {
                    // First time using this system
                    System.out.println("The database is empty.");
                    System.out.println("Progress will be stored in database.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void storeArrayList() {
        File[] files = new File("database").listFiles();

        for (File file : files) {
            if (file.exists()) { //If the files exists, load all data into driver program
                try {
                    FileOutputStream fileOutput = new FileOutputStream("database/" + file.getName());
                    ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
                    switch (file.getName()) {
                        case "order.txt" ->
                            objOutput.writeObject(orderList);
                        case "menu.txt" ->
                            objOutput.writeObject(menuList);
                        case "pax.txt" ->
                            objOutput.writeObject(paxList);
                        default ->
                            System.out.println("Unknown file stored in database to be serialized in " + file.getName());
                    }
                    objOutput.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Progress has been stored in database.");
    }

    public int addNewOrder() {
        int id;
        boolean validId;
        Order newEntry;

        System.out.println("\n\n<-- ORDERING FOR CATERING MEAL SERVICE -->");
        do {
            Order lastOrder = orderList.getEntry(orderList.getNumberOfEntries());
            if (lastOrder == null) {
                System.out.println("\nSuggested id: 1");
            } else {
                System.out.println("\nSuggested id: " + (lastOrder.getOrderId() + 1));
            }

            System.out.print("\nEnter order ID: ");
            id = input.nextInt();
            input.nextLine();   // clear buffer
            newEntry = orderList.getEntry(new Order(id));

            validId = true;
            if (newEntry == null) {
                orderList.add(new Order(id));
            } else {
                System.out.println("Order ID exists!");
                validId = false;
            }
        } while (validId == false);

        // Input event details
        setEventDate(id);
        setEventTime(id);
        setEventAddress(id);

        return id;
    }

    public void addNewMenu() {
        int id;
        Menu newEntry;

        System.out.println("\n\n<-- ADDING NEW MENU -->");
        do {
            Menu lastMenu = menuList.getEntry(menuList.getNumberOfEntries());
            if (lastMenu == null) {
                System.out.println("\nSuggested id: 1");
            } else {
                System.out.println("\nSuggested id: " + (lastMenu.getMenuId() + 1));
            }

            System.out.print("\nEnter menu ID: ");
            id = input.nextInt();
            input.nextLine();   // clear buffer
            newEntry = menuList.getEntry(new Menu(id));

            if (newEntry != null) {
                System.out.println("Menu ID exists!");
            }
        } while (newEntry != null);

        boolean validName;
        String menuType;
        do {
            System.out.print("\nMenu Name: ");
            menuType = input.nextLine();

            validName = true;
            for (int i = 1; i <= menuList.getNumberOfEntries(); i++) {
                if (menuType.toUpperCase().equals(menuList.getEntry(i).getMenuType().toUpperCase())) {
                    System.out.println("Menu Name exists!");
                    validName = false;
                    break;
                }
            }
        } while (validName == false);

        double price;
        boolean priceValid;
        do {
            priceValid = true;
            System.out.print("\nPrice per pax: ");
            price = input.nextDouble();
            input.nextLine();   // clear buffer

            if (price < 0) {
                System.out.println("Invalid price!");
                priceValid = false;
            }
        } while (priceValid == false);

        // adding new package info into menu list
        menuList.add(new Menu(id, menuType, price));
        System.out.println("\nMenu added successfully!");
    }

    public void addMenuToOrder(int orderId) {
        Menu menu = null;
        int menuId;

        displayMenu();

        do {
            System.out.print("Enter Menu ID: ");
            menuId = input.nextInt();
            input.nextLine();   // clear buffer

            // to search if menu exists in Menu List
            menu = menuList.getEntry(new Menu(menuId));

            if (menu == null) {
                System.out.println("Invalid menu!\n");
            }
        } while (menu == null);

        // assign the menu to a customer order
        orderList.getEntry(new Order(orderId)).addMenuIntoOrder(menu);

        addPaxToOrderedMenu(orderId, menuId);
    }

    public void removeMenuFromOrder(int orderId) {
        System.out.println("\n<-- REMOVING a MENU from ORDER -->");
        System.out.print("Enter Menu ID to be removed from the order: ");
        int menuId = input.nextInt();
        input.nextLine();   // clear buffer

        Menu menu = menuList.getEntry(new Menu(menuId));
        if (menu != null) {
            orderList.getEntry(new Order(orderId)).removeMenuFromOrder(menu);
            removePaxFromOrderedMenu(orderId, menuId);
            System.out.println("Menu [" + menuId + "] removed from order.");
        } else {
            System.out.println("Menu not found in order!");
        }
    }

    public void editOrder() {
        System.out.println("\n\n<-- EDITING an ORDER -->");
        System.out.print("Enter the order ID to be edited: ");
        int orderId = input.nextInt();
        input.nextLine();   // clear buffer

        Order order = orderList.getEntry(new Order(orderId));

        if (order != null) {
            displayOneOrder(order);
            System.out.println();

            boolean validChoice;
            do {
                validChoice = true;

                System.out.println("\n[1] Edit event date");
                System.out.println("[2] Edit event time");
                System.out.println("[3] Edit event address");
                System.out.println("[4] Edit pax");
                System.out.println("[5] Add menu to order");
                System.out.println("[6] Remove menu to order");
                System.out.println("[0] Exit");

                System.out.print("\nSelect an option: ");
                int choice = input.nextInt();
                input.nextLine();   // clear buffer

                if (choice >= 1 && choice <= 6) {

                    switch (choice) {
                        case 1:
                            System.out.println("\n<-- Editing EVENT DATE -->");
                            setEventDate(orderId);
                            break;
                        case 2:
                            System.out.println("\n<-- Editing EVENT TIME -->");
                            setEventTime(orderId);
                            break;
                        case 3:
                            System.out.println("\n<-- Editing EVENT ADDRESS -->");
                            setEventAddress(orderId);
                            break;
                        case 4:
                            System.out.println("\n<-- Editing PAX -->");
                            System.out.print("Enter menu ID: ");
                            int menuId = input.nextInt();
                            input.nextLine();   // clear buffer

                            Menu menu = menuList.getEntry(new Menu(menuId));
                            Menu orderedMenu = order.getAllMenu().getEntry(menu);
                            if (orderedMenu != null) {
                                removePaxFromOrderedMenu(orderId, menuId);
                                addPaxToOrderedMenu(orderId, menuId);
                                System.out.println("Pax of menu [" + menuId + "] in order [" + orderId + "] is updated.");
                            } else {
                                System.out.println("Menu not found in order!");
                            }
                            break;
                        case 5:
                            System.out.println("\n<-- Adding MENU to ORDER -->");
                            addMenuToOrder(orderId);
                            break;
                        case 6:
                            removeMenuFromOrder(orderId);
                            break;
                    }
                } else if (choice != 0) {
                    System.out.println("Invalid choice!");
                    validChoice = false;
                }
            } while (validChoice == false);
        } else {
            System.out.println("Order not found!");
        }
    }

    public void editMenu() {
        System.out.println("\n\n<-- EDITING a Menu -->");
        System.out.print("Enter the menu ID to be edited: ");
        int menuId = input.nextInt();
        input.nextLine();   // clear buffer

        Menu menu = menuList.getEntry(new Menu(menuId));

        if (menu != null) {
            displayOneMenu(menu);
            System.out.println();

            boolean validChoice;
            do {
                validChoice = true;

                System.out.println("\n[1] Edit menu name");
                System.out.println("[2] Edit price of package");
                System.out.println("[0] Exit");

                System.out.print("\nSelect an option: ");
                int choice = input.nextInt();
                input.nextLine();   // clear buffer

                if (choice >= 1 && choice <= 2) {

                    switch (choice) {
                        case 1:
                            boolean validName;
                            String menuType;

                            System.out.println("< Editing MENU NAME >");
                            do {
                                System.out.print("\nEnter new menu name: ");
                                menuType = input.nextLine();

                                validName = true;
                                for (int i = 1; i <= menuList.getNumberOfEntries(); i++) {
                                    if (menuType.toUpperCase().equals(menuList.getEntry(i).getMenuType().toUpperCase())) {
                                        System.out.println("Menu Name exists!");
                                        validName = false;
                                        break;
                                    }
                                }
                            } while (validName == false);

                            menuList.getEntry(new Menu(menuId)).setMenuType(menuType);
                            break;
                        case 2:
                            System.out.println("< Editing MENU PRICE >");
                            System.out.print("\nEnter new price: ");
                            double price = input.nextDouble();
                            input.nextLine();   // clear buffer

                            menuList.getEntry(new Menu(menuId)).setPrice(price);
                            break;
                    }
                } else if (choice != 0) {
                    System.out.println("Invalid choice!");
                    validChoice = false;
                }
            } while (validChoice == false);
        } else {
            System.out.println("Menu item not found!");
        }
    }

    public void searchOrder() {
        System.out.println("\n\n<-- SEARCHING for an Order -->");
        System.out.print("Enter Order ID: ");
        int id = input.nextInt();
        input.nextLine();   // clear buffer

        Order order = orderList.getEntry(new Order(id));
        if (order != null) {
            displayOneOrder(order);
        } else {
            System.out.println("Order not found!");
        }
    }

    public void searchMenu() {
        System.out.println("\n\n<-- SEARCHING for an item in Menu -->");
        System.out.print("Enter Menu ID: ");
        int id = input.nextInt();
        input.nextLine();   // clear buffer

        Menu menu = menuList.getEntry(new Menu(id));
        if (menu != null) {
            displayOneMenu(menu);
        } else {
            System.out.println("Menu item not found!");
        }
    }

    public void displayOneOrder(Order order) {
        System.out.println("\n" + order);
        System.out.println("Menu packages\t: \n");
        System.out.println("MenuID\tPrice per pax\tPackage Name\t\tPax\tSubtotal");
        System.out.println("------\t-------------\t-------------------\t----\t-----------");
        for (int j = 1; j <= order.getAllMenu().getNumberOfEntries(); j++) {
            System.out.printf("%-38s", order.getAllMenu().getEntry(j));
            System.out.println(order.getAllPax().getEntry(j) + "\tRM"
                    + dp2.format(calcSubtotal(order.getOrderId(),
                            order.getAllMenu().getEntry(j).getMenuId())));
        }
    }

    public void displayOneMenu(Menu menu) {
        System.out.println("\nMenuID\tPrice per pax\tPackage Name");
        System.out.println("------\t-------------\t----------------");
        System.out.println(menu);
    }

    public void removeOrder() {
        System.out.println("\n\n<-- REMOVING an order -->");
        System.out.print("Enter Order ID: ");
        int id = input.nextInt();
        input.nextLine();   // clear buffer

        Order anOrder = orderList.getEntry(new Order(id));

        if (anOrder != null) {
            displayOneOrder(anOrder);
            
            System.out.print("\nAre you sure you want to remove this order? (Y/N): ");
            char delete = input.nextLine().charAt(0);

            if (delete == 'Y' || delete == 'y') {
                orderList.remove(anOrder);
                System.out.println("Order successfully removed!");
            } else {
                System.out.println("Order not deleted.");
            }
        } else {
            System.out.println("Order not found!");
        }
    }

    public void removeMenu() {
        System.out.println("\n\n<-- REMOVING a menu item -->");
        System.out.print("Enter Menu ID: ");
        int id = input.nextInt();
        input.nextLine();   // clear buffer

        Menu menuItem = menuList.getEntry(new Menu(id));

        if (menuItem != null) {
            displayOneMenu(menuItem);
            
            System.out.print("\nAre you sure you want to remove this menu? (Y/N): ");
            char delete = input.nextLine().charAt(0);

            if (delete == 'Y' || delete == 'y') {
                menuList.remove(menuItem);
                System.out.println("Menu successfully removed!");
            } else {
                System.out.println("Menu not deleted.");
            }
        } else {
            System.out.println("Menu Item not found!");
        }
    }

    public void displayOrder() {
        if (orderList.getNumberOfEntries() != 0) {
            System.out.print("\n\n<-- ORDER LIST -->");
            for (int i = 1; i <= orderList.getNumberOfEntries(); i++) {
                System.out.println("\n\n" + orderList.getEntry(i));
                System.out.println("Menu packages\t: \n");
                System.out.println("MenuID\tPrice per pax\tPackage Name\t\tPax\tSubtotal");
                System.out.println("------\t-------------\t-------------------\t----\t-----------");
                for (int j = 1; j <= orderList.getEntry(i).getAllMenu().getNumberOfEntries(); j++) {
                    System.out.printf("%-38s", orderList.getEntry(i).getAllMenu().getEntry(j));
                    System.out.println(orderList.getEntry(i).getAllPax().getEntry(j) + "\tRM"
                            + dp2.format(calcSubtotal(orderList.getEntry(i).getOrderId(),
                                    orderList.getEntry(i).getAllMenu().getEntry(j).getMenuId())));
                }
            }
        } else {
            System.out.println("\n< No order in list! >");
        }
    }

    public void displayMenu() {
        if (menuList.getNumberOfEntries() != 0) {
            System.out.println("\n<-- MENU LIST -->\n");
            System.out.println("MenuID\tPrice per pax\tPackage Name");
            System.out.println("------\t-------------\t----------------");
            System.out.println(menuList);
        } else {
            System.out.println("\n< No menu in list! >");
        }
    }

    public void setEventDate(int orderId) {
        boolean validDate;
        int day, month, year;

        do {
            System.out.print("Enter event date(dd MM yyyy): ");
            day = input.nextInt();
            month = input.nextInt();
            year = input.nextInt();
            input.nextLine();   // clear buffer

            // validation for date input
            validDate = dateValidation(day, month, year);
        } while (validDate == false);

        orderList.getEntry(new Order(orderId)).setEventDate(day, month, year);
    }

    public void setEventTime(int orderId) {
        boolean validTime;
        int startHour, startMin, endHour, endMin;

        do {
            System.out.println("\nEnter event time in 24 hours format (HH MM).");
            System.out.print("Start time: ");
            startHour = input.nextInt();
            startMin = input.nextInt();
            input.nextLine();   // clear buffer
            System.out.print("End time: ");
            endHour = input.nextInt();
            endMin = input.nextInt();
            input.nextLine();   // clear buffer

            // validation for time input
            validTime = timeValidation(startHour, startMin, endHour, endMin);
        } while (validTime == false);

        orderList.getEntry(new Order(orderId)).setEventStartTime(startHour, startMin);
        orderList.getEntry(new Order(orderId)).setEventEndTime(endHour, endMin);
    }

    public void setEventAddress(int orderId) {
        System.out.print("\nEnter event address: ");
        String address = input.nextLine();

        orderList.getEntry(new Order(orderId)).setEventAddress(address);
    }

    public void addPaxToOrderedMenu(int orderId, int menuId) {
        int pax;
        do {
            System.out.print("\nEnter pax: ");
            pax = input.nextInt();
            input.nextLine();   // clear buffer

            if (pax < 0) {
                System.out.println("Invalid pax!");
            } else if (pax < 20) {
                System.out.println("The minimum pax requirement is 20.");
            }
        } while (pax < 20);

        int paxId = paxList.getNumberOfEntries() + 1;
        paxList.add(new Pax(paxId, pax, orderId, menuId));

        Pax paxItem = paxList.getEntry(new Pax(paxId));
        orderList.getEntry(new Order(orderId)).addPaxToMenu(paxItem);
    }

    public void removePaxFromOrderedMenu(int orderId, int menuId) {
        Pax paxItem = paxList.getEntry(new Pax(orderId, menuId));
        orderList.getEntry(new Order(orderId)).removePaxFromMenu(paxItem);
    }

    public double calcSubtotal(int orderId, int menuId) {
        int pax = paxList.getEntry(new Pax(orderId, menuId)).getPax();
        double price = menuList.getEntry(new Menu(menuId)).getPrice();
        double result = price * (double) pax;

        return result;
    }

    public boolean dateValidation(int day, int month, int year) {
        boolean validDate = true;
        if (year >= 2020 && year <= 2030) {
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    if (day < 1 || day > 31) {
                        System.out.println("Invalid day!");
                        validDate = false;
                    }
                    break;
                case 2:
                    if (day < 1 || day > 29) {
                        System.out.println("Invalid day!");
                        validDate = false;
                    } else if (day == 29) {
                        if (year % 4 != 0) {
                            System.out.println("Invalid day!");
                            validDate = false;
                        }
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    if (day < 1 || day > 30) {
                        System.out.println("Invalid day!");
                        validDate = false;
                    }
                    break;
                default:
                    System.out.println("Invalid month!");
                    validDate = false;
            }
        } else {
            System.out.println("Invalid year!");
            validDate = false;
        }
        return validDate;
    }

    public boolean timeValidation(int startHour, int startMin, int endHour, int endMin) {
        boolean validTime = true;
        if (startHour < 24 && endHour < 24 && startMin < 60 && endMin < 60
                && startHour >= 0 && endHour >= 0 && startMin >= 0 && endMin >= 0) {
            if (startHour == endHour) {
                if (startMin > endMin) {
                    System.out.println("Invalid time!");
                    validTime = false;
                }
            } else if (startHour > endHour) {
                System.out.println("Invalid time!");
                validTime = false;
            }
        } else {
            System.out.println("Invalid time!");
            validTime = false;
        }
        return validTime;
    }
}
