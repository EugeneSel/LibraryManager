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

    public Book(String title, String author, String isbn) {
        this.id = autoincrement++;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public int getId() { return this.id; };
    public String getTitle() { return this.title; };
    public String getAuthor() { return this.author; };
    public String getIsbn() { return this.isbn; };
    
    public void setId(int id) { this.id = id; };
    public void setTitle(String title) { this.title = title; };
    public void setAuthor(String author) { this.author = author; };
    public void setIsbn(String isbn) { this.isbn = isbn; };

    public String toString() {
        return "\nId: " + this.id +
            "\nTitle: " + this.title +
            "\nAuthor: " + this.author +
            "\nISBN: " + this.isbn;
    }
}