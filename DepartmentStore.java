/*
Daniel Pelepelin
112096007
Rec 30 Section 2
*/

import java.util.Scanner;

/**
 * Main DepartmentStore driver Class that prompts the user for menu options.
 */
public class DepartmentStore {
    public static void main(String[] args) {

        ItemList items = new ItemList();

        String table = String.format("%-25s%-25s%-25s%-25s%-25s\n%s",
                "Item Name", "RFID", "Original Location", "Current Location", "Price",
                "----------------------------------------------------------------------------------------------------------");

        String name;
        double price;
        String rfidTagNumber;
        String originalLocation;
        String currentLocation;

        String input = "";
        System.out.println("Welcome!");
        while (!input.equalsIgnoreCase("Q")) { //Loop to constantly run menu until user Quits.
            System.out.println(
                    "\nC - Clean store" +
                    "\nI - Insert an item into the list" +
                    "\nL - List by location" +
                    "\nM - Move an item in the store" +
                    "\nO - Checkout" +
                    "\nP - Print all items in store" +
                    "\nR - Print by RFID tag number" + //Optional Extra Credit
                    "\nU - Update inventory system" +
                    "\nQ - Exit the program" +
                    "\n\nPlease select an option: "
            );

            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();

            switch (input.toUpperCase()) { //Switch statement for each different user input
                case "C":
                    System.out.println("The following item(s) have been moved back to their original locations:\n ");
                    System.out.println(table);
                    items.cleanStore();

                    break;
                case "I":
                    System.out.println("Enter the name: ");
                    name = scan.nextLine();

                    System.out.println("Enter the RFID: ");
                    rfidTagNumber = scan.nextLine();

                    System.out.println("Enter the original location: ");
                    originalLocation = scan.nextLine();

                    System.out.println("Enter the price: ");
                    price = scan.nextDouble();

                    items.insertInfo(name,rfidTagNumber,price,originalLocation);
                    break;
                case "L":
                    System.out.println("Enter the location: ");
                    originalLocation = scan.nextLine();
                    System.out.println(table);
                    items.printByLocation(originalLocation);
                    break;
                case "M":
                    System.out.println("Enter the RFID: ");
                    rfidTagNumber = scan.nextLine();

                    System.out.println("Enter the original location: ");
                    originalLocation = scan.nextLine();

                    System.out.println("Enter the new location: ");
                    currentLocation = scan.nextLine();

                    items.moveItem(rfidTagNumber, originalLocation, currentLocation);

                    break;
                case "O":
                    System.out.println("Enter the cart number: ");
                    String cartNumber = scan.nextLine();
                    System.out.println(table);
                    double total = items.checkOut(cartNumber);
                    System.out.println("\nThe total cost for all merchandise in cart " + cartNumber.substring(1) + " was " + total);
                    break;
                case "P":
                    System.out.println(table);
                    items.printAll();
                    break;
                case "R": //Optional Extra Credit
                    System.out.println("Enter the RFID: ");
                    rfidTagNumber = scan.nextLine();
                    System.out.println(table);
                    items.printRFID(rfidTagNumber);
                    break;
                case "U": //remove all purchased
                    System.out.println("The following item(s) have removed from the system:\n");
                    System.out.println(table);
                    items.removeAllPurchased();
                    break;
                case "Q":
                    System.out.println("Program terminating successfully...");
                    System.exit(0);
                    break;
            }
        }
    }
}
