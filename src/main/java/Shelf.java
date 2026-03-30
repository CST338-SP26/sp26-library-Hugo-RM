import Utilities.Code;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author Hugo Ruiz-Mireles
 * @since 2026.03.29
 * Abstract: Shelf class that stores books of a certain category
 *
 */
public class Shelf {
    private static final int SHELF_NUMBER_ = 0;
    private static final int SUBJECT_ = 1;

    private HashMap<Book, Integer> books = new HashMap<>();
    private int shelfNumber;
    private String subject;
    private int numBooksOnShelf;

    /**
     * Creates a shelf with the given shelf number and subject.
     *
     * @param shelfNumber the shelf number
     * @param subject the subject of the shelf
     */
    public Shelf(int shelfNumber, String subject) {
        this.shelfNumber = shelfNumber;
        this.numBooksOnShelf = 0;
        this.subject = subject;
    }

    /**
     * Creates an empty shelf with default values.
     */
    public Shelf() {
        this.numBooksOnShelf = 0;
        this.shelfNumber = 0;
        this.subject = "";
    }

    /**
     * Adds a book to the shelf if the subject matches.
     *
     * @param book the book to add
     * @return SUCCESS if the book was added, or an error code if the subject does not match
     */
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

    /**
     * Gets the number of copies of a book on the shelf.
     *
     * @param book the book to look up
     * @return the number of copies, or -1 if the book is not on the shelf
     */
    public int getBookCount(Book book) {
        return books.getOrDefault(book, -1);
    }

    /**
     * Returns the books stored on this shelf.
     *
     * @return the map of books and their counts
     */
    public HashMap<Book, Integer> getBooks() {
        return books;
    }

    /**
     * Returns the shelf number.
     *
     * @return the shelf number
     */
    public int getShelfNumber() {
        return shelfNumber;
    }

    /**
     * Returns the shelf subject.
     *
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Returns the total number of books on the shelf.
     *
     * @return the number of books on the shelf
     */
    public int getNumBooksOnShelf() {
        return numBooksOnShelf;
    }

    /**
     * Lists the books currently on the shelf.
     *
     * @return a formatted string containing the shelf contents
     */
    public String listBooks() {
        StringBuilder s = new StringBuilder(String.format("%d books on shelf: %s\n", getNumBooksOnShelf(), this.toString()));

        books.forEach((book, numBooks) -> s.append(String.format("%s %d\n", book, numBooks)));

        return s.toString();
    }

    /**
     * Removes one copy of a book from the shelf.
     *
     * @param book the book to remove
     * @return SUCCESS if the book was removed, or an error code if it is not available
     */
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

    /**
     * Replaces the stored book map.
     *
     * @param books the new book map
     */
    public void setBooks(HashMap<Book, Integer> books) {
        this.books = books;
    }

    /**
     * Sets the shelf number.
     *
     * @param shelfNumber the new shelf number
     */
    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    /**
     * Sets the shelf subject.
     *
     * @param subject the new shelf subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Sets the total number of books on the shelf.
     *
     * @param numBooksOnShelf the new total number of books
     */
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
