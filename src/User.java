import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class User extends Student{
    public User(String fullName, String uname, String password) {
        super(fullName, uname, password);
    }

    public boolean issueBook(Books buybook) {
        if (buybook.getCopies() <= 0) {
            System.out.println("Sorry!! Book you want purchase is out of stock!!");
            return false;
        }
        buybook.setCopies(buybook.getCopies()-1);
        buybook.setIssue_date(LocalDate.now());
        buybook.setReturn_date(LocalDate.now().plusDays(15));
        buybook.setStatus(false);
        this.issuedBooks.add(buybook);
        System.out.println("Transaction successful!");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println(buybook.getBookName() +" book has been added to your purchased books list.");
        //  System.out.println("Remaining points: " + this.totalpoints);
        System.out.println("-----------------------------------------------------------------------------------");
        return true;

    }

    public void returnBook(Books buybook,ArrayList<Books> totalBooks) {
        System.out.println("function called");
        ListIterator<Books> iterate = totalBooks.listIterator();
        while (iterate.hasNext()) {
            System.out.println("in the loop");
            Books book=iterate.next();
            if(book.getBookId()==buybook.getBookId()) {
                System.out.println("in condition");
                Admin admin=new Admin();
                book.setCopies(book.getCopies()+1);
                System.out.println("copies updated");
                setFine(admin.countFine(buybook));
                System.out.println("fine returned");
                buybook.setStatus(true);
                System.out.println("Transaction successful!");
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println(buybook.getBookName() +" book has been returned");
                return;
            }
        }
    }
}

