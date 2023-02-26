
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Admin implements manageBook {
    String aname;
    String apassword;

    Admin(){
    }

    Admin(String aname, String apassword) {
        this.aname = aname;
        this.apassword = apassword;
    }

    public void DisplayRegUsers(ArrayList<User> UsersList) {
        System.out.println("------------------------------------- User Profile --------------------------------");      
        System.out.printf("%-30s %-30s %-30s%n","Full Name","User Name","Fine");
        System.out.println("-----------------------------------------------------------------------------------");
        for (User u : UsersList) {
            u.ShowProfile();
            System.out.println("");
        }
        System.out.println("-----------------------------------------------------------------------------------");
    }

    public void BooksInStore(ArrayList<Books> totalBooks) {
        System.out.println("---------------------------------- Book Catalog -----------------------------------");
       // System.out.println("Book ID     Book Name       Book Points    Available Copies   Availability");
         System.out.printf("%-10s %-30s %-10s %-10s %-20s%n","Book ID","Book Name","Price","Copies","Availability");
        System.out.println("-----------------------------------------------------------------------------------");
        for (Books b : totalBooks) {
            b.displayBook();
        }
        System.out.println("-----------------------------------------------------------------------------------");
    }

    // Function Insert data of books

    @Override
    public void insertBook(ArrayList<Books> totalBooks) {
        Scanner sc = new Scanner(System.in);
        int bid=1,price,qty;
        String name;
        bid++;
        System.out.print("Enter Book Name: ");
        name = sc.nextLine();
        while(name.length() < 1){
            System.out.println("| Error: Please Enter the Book Name    |");
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*");
            System.out.print("Enter Book Name: ");
            name = sc.nextLine();
        }
        System.out.print("Enter Book Price: ");
        price=sc.nextInt();
        while(price<1){
            System.out.println("| Error: Please Enter the Book Price more than 1    |");
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*");
            System.out.print("| Enter Book Price: ");
            price = sc.nextInt();
        }
        System.out.print("Enter Quantity of Book : ");
        qty=sc.nextInt();
        while(qty<1){
            System.out.println("| Error: Please Enter Quantity of Book more than 1 |");
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*");
            System.out.print("Enter Quantity of Book : ");
            qty = sc.nextInt();
        }
        totalBooks.add(new Books(bid, name, price, qty));
        System.out.println("record book inserted : ");
    }

    @Override
    public int countFine(Books buybook) {        
        int fine_rs=0;
        long daysBetween = ChronoUnit.DAYS.between(buybook.getReturn_date(), LocalDate.now());        
        if(daysBetween>0){
            fine_rs= (int) ((daysBetween)*5);
        }
        return fine_rs;
    }

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
                        admin.insertBook(totalBooks);
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