package model;

public class Book {
    private static int autoincrement = 0;

    private int id;
    private String title;
    private String author;
    private String isbn;

    public Book() {
        //this.id = autoincrement++;
    }

    public Book(final String title, final String author, final String isbn) {
        this.id = autoincrement++;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public Book(final  int id, final String title, final String author, final String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public int getId() {
        return this.id;
    };

    public String getTitle() {
        return this.title;
    };

    public String getAuthor() {
        return this.author;
    };

    public String getIsbn() {
        return this.isbn;
    };

    public void setId(final int id) {
        this.id = id;
    };

    public void setTitle(final String title) {
        this.title = title;
    };

    public void setAuthor(final String author) {
        this.author = author;
    };

    public void setIsbn(final String isbn) {
        this.isbn = isbn;
    };

    public String toString() {
        return "\nId: " + this.id +
            "\nTitle: " + this.title +
            "\nAuthor: " + this.author +
            "\nISBN: " + this.isbn;
    }
}