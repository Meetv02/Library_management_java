import java.util.ArrayList;

//manage the insert book and find the return book fine for the return book 
public interface manageBook {
    void insertBook(ArrayList<Books> totalBooks);
    int countFine(Books buybook);
}
