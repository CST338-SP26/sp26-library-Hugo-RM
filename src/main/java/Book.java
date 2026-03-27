import java.time.LocalDate;
import java.util.Objects;

public class Book {
    public static final int ISBN_ = 0;
    public static final int TITLE_ = 1;
    public static final int SUBJECT_ = 2;
    public static final int PAGE_COUNT_ = 3;
    public static final int AUTHOR_ = 4;
    public static final int DUE_DATE_ = 5;

    private String author;
    private LocalDate dueDate;
    private String isbn;
    private int pageCount;
    private String subject;
    private String title;

    public Book(String isbn, String author, String title, int pageCount, String subject, LocalDate dueDate) {
        this.author = author;
        this.dueDate = dueDate;
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.subject = subject;
        this.title = title;
    }

    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Book book = (Book) object;
        return getPageCount() == book.getPageCount() && java.util.Objects.equals(getAuthor(), book.getAuthor()) && java.util.Objects.equals(getISBN(), book.getISBN()) && java.util.Objects.equals(getSubject(), book.getSubject()) && java.util.Objects.equals(getTitle(), book.getTitle());
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), getAuthor(), getISBN(), getPageCount(), getSubject(), getTitle());
    }

    @java.lang.Override
    public java.lang.String toString() {
        return String.format("%s by %s ISBN: %s", getTitle(), getAuthor(), getISBN());
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getISBN() {
        return isbn;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getSubject() {
        return subject;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
