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
    // issue the book and store book information in the book object
    public boolean issueBook(Books buybook) {
        //check if the book is available or not
        if (buybook.getCopies() <= 0) {
            System.out.println("Sorry!! Book you want purchase is out of stock!!");
            return false;
        }
        buybook.setCopies(buybook.getCopies()-1);
        buybook.setIssue_date(LocalDate.now());
        buybook.setReturn_date(LocalDate.now().plusDays(15));
        buybook.setStatus(false);
        //add the book to the book list
        this.issuedBooks.add(buybook);
        System.out.println("Transaction successful!");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println(buybook.getBookName() +" book has been added to your purchased books list.");        
        System.out.println("-----------------------------------------------------------------------------------");
        return true;

    }
    //return the book by its book id    
    public void returnBook(Books buybook,ArrayList<Books> totalBooks) {        
        ListIterator<Books> iterate = totalBooks.listIterator();
        while (iterate.hasNext()) {           
            Books book=iterate.next();
            // check if the book is already exits in the list or not
            if(book.getBookId()==buybook.getBookId()) {               
                Admin admin=new Admin();
                book.setCopies(book.getCopies()+1);
                // check the book fine and update the fine list             
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

