import java.io.Console;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;
import java.io.*;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  
public class Main {
    public static void main(String[] args) {
        Console console;
        Scanner scan = new Scanner(System.in);
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        System.out.println();

        // Store Books data
        ArrayList<Books> totalBooks = new ArrayList<Books>();
        // Store Users data
        ArrayList<User> registeredUsers = new ArrayList<User>();


        // initial few books insert
         Books.initBooks(totalBooks);
          //Date dNow = new Date( );
     //   System.out.println(dNow);    
        System.out.println("----------------------------- Welcome to the book store ---------------------------");

        // Main execution flow goes here untill user press 3(i.e. exiting)
        while (true) {
            try {
                System.out.println("------------------------------------- Main Menu -----------------------------------");
                System.out.println("1-->User ogin");
                System.out.println("2-->Admin Login");
                System.out.println("3-->Register");
                System.out.println("4-->Exit");
                System.out.println("-----------------------------------------------------------------------------------");


                System.out.println("Enter your choice : ");
                int choice;

                choice = scan.nextInt();

                // If any user is logged in(i.e currUser!=null), then provides login functionality
                User currUser = null;
                switch (choice) {
                    // Login
                    case 1:
                        //Log in and get the current user's object
                        currUser = Login.userLogin(registeredUsers);

                        // User not exists
                        if (currUser == null) {
                            System.out.println("-----------------------------------------------------------------------------------");
                            System.out.println("1-->Forgot Password");
                            System.out.println("2-->Back to Main Menu");
                            System.out.println("-----------------------------------------------------------------------------------");
                            int ch = scan.nextInt();

                            // Retry , i.e start from the beginning
                            if (ch == 2) continue;

                            // Forgot password
                            System.out.println("Enter the username : ");
                            String findUser = br.readLine();
                            boolean status = Login.forgotPassword(findUser, registeredUsers);

                            if (status == true) System.out.println(
                                    "Password updated successfully"
                            );
                            else System.out.println(
                                    "Password not updated or Username not found"
                            );
                        }
                        User.userHome(currUser,totalBooks);
                        break;
                    // Admin Loign
                    case 2:
                        Login.adminLogin(registeredUsers,totalBooks);
                        break;
                    // Register
                    case 3:
                        Register.userRegister(registeredUsers);
                        break;
                    case 4:
                        System.exit(0);
                    default:
                        System.out.println();
                        System.out.println("Invalid Choice!!");
                        break;
                }

            } catch (Exception e) {
                System.out.println("EXCEPTION CAUGHT---->" + e);
                System.out.println("PLEASE ENTER THE EXPECTED INPUT");
                scan.next();
            }
        }
    }
}