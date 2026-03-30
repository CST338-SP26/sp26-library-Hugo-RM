import Utilities.Code;

import java.util.HashMap;
import java.util.Objects;

public class Shelf {
    private static final int SHELF_NUMBER_ = 0;
    private static final int SUBJECT_ = 1;

    private HashMap<Book, Integer> books = new HashMap<>();
    private int shelfNumber;
    private String subject;
    private int numBooksOnShelf;

    public Shelf(int shelfNumber, String subject) {
        this.shelfNumber = shelfNumber;
        this.numBooksOnShelf = 0;
        this.subject = subject;
    }

    public Shelf() {
        this.numBooksOnShelf = 0;
        this.shelfNumber = 0;
        this.subject = "";
    }

    public Code addBook(Book book) {
        if (books.containsKey(book)) {
            books.merge(book, 1, Integer::sum);
            numBooksOnShelf++;
            return Code.SUCCESS;
        }

        if (book.getSubject().equals(getSubject())) {
            books.put(book, 1);
            numBooksOnShelf++;
            System.out.printf("%s added to shelf %s\n", book, this.toString());
            return Code.SUCCESS;
        }

        return Code.SHELF_SUBJECT_MISMATCH_ERROR;
    }

    public int getBookCount(Book book) {
        return books.getOrDefault(book, -1);
    }

    public HashMap<Book, Integer> getBooks() {
        return books;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public String getSubject() {
        return subject;
    }

    public int getNumBooksOnShelf() {
        return numBooksOnShelf;
    }

    public String listBooks() {
        StringBuilder s = new StringBuilder(String.format("%d books on shelf: %s\n", getNumBooksOnShelf(), this.toString()));

        books.forEach((book, numBooks) -> s.append(String.format("%s %d\n", book, numBooks)));

        return s.toString();
    }

    public Code removeBook(Book book) {
        if (!books.containsKey(book)) {
            System.out.printf("%s is not on shelf %s", book.getTitle(), getSubject());
            return Code.BOOK_NOT_IN_INVENTORY_ERROR;
        }

        if (books.get(book) == 0) {
            System.out.printf("No copies of %s remain on shelf %s", book.getTitle(), getSubject());
            return Code.BOOK_NOT_IN_INVENTORY_ERROR;
        }

        numBooksOnShelf--;
        books.merge(book, -1, Integer::sum);
        System.out.printf("%s successfully removed from shelf %s", book.getTitle(), getSubject());

        return Code.SUCCESS;
    }

    public void setBooks(HashMap<Book, Integer> books) {
        this.books = books;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setNumBooksOnShelf(int numBooksOnShelf) {
        this.numBooksOnShelf = numBooksOnShelf;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Shelf shelf = (Shelf) o;
        return getShelfNumber() == shelf.getShelfNumber() && Objects.equals(getSubject(), shelf.getSubject());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShelfNumber(), getSubject());
    }

    @Override
    public String toString() {
        return String.format("%d : %s", shelfNumber, subject);
    }
}
