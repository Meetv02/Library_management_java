import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class User {

    String fullName;
    String uname;
    String password;
    int fine;
    ArrayList<Books> issuedBooks = new ArrayList<Books>();

    public User(String fullName, String uname, String password) {
        this.fullName = fullName;
        this.uname = uname;
        this.password = password;
        this.fine = 0;
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

    public int getFine() {
        return this.fine;
    }

    public void setPassword(String pwd) {
        this.password = pwd;
    }
     public void setFine(long lateFees) {
        this.fine += lateFees;    }

    public int ShowUserProfile() {
        Scanner scan=new Scanner(System.in);
        System.out.println("");
        System.out.println("---------------------------------- User Profile -------------------------------------");
        System.out.println("Full Name : " + getFullName());
        System.out.println("-----------------------------------------------------");
        System.out.println("User Name : " + getUsername());
        System.out.println("-----------------------------------------------------");
        System.out.println("Password : "  );
        System.out.println("-----------------------------------------------------");
        System.out.println("Total Fine:" + getFine());
        System.out.println("-----------------------------------------------------");
        System.out.println("Books Bought : ");
        ListIterator<Books> iterate = issuedBooks.listIterator();
        while (iterate.hasNext()) {
           System.out.println("->" + (iterate.next()).bname);
           // System.out.println((iterate.next()).issue_date + "  ");
        }

        System.out.println("___________________________________________________________________________________");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("1-->Back");
        System.out.println("2-->Logout");
        System.out.println("-----------------------------------------------------------------------------------");

        System.out.println("Enter your choice : ");
        int profilechoice = scan.nextInt();

        return profilechoice;

    }

    public void ShowProfile() {
        System.out.println(getFullName() + "         " + getUsername() + "         " + getPassword() + "           " + getFine());
        System.out.print("Books Bought : ");
        ListIterator<Books> iterate = issuedBooks.listIterator();
        while (iterate.hasNext()) {
            System.out.print((iterate.next()).bname + "  ");           
        }
        System.out.println();
        System.out.print("-----------------------------------------------------------------------------------");
    }

    public boolean issueBook(Books buybook) {
        if (buybook.getCopies() <= 0) {
            System.out.println("Sorry!! Book you want purchase is out of stock!!");
            return false;
        }
        buybook.copies -= 1; 
           // LocalDate issue_date = LocalDate.now();                  
           // buybook.issue_date=issue_date;
           buybook.issue_date = LocalDate.now();
            this.issuedBooks.add(buybook);
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("Transaction successful!");
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println(buybook.bname +" book has been added to your purchased books list.");
          //  System.out.println("Remaining points: " + this.totalpoints);
            System.out.println("-----------------------------------------------------------------------------------");
            return true;
       
    }

    public void returnBook(Books buybook,ArrayList<Books> totalBooks) {
        ListIterator<Books> iterate = totalBooks.listIterator();
        while (iterate.hasNext()) {
            Books book=iterate.next();
           if(book.bid==buybook.bid) {
                iterate.next().copies++;
                buybook.return_date=LocalDate.now();               
                 long daysBetween = ChronoUnit.DAYS.between(buybook.issue_date,LocalDate.now().plusDays(25));
                
                 if(daysBetween>15){
                    long fine_rs=(daysBetween-15)*5;                   

                    setFine(fine_rs);
                 }
                this.issuedBooks.remove(buybook);
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println("Transaction successful!");
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println(buybook.bname +" book has been returned");
                return;
           }
        }
    }

    public Books getReturnBook(){
        ListIterator<Books> iterate = issuedBooks.listIterator();
        System.out.println("----------------------------------- Issued Books ----------------------------------");
            System.out.println("Book ID       Book Name     Issue_date    Return_date");
        while(iterate.hasNext()){
            Books book=iterate.next();
            System.out.println(book.bid + "    " + book.bname + "     " + book.issue_date + " " + book.return_date);
        }

        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Enter Book ID you want to return : ");
        Scanner sc = new Scanner(System.in);
        int id=sc.nextInt();

        iterate = issuedBooks.listIterator();

        while(iterate.hasNext()){
            Books book=iterate.next();
            if(book.bid==id){
                return book;
            }
        }
        System.out.println("Book not exist");
        return null;
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
            System.out.println("2-->Issue Book");
            System.out.println("3-->Return Book");
            System.out.println("4-->Logout");
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
                            System.out.println("-----------------------------------------------------------------------------------");
                        }

                        break;
                    // Purchase books
                    case 2:
                    System.out.println("-----------------------------------------------------------------------------------");
                        System.out.println(
                                "Enter the book id which you want to purchase"
                        );
                        int bookId = scan.nextInt();

                        // Find the book and purchase the book
                        Books foundbook = Books.findBook(bookId, totalBooks);                      
                        if (foundbook == null) {
                            System.out.println("-----------------------------------------------------------------------------------");
                            System.out.println("please enter valid book id...");
                        } else {
                            currUser.issueBook(foundbook);
                        }
                        break;
                        //return book
                    case 3:
                        Books rbook=currUser.getReturnBook();
                        if(rbook!=null){
                            currUser.returnBook(rbook,totalBooks);
                        }
                        break;
                    //Log out
                    case 4:
                        currUser = null;
                        new ProcessBuilder("cmd", "/c", "cls");
                        System.out.println("Successfully Logged Out!!");
                        System.out.println("-----------------------------------------------------------------------------------");
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
