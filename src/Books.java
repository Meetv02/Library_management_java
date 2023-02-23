import java.util.ArrayList;
import java.util.ListIterator;

public class Books {

    String bname;
    int bpoints;
    int copies;
    int bid;


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
        this.bid = bid;
        this.bname = bname;
        this.bpoints = bpoints;
        this.copies = copies;
    }

    // Function Insert data of books
    public static void insertBooks(ArrayList<Books> totalBooks) {
        totalBooks.add(new Books(1, "War and Peace", 200, 3));
        totalBooks.add(new Books(2, "  Moby Quick ", 150, 5));
        totalBooks.add(new Books(3, "Les Miserable", 100, 2));
        totalBooks.add(new Books(4, " The Odyssey ", 250, 4));
        totalBooks.add(new Books(5, "   Dracula   ", 175, 1));
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
