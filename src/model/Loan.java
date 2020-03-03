package model;
import java.time.LocalDate;

public class Loan {
    private static int autoincrement = 0;

    private int id;
    private Member member;
    private Book book;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public Loan() {
        this.id = autoincrement++;
        this.loanDate = LocalDate.now();
        this.returnDate = null;
    }

    public Loan(Member member, Book book) {
        this.id = autoincrement++;
        this.member = member;
        this.book = book;
        this.loanDate = LocalDate.now();
        this.returnDate = null;
    }

    public Loan(Member member, Book book, LocalDate loanDate, LocalDate returnDate) {
        this.id = autoincrement++;
        this.member = member;
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public int getId() { return this.id; };
    public Member getMember() { return this.member; };
    public Book getBook() { return this.book; };
    public LocalDate getLoanDate() { return this.loanDate; };
    public LocalDate getReturnDate() { return this.returnDate; };

    public void setId(int id) { this.id = id; };
    public void setMember(Member member) { this.member = member; };
    public void setBook(Book book) { this.book = book; };
    public void getLoanDate(LocalDate loanDate) { this.loanDate = loanDate; };
    public void getReturnDate(LocalDate returnDate) { this.returnDate = returnDate; };

    public String toString() {
        if (this.returnDate != null)
            return "\nId: " + this.id +
                "\n\nMember: " + this.member.toString() +
                "\n\nBook: " + this.book.toString() +
                "\n\nLoan date: " + this.loanDate.toString() +
                "\nReturn date: " + this.returnDate.toString();
        else
            return "\nId: " + this.id +
                "\n\nMember: " + this.member.toString() +
                "\n\nBook: " + this.book.toString() +
                "\n\nLoan date: " + this.loanDate.toString() +
                "\nReturn date: Not returned";
    }
}