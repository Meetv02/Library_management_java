import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;
import java.time.LocalDate;

public class Books {

    String bname;
    int bpoints;
    int copies;
    int bid;
    LocalDate issue_date;
    LocalDate return_date;
    


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
   
    public Books(int bid, String bname, int bpoints, int copies) {
        LocalDate issue_date = LocalDate.now();
        // LocalDate return_date= new Date(issue_date.getTime() + 15 * 24 * 60 * 60 * 1000); 
        System.out.println("Current Date: "+issue_date);
        //System.out.println("Return Date: "+return_date);
        this.bid = bid;
        this.bname = bname;
        this.bpoints = bpoints;
        this.copies = copies;
        //this.issue_date = issue_date;      
    }
    


    public static void initBooks(ArrayList<Books> totalBooks) {       
        totalBooks.add(new Books(1, "War and Peace", 200, 3));
        totalBooks.add(new Books(2, "  Moby Quick ", 150, 5));
        totalBooks.add(new Books(3, "Les Miserable", 100, 2));
        totalBooks.add(new Books(4, " The Odyssey ", 250, 4));
        totalBooks.add(new Books(5, "   Dracula   ", 175, 1));
    }
    // Function Insert data of books
    public static void insertBooks(ArrayList<Books> totalBooks) {
        Scanner sc = new Scanner(System.in);
        int bid=1,price,qty;
        String name;
        bid++;    
        System.out.print("Enter Book Name: ");        
        name = sc.nextLine();
       while(name.length() < 1){              
                System.out.println("| Error: Please Enter the Book Name    |");              


                System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*");
                System.out.print("| Enter Book Name: ");
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
        System.out.println(bid + "         " + bname + "            " + bpoints + "              " + copies + "          " + avail);
    }

    // Display all books present in the bookstore(i.e ArrayList)
    public static void AllBooksDisplay(ArrayList<Books> totalBooks) {
        ListIterator<Books> iterate = totalBooks.listIterator();
        while (iterate.hasNext()) {
            (iterate.next()).displayBook();
        }
    }
}
