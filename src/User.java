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
        buybook.setIssue_date(LocalDate.now().plusDays(15));
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
        ListIterator<Books> iterate = totalBooks.listIterator();
        while (iterate.hasNext()) {
            Books book=iterate.next();
            if(book.getBookId()==buybook.getBookId()) {
                Admin admin=new Admin();
                (iterate.next()).setCopies(iterate.next().getCopies()+1);
                setFine(admin.countFine(buybook));
                buybook.setStatus(true);
                System.out.println("Transaction successful!");
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println(buybook.getBookName() +" book has been returned");
                return;
            }
        }
    }
}

