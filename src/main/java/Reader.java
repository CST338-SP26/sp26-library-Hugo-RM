import Utilities.Code;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reader {
    public static final int CARD_NUMBER_ = 0;
    public static final int NAME_ = 1;
    public static final int PHONE_ = 2;
    public static final int BOOK_COUNT_ = 3;
    public static final int BOOK_START_ = 4;

    private List<Book> books;
    private int cardNumber;
    private String phone;
    private String name;

    public Reader(int cardNumber, String name, String phone) {
        this.books = new ArrayList<>();
        this.cardNumber = cardNumber;
        this.phone = phone;
        this.name = name;
    }

    public Code addBook(Book book) {
        if (hasBook(book)) {
            return Code.BOOK_ALREADY_CHECKED_OUT_ERROR;
        }

        books.add(book);

        return Code.SUCCESS;
    }

    public Code removeBook(Book book) {
        if (!hasBook(book)) {
            return Code.READER_DOESNT_HAVE_BOOK_ERROR;
        }

        try {
            books.remove(book);
        } catch (Exception e) {
            return Code.READER_COULD_NOT_REMOVE_BOOK_ERROR;
        }

        return Code.SUCCESS;
    }

    public boolean hasBook(Book book) {
        return books.contains(book);
    }

    public int getBookCount() {
        return books.size();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return getCardNumber() == reader.getCardNumber() && Objects.equals(getPhone(), reader.getPhone()) && Objects.equals(getName(), reader.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardNumber(), getPhone(), getName());
    }

    @Override
    public String toString() {
        return String.format("%s (#%d) has checked out {%s}", getName(), getCardNumber(), getBooks().stream().map(Book::getTitle).collect(java.util.stream.Collectors.joining(", ")));
    }
}
