package model;
import java.time.LocalDate;

public class Loan {
    //private static int autoincrement = 0;

    private int id;
    private Member member;
    private Book book;
    private LocalDate loanDate;
    private LocalDate returnDate;
    /**
     * 
     */
    public Loan() {
        // this.id = autoincrement++;
        this.loanDate = LocalDate.now();
        this.returnDate = null;
    }
    /**
     * 
     * @param member
     * @param book
     */
    public Loan(final Member member, final Book book) {
        // this.id = autoincrement++;
        this.member = member;
        this.book = book;
        this.loanDate = LocalDate.now();
        this.returnDate = null;
    }
    /**
     * 
     * @param member
     * @param book
     * @param loanDate
     * @param returnDate
     */
    public Loan(final Member member, final Book book, final LocalDate loanDate, final LocalDate returnDate) {
        // this.id = autoincrement++;
        this.member = member;
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }
    /**
     * 
     * @param id
     * @param member
     * @param book
     * @param loanDate
     * @param returnDate
     */

    public Loan( final int id, final Member member, final Book book, final LocalDate loanDate, final LocalDate returnDate) {
        this.id = id;
        this.member = member;
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }
    /**
     * 
     */

    public int getId() {
        return this.id;
    };
    /**
     * 
     * @return
     */
    public Member getMember() {
        return this.member;
    };
    /**
     * 
     * @return
     */
    public Book getBook() {
        return this.book;
    };
    /**
     * 
     * @return
     */
    public LocalDate getLoanDate() {
        return this.loanDate;
    };
    /**
     * 
     * @return
     */
    public LocalDate getReturnDate() {
        return this.returnDate;
    };
    /**
     * 
     * @param id
     */
    public void setId(final int id) {
        this.id = id;
    };
    /**
     * 
     * @param member
     */

    public void setMember(final Member member) {
        this.member = member;
    };
    /**
     * 
     */

    public void setBook(final Book book) {
        this.book = book;
    };
    /**
     * 
     * @param loanDate
     */

    public void setLoanDate(final LocalDate loanDate) {
        this.loanDate = loanDate;
    };
    /**
     * 
     * @param returnDate
     */
    public void setReturnDate(final LocalDate returnDate) {
        this.returnDate = returnDate;
    };
    /**
     * 
     */

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