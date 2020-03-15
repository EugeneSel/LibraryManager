package test;

import java.time.LocalDate;

import dao.IBookDao;
import dao.ILoanDao;
import dao.IMemberDao;
import dao.impl.BookDao;
import dao.impl.LoanDao;
import dao.impl.MemberDao;
import exception.DaoException;
import model.Book;
import model.Loan;
import model.Member;
import model.Member.SubscriptionType;

public class DaoTest {

	public static void main(String[] args) throws DaoException {

		// init member
		Member m = new Member(50, "LeonardiH", "AlessandroH", "ParisFrance", "meemail@email.com", "0145",
				SubscriptionType.PREMIUM);
		Book l = new Book(11, "Leonardi", "Alessandro", "elegidoisbn");
		Loan loan = new Loan(117, m, l, LocalDate.of(2019, 12, 1), LocalDate.now());
		// init dao 
		ILoanDao loanDao = LoanDao.getInstance();
		// getlist
		loanDao.getList();
		System.out.println("\n");
		loanDao.getListCurrent();
		System.out.println("\n");
		loanDao.getListCurrentByBook(2);
		System.out.println("\n");
		loanDao.getListCurrentByMember(5);
		System.out.println("\n");
		// creation of loan
		loanDao.create(4, 5, LocalDate.now());
		System.out.println("\n");
		// loan get for the first execution, ID = 7
		// I verify if the method function well
		loanDao.getById(7);
		System.out.println("\n");
		// update
		loanDao.update(loan);
		System.out.println("\n");
		// Verification methode recherche by id
		loanDao.getById(1);
		System.out.println("\n");
		// verify method count
		loanDao.count();
		System.out.println("\n");

		// *****************************************************************************************************

		// init object book
		Book livre = new Book(11, "Leonardi", "Alessandro", "elegidoisbn");
		// init dao 
		IBookDao bookDao = BookDao.getInstance();
		//verify method getById
		bookDao.getById(1);
		System.out.println("\n");
		//verify create 
		Book livre_2 = new Book("Leonardi", "Alessandro", "elegidoisbn");
		bookDao.create(livre);
		System.out.println("\n");
		//verify update 
		bookDao.update(livre);
		System.out.println("\n");
		//verify the recherche 
		bookDao.getById(11);
		System.out.println("\n");
		//verify delate 
		bookDao.delete(11);
		System.out.println("\n");
		//verify if the following execution is empty
		bookDao.getById(11);
		System.out.println("\n");
		//verification of count
		bookDao.count();
		System.out.println("\n");
		
	//***********************************************************************************************************
	
	//init dao
		IMemberDao membreDao =  MemberDao.getInstance();
	//init member object
		Member membre = new Member (50,"LeonardiBelen","AlessandroA","ParisFrance","meemail@email.com","0145", SubscriptionType.PREMIUM);
	//verify getById
		membreDao.getById(1);
		System.out.println("\n");
	//verify create
		Member membre_2 = new Member ("LeonardiBelen","AlessandroA","ParisFrance","meemail@email.com","0145", SubscriptionType.PREMIUM);
		membreDao.create(membre_2);
		System.out.println("\n");
		membreDao.getById(13);
		System.out.println("\n");
		membreDao.update(membre);
		System.out.println("\n");
	//Verify count	
		membreDao.count();
		System.out.println("\n");
	//verify getList
		membreDao.getList();
		System.out.println("\n");
	
		}
}
