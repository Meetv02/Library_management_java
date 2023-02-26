
import java.util.ArrayList;

public class Admin {
    String aname;
    String apassword;

    Admin(String aname, String apassword) {
        this.aname = aname;
        this.apassword = apassword;
    }

    public void DisplayRegUsers(ArrayList<User> UsersList) {
        System.out.println("------------------------------------- User Profile --------------------------------");
        System.out.println("Full Name         User Name        Password         Balance Points ");
        System.out.println("-----------------------------------------------------------------------------------");
        for (User u : UsersList) {
            u.ShowProfile();
            System.out.println("");
        }
        System.out.println("-----------------------------------------------------------------------------------");
    }

    public void BooksInStore(ArrayList<Books> totalBooks) {
        System.out.println("---------------------------------- Book Catalog -----------------------------------");
        System.out.println("Book ID     Book Name       Book Points    Available Copies   Availability");
        System.out.println("-----------------------------------------------------------------------------------");
        for (Books b : totalBooks) {
            b.displayBook();
        }
        System.out.println("-----------------------------------------------------------------------------------");
    }
}