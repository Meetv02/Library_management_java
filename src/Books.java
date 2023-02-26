import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;
import java.time.LocalDate;

public class Books {

    private String bname;
    private int bpoints,copies,bid;
    private boolean status;
    private LocalDate issue_date,return_date;

    public String getBookName() {
        return this.bname;
    }

    public int getBookPoints() {
        return this.bpoints;
    }

    public int getCopies() {
        return this.copies;
    }

    public int getBookId() {
        return this.bid;
    }

    public LocalDate getIssue_date(){
        return this.issue_date;
    }

    public LocalDate getReturn_date(){
        return this.return_date;
    }

    public boolean getStatus(){
        return this.status;
    }
    public void setCopies(int copies) {
        this.copies=copies;
    }

    public void setIssue_date(LocalDate issue_date){
        this.issue_date=issue_date;
    }

    public void setReturn_date(LocalDate return_date){
        this.return_date=return_date;
    }

    public void setStatus(boolean status){
        this.status=status;
    }
    public Books(int bid, String bname, int bpoints, int copies) {
        this.bid = bid;
        this.bname = bname;
        this.bpoints = bpoints;
        this.copies = copies;
    }

    public static void initBooks(ArrayList<Books> totalBooks) {       
        totalBooks.add(new Books(1, "War and Peace", 200, 3));
        totalBooks.add(new Books(2, "Moby Quick", 150, 5));
        totalBooks.add(new Books(3, "Les Miserable", 100, 2));
        totalBooks.add(new Books(4, "The Odyssey", 250, 4));
        totalBooks.add(new Books(5, "Dracula", 175, 1));
    }

    // find the required book and return the book object
    public static Books findBook(int bookId, ArrayList<Books> totalBooks) {
        ListIterator<Books> iterate = totalBooks.listIterator();
        while (iterate.hasNext()) {
            Books buybook = iterate.next();
            if (buybook.bid == bookId) {
                return buybook;
            }
        }
        return null;
    }
    public void displayBook() {
        String avail;
        if (copies == 0) avail = "Out of Stock";
        else avail = "In Stock";
        //System.out.println(bid + "         " + bname + "            " + bpoints + "              " + copies + "          " + avail);
         System.out.printf("%-10d %-30s %-10d %-10d %-20s%n",bid,bname,bpoints,copies,avail);
    }

    public void displayIssuedBook() {
        String returns;
        if (status == false) returns = "Not Returned";
        else returns = "Returned";
      //  System.out.println(bid + "         " + bname + "            " + bpoints + "              " + copies + "          " + issue_date + "         " + return_date + "         " + returns);
         System.out.printf("%-10d %-30s %-10d %-10d %-20 %-10s %-10s %-10s%n",bid,bname,bpoints,copies,issue_date,return_date,returns);

    }
   
    // Display all books present in the bookstore(i.e ArrayList)
    public static void AllBooksDisplay(ArrayList<Books> totalBooks) {
        ListIterator<Books> iterate = totalBooks.listIterator();
        while (iterate.hasNext()) {
            (iterate.next()).displayBook();
        }
    }
}
