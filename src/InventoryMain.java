import java.util.*;

public class InventoryMain {

    public static void main(String[] args) {
        //USING HASHMAP TO STORE USERNAME AND PASSWORD
        Map<String, String> userAccounts = new HashMap();
        userAccounts.put("TestUser", "TestPa$$Word");
        char newUserChoice;
        String matchPassword;
        String userName = "";
        String password;
        String firstName;
        String lastName;
        int security = 0;
        boolean isMatch = false;
        Scanner in = new Scanner(System.in);
        boolean running = true;

        while (true) {
            System.out.println("\nI want to log in: 'true' or 'false'\n");
            try {
                running = in.nextBoolean();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                running = false;
            }
            in.nextLine();
            if (running) {
                System.out.println("\n\n\t\tWelcome to your Inventory Keeper!\n" +
                        "\tHere we will create a system where you are able to add and subtract, add and delete items from a list\n" +
                        "to help you keep track of things in your stock!\n\n");
                isMatch = false;
                while (!isMatch) {
                    System.out.println("Login to view your inventory! Username then password pressing Enter/Return after each!\n" +
                            "If you do not have a username and password please create an account by typing the word 'new'\n");
                    userName = in.nextLine();
                    //CHECKING FOR IF USER IS NEW
                    if ("new".equals(userName.toLowerCase())) {
                        //CREATING USER AND PASSWORD
                        do {
                            System.out.println("Please enter a username and remember it!");
                            userName = in.nextLine();
                            if (userAccounts.containsKey(userName)) {
                                System.out.println("\nI'm sorry, but this username is taken, please choose another...\n\n");
                            }
                        } while (userAccounts.containsKey(userName));
                        System.out.println("Please enter a password and remember it!");
                        password = in.nextLine();
                        System.out.println("Username: " + userName + "\n" +
                                "Password: " + password + "\n" +
                                "If this is okay with you, press 'y'; if not press 'n'\n");
                        newUserChoice = in.next().charAt(0);
                        in.nextLine();
                        //CHECKING TO MAKE SURE USER IS OKAY WITH USERNAME AND PASSWORD
                        if (newUserChoice == 'y') {
                            isMatch = true;
                            userAccounts.put(userName, password);
                        } else {
                            isMatch = false;
                        }
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
                            //method Inventory Menu(userName);
                        } else {
                            System.out.println("I'm sorry! Your password didn't match our records, you may try again!");
                            isMatch = false;
                        }
                    } else {
                        System.out.println("UserName not found, try again.");
                        isMatch = false;
                    }
                }
                System.out.println("\nThank you for using our InventoryKeeper,\n" +
                        "you have been logged out!\n");
            }
        }
    }
}

