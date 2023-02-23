import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;


public class User {

    String fullName;
    String uname;
    String password;
    int totalpoints;
    ArrayList<Books> boughtBooks = new ArrayList<Books>();

    public User(String fullName, String uname, String password) {
        this.fullName = fullName;
        this.uname = uname;
        this.password = password;
        this.totalpoints = 500;
    }

    public String getUsername() {
        return this.uname;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getPassword() {
        return this.password;
    }

    public int getTotalpoints() {
        return this.totalpoints;
    }

    public void setPassword(String pwd) {
        this.password = pwd;
    }

    public int ShowUserProfile() {
        Scanner scan=new Scanner(System.in);
        System.out.println("");
        System.out.println("---------------------------------- User Profile -------------------------------------");
        System.out.println("Full Name : " + getFullName());
        System.out.println("User Name : " + getUsername());
        System.out.println("Password : " + getPassword());
        System.out.println("Balance Points:" + getTotalpoints());
        System.out.println();
        System.out.println("Books Bought : ");
        ListIterator<Books> iterate = boughtBooks.listIterator();
        while (iterate.hasNext()) {
            System.out.println("->" + (iterate.next()).bname);
        }

        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("1-->Back");
        System.out.println("2-->Logout");
        System.out.println("-----------------------------------------------------------------------------------");

        System.out.println("Enter your choice : ");
        int profilechoice = scan.nextInt();

        return profilechoice;

    }

    public void ShowProfile() {
        System.out.println(getFullName() + "         " + getUsername() + "         " + getPassword() + "           " + getTotalpoints());
        System.out.print("Books Bought : ");
        ListIterator<Books> iterate = boughtBooks.listIterator();
        while (iterate.hasNext()) {
            System.out.print((iterate.next()).bname + "  ");
        }
        System.out.println();
        System.out.print("-----------------------------------------------------------------------------------");
    }

    public boolean PurchaseBook(Books buybook) {
        if (buybook.getCopies() <= 0) {
            System.out.println("Sorry!! Book you want purchase is out of stock!!");
            return false;
        }
        buybook.copies -= 1;
        if (buybook.bpoints <= this.totalpoints) {
            this.totalpoints -= buybook.bpoints;
            this.boughtBooks.add(buybook);
            System.out.println("Transaction successful!");
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println(buybook.bname + " book has been added to your purchased books list.");
            System.out.println("Remaining points: " + this.totalpoints);
            System.out.println("-----------------------------------------------------------------------------------");
            return true;
        } else {
            System.out.println("Insufficient balance. Please purchase more points to make a transaction.");
            this.ShowProfile();
            return false;
        }
    }

    // Perform the operations untill the user is logged in
    public static void userHome(User currUser,ArrayList<Books> totalBooks){
        while(currUser!=null){
            Scanner scan=new Scanner(System.in);
            // Display the available books in the store
            System.out.println("----------------------------------- Book Catalog ----------------------------------");
            System.out.println("Book ID       Book Name          Book Points    Available Copies   Availability");
            Books.AllBooksDisplay(totalBooks);

            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("1-->Profile");
            System.out.println("2-->Purchase");
            System.out.println("3-->Logout");
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("Enter your choice : ");
            try {
                int logichoice = scan.nextInt();
                System.out.println();
                switch (logichoice) {
                    // Show profile
                    case 1:
                        int profilechoice=currUser.ShowUserProfile();

                        // Clear the screen and back to home page
                        if (profilechoice == 1) {
                            new ProcessBuilder("cmd", "/c", "cls");
                        }
                        // Logout
                        if (profilechoice == 2) {
                            currUser = null;
                            new ProcessBuilder("cmd", "/c", "cls");
                            System.out.println("Successfully Logged Out!!");
                        }

                        break;
                    // Purchase books
                    case 2:
                        System.out.println(
                                "Enter the book id which you want to purchase"
                        );
                        int bookId = scan.nextInt();

                        // Find the book and purchase the book
                        Books foundbook = Books.findBook(bookId, totalBooks);
                        if (foundbook == null) {
                            System.out.println("please enter valid book id...");
                        } else {
                            currUser.PurchaseBook(foundbook);
                        }
                        break;
                    //Log out
                    case 3:
                        currUser = null;
                        new ProcessBuilder("cmd", "/c", "cls");
                        System.out.println("Successfully Logged Out!!");
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error " + e);
                scan.nextLine();
            }
        }
    }
}
