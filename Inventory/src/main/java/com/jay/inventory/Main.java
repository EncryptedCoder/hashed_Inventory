/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jay.inventory;

import java.util.*;

/**
 * @date 9/27/2019
 * @author JRoat
 */
public class Main {

    static String userName;
    static String password;

    static Scanner in = new Scanner(System.in);
    static Map<String, String> userAccounts = new HashMap<>();

    //where the magic begins
    public static void main(String[] args) {

        InventoriedItems ITEMS = new InventoriedItems();
        int index = -1;
        //USING HASHMAP TO STORE USERNAME AND PASSWORD
        boolean isMatch;
        //creating a few users
        userAccounts.put("jay", "jay");
        userAccounts.put("test", "test");
        userAccounts.put("love", "love");
        userAccounts.put("admin", "admin");
        boolean running;
        while (true) {
            System.out.println("\nI want to log in: 'true' or 'false'\n");
            try {
                running = in.nextBoolean();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                running = false;
            }
            in.nextLine();
            if (running) {
                System.out.println("\n\n\t\tWelcome to your Inventory Keeper!\n"
                        + "\tHere we will create a system where you are able to add and subtract, add and delete items\n"
                        + "from a list to help you keep track of things in your stock!\n\n");
                isMatch = false;
                while (!isMatch) {
                    System.out.println("Login to view your inventory! Username then password pressing Enter/Return after\n"
                            + "each! If you do not have a username and password please create an account by typing the word 'new'\n");
                    userName = in.nextLine();
                    //CHECKING FOR IF USER IS NEW
                    if ("new".equals(userName.toLowerCase())) {
                        //CREATING USER AND PASSWORD
                        createUser();
                        //USER HAS BEEN CREATED
                    } else if (userName.equals("stop")) {
                        System.out.println("Good Bye!\n");
                        in.nextLine();
                        System.exit(0);
                    } else if (userAccounts.containsKey(userName)) {
                        //MAKING SURE USER TYPED IN A USERNAME THAT IS ALREADY IN HASHMAP
                        password = in.nextLine();
                        //CHECKING TO MAKE SURE THE PASSWORD(VALUE) MATCHED WITH THE USERNAME(KEY)
                        if (password.equals(userAccounts.get(userName))) {
                            isMatch = true;
                            //HERE WE NEED TO GO TO THE INVENTORY MENU
                            System.out.println("\nLogged in as: " + userName + "!\n");
                            in.nextLine();
                            boolean loggedIN = true;
                            ////////////////////////////////////////////////////////////////////////////////////////////
                            String iN = "Glass";
                            Double iP = 123.21;
                            Integer iQ = 32;
                            Integer iK = 1;

                            ITEMS.createItemInventory(iN, iP, iQ, iK);

                            while (loggedIN) {
                                iN = null;
                                iP = null;
                                iQ = null;
                                iK = null;
                                ////////////////////////////////////////////////////////////////////////////////////////////
                                System.out.println("\n\n\tWhich option?\n"
                                        + "1: Search Item Name\n"
                                        + "2: Search Item Key\n"
                                        + "3: Create New Item\n"
                                        + "4: List All Items\n");

                                int choice = in.nextInt();
                                in.nextLine();
                                switch (choice) {
                                    case 1:
                                        System.out.println("Please enter in the 'Item Name' you wish to look for...\n");
                                        iN = in.nextLine();
                                        break;
                                    case 2:
                                        System.out.println("Please enter in the 'Key' you wish to look for...\n");
                                        iK = in.nextInt();
                                        break;
                                    case 3:
                                        System.out.println("Item Name  : ");
                                        iN = in.nextLine();
                                        System.out.println("Price Item : ");
                                        iP = in.nextDouble();
                                        System.out.println("Quantity   : ");
                                        iQ = in.nextInt();
                                        System.out.println("Key of Item: ");
                                        iK = in.nextInt();
                                        ITEMS.createItemInventory(iN, iP, iQ, iK);
                                        in.nextLine();
                                        break;
                                    case 4:
                                        ITEMS.listInventory();
                                        break;
                                    default:
                                        System.out.println("Sorry, '" + choice + "' is not a valid option.");
                                        choice = 0;
                                        break;
                                }

                                if (choice < 4 && choice != 0) {
                                    index = ITEMS.retrieveInfo(iN, iK);
                                    if (index != -1) {
                                        ITEMS.updateItem(index);
                                    }
                                }
                            }
                        } else {
                            System.out.println("I'm sorry! Your password didn't match our records, you may try again!");
                            isMatch = false;
                        }
                    } else {
                        System.out.println("UserName not found, try again.");
                        isMatch = false;
                    }
                }
                System.out.println("\nThank you for using our InventoryKeeper,\n"
                        + "you have been logged out!\n");
            }
        }
    }

    //creating a new user
    static void createUser() {
        boolean isGood;
        do {
            do {
                System.out.println("Please enter a username and remember it!");
                userName = in.nextLine();
                if (userAccounts.containsKey(userName)) {
                    System.out.println("\nI'm sorry, but this username is taken, please choose another...\n\n");
                }
            } while (userAccounts.containsKey(userName));
            System.out.println("Please enter a password and remember it!");
            password = in.nextLine();
            System.out.println("Username: " + userName + "\n"
                    + "Password: " + password + "\n"
                    + "This is good for me: true or false\n");
            //CHECKING TO MAKE SURE USER IS OKAY WITH USERNAME AND PASSWORD
            isGood = in.nextBoolean();
            in.nextLine();
            if (isGood) {
                userAccounts.put(userName, password);
                isGood = true;
            } else {
                isGood = false;
            }
        } while (!isGood);
    }

    static void choices() {

    }
}
