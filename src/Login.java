import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Login {

    // Read password
    private static String readPassword() {
        Console console;
        if ((console = System.console()) != null) {
            char[] password = console.readPassword();
            for (int i = 0; i < password.length; i++) {
                System.out.print("*");
            }
            System.out.println();
            return new String(password);
        }
        return null;
    }

    // Forgot password
    public static boolean forgotPassword(
            String findUser,
            ArrayList<User> registeredUsers
    ) throws IOException {
        // Searches for the username
        int found = 0;
        ListIterator<User> iterate = registeredUsers.listIterator();
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        while (iterate.hasNext()) {
            User u = iterate.next();
            if (u.uname.equals(findUser)) {
                System.out.println("Enter new password : ");
                Scanner scan = new Scanner(System.in);
                String pwd = br.readLine();
                u.setPassword(pwd);
                return true;
            }
        }
        return false;
    }
    public static User userLogin(
            ArrayList<User> registeredUsers
    ) {
        Scanner scan = new Scanner(System.in);
        System.out.println("------------------------------------- Log In --------------------------------------");
        System.out.println("Enter Username : ");
        String curruname = scan.nextLine();
        System.out.println("Enter Password : ");

        String currupwd = readPassword();

        ListIterator<User> iterate = registeredUsers.listIterator();
        while (iterate.hasNext()) {
            User u = iterate.next();
            if (u.uname.equals(curruname) && u.password.equals(currupwd)) {
                System.out.println("Successfully Logged in.........");
                System.out.println();
                return u;
            }
        }
        return null;
    }

    public static void adminLogin(
            ArrayList<User> registeredUsers,
            ArrayList<Books> totalBooks
    ) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Username : ");
        String curruname = scan.nextLine();
        System.out.println("Password : ");
        String currupwd = readPassword();

        // Admin LogIn
        if (curruname.equals("admin") && currupwd.equals("admin")) {
            Admin admin = new Admin(curruname, currupwd);
            System.out.println("Admin Login successful!!");

            while (admin != null) {
                System.out.println("------------------------------------ Admin Panel ----------------------------------");
                System.out.println("1--->Registered Users");
                System.out.println("2--->Available Books ");
                System.out.println("3--->Add New Book");
                System.out.println("4--->LogOut");
                System.out.println("-----------------------------------------------------------------------------------");

                System.out.println("Enter your choice : ");
                int ch = scan.nextInt();

                switch (ch) {
                    case 1:
                        admin.DisplayRegUsers(registeredUsers);
                        break;
                    case 2:
                        admin.BooksInStore(totalBooks);
                        break;
                    case 3:
                        Books.insertBooks(totalBooks);
                        break;
                    case 4:
                        //Unsetting/destroying the sessions
                        admin = null;
                        //new ProcessBuilder("cmd", "/c", "cls");
                        System.out.println("Logged out successfully!!");
                        break;
                    default:
                        System.out.println("Invalid choice!!");
                }
            }
        } else {
            new ProcessBuilder("cmd", "/c", "cls");
            System.out.println("Admin Login Failed");
        }
    }
}
