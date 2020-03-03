package test;

import model.*;
import model.Member.SubscriptionType;

public class ModelTest {
    public static void main(String[] argc) {
        Member testMember = new Member("Sielskyi", "Yevhenii", "N214", "youdjin.sel15@gmail.com", "+33768476880", SubscriptionType.VIP);
        Book testBook = new Book("The Count of Monte Cristo", "Alexandre Dumas", "12345");
        Loan testLoan = new Loan(testMember, testBook);

        System.out.println(testLoan.toString());
    }
}