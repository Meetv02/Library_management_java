import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Student {
    private String fullName, uname, password;
    private int fine;
    ArrayList<Books> issuedBooks = new ArrayList<Books>();
    //create a new Student constructor
    public Student(String fullName, String uname, String password) {
        this.fullName = fullName;
        this.uname = uname;
        this.password = password;
        this.fine = 0;
    }

    //get the usernames from the issuedBooks list
    public String getUsername() {
        return this.uname;
    }
    // get the full name from the issuedBooks list
    public String getFullName() {
        return this.fullName;
    }
    // get the password from the issuedBooks list
    public String getPassword() {
        return this.password;
    }
    // get the fine from the issuedBooks list
    public int getFine() {
        return this.fine;
    }
    //set the new password of the forgotten user
    public void setPassword(String pwd) {
        this.password = pwd;
    }
    // set the fine of the late return book
    public void setFine(long lateFees) {
        this.fine += lateFees;
    }
    // display the user name,full name,fine of the user and also display the issued book by the users
    public int ShowUserProfile() {
        Scanner scan = new Scanner(System.in);
        System.out.println("");
        System.out.println("---------------------------------- User Profile -------------------------------------");
        // get the fullname from the user user list
        System.out.println("Full Name : " + getFullName());
        // get the username from the user user list
        System.out.println("User Name : " + getUsername());
        // get the fine from the user user list
        System.out.println("Total Fine:" + getFine());
        System.out.println();
        System.out.println("Books Bought : ");
        ListIterator<Books> iterate = issuedBooks.listIterator();
        System.out.printf("%-10s %-30s %-10s %-10s %-20s %-20s %-10s%n","Book ID","Book Name","Price","Copies","Issue Date","Return Date","Return Status");
        while (iterate.hasNext()) {
            //display the book details of the book list
            (iterate.next()).displayIssuedBook();
        }

        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("1-->Back");
        System.out.println("2-->Logout");
        System.out.println("-----------------------------------------------------------------------------------");

        System.out.println("Enter your choice : ");
        int profilechoice = scan.nextInt();

        return profilechoice;

    }
    //show the user profile information and display the issue  book information 
    public void ShowProfile() {        
        System.out.printf("%-30s %-30s %-30d%n",getFullName(),getUsername(),getFine());        
        System.out.println("------------------------------------- Books Bought --------------------------------");      
        System.out.printf("%-10s %-30s %-20s %-20s %-10s%n","Book ID","Book Name","Issue Date","Return Date","Return Status");
        
        ListIterator<Books> iterate = issuedBooks.listIterator();
        while (iterate.hasNext()) {
            Books book=iterate.next();
            String returns;
            if (book.getStatus()== false) returns = "Not Returned";
            else returns = "Returned";
            System.out.printf("%-10d %-30s %-20s %-20s %-10s%n",book.getBookId(),book.getBookName(),book.getIssue_date(),book.getReturn_date(),returns);
           }
        System.out.println();
        System.out.print("-----------------------------------------------------------------------------------");
    }
    //display the return book using the book id 
    public Books getReturnBook() {
        ListIterator<Books> iterate = issuedBooks.listIterator();
        System.out.println("----------------------------------- Issued Books ----------------------------------");        
        System.out.printf("%-10s %-30s %-20s %-20s%n","Book ID","Book Name","Issue Date","Return Date");

        while (iterate.hasNext()) {
            Books book = iterate.next();
            if(book.getStatus()==false){                
                 System.out.printf("%-10d %-30s %-20s %-20s%n",book.getBookId(),book.getBookName(),book.getIssue_date(),book.getReturn_date());
            }
        }

        System.out.println("Enter Book ID you want to return : ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        iterate = issuedBooks.listIterator();
        while (iterate.hasNext()) {
            Books book = iterate.next();
            if (book.getBookId() == id) {
                return book;
            }
        }
        System.out.println("Book not exist");
        return null;
    }

    // Perform the operations untill the user is logged in
    public static void userHome(User currUser, ArrayList<Books> totalBooks) {
        while (currUser != null) {
            Scanner scan = new Scanner(System.in);
            // Display the available books in the store
            System.out.println("----------------------------------- Book Catalog ----------------------------------");            
             System.out.printf("%-10s %-30s %-10s %-10s %-20s%n","Book ID","Book Name","Price","Copies","Availability");
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
                        int profilechoice = currUser.ShowUserProfile();

                        // Clear the screen and back to home page
                        // if (profilechoice == 1) {
                        //     new ProcessBuilder("cmd", "/c", "cls");
                        // }
                        // Logout
                        if (profilechoice == 2) {
                            currUser = null;
                            // new ProcessBuilder("cmd", "/c", "cls");
                            System.out.println("Successfully Logged Out!!");
                        }

                        break;
                    // isuue books
                    case 2:
                        System.out.println(
                                "Enter the book id which you want to issue"
                        );
                        int bookId = scan.nextInt();

                        // Find the book and isuue the book
                        Books foundbook = Books.findBook(bookId, totalBooks);
                        if (foundbook == null) {
                            System.out.println("please enter valid book id...");
                        } else {
                            currUser.issueBook(foundbook);
                        }
                        break;
                      //return book
                    case 3:
                        Books rbook = currUser.getReturnBook();
                        if (rbook != null) {
                            currUser.returnBook(rbook, totalBooks);
                        }
                        break;
                    //Log out
                    case 4:
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
