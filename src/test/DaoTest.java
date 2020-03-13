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
		Loan loan = new Loan(7, m, l, LocalDate.of(2019, 12, 1), LocalDate.now());
		// init dao 
		ILoanDao loanDao = LoanDao.getInstance();
		// getlist
		loanDao.getList();
		loanDao.getListCurrent();
		loanDao.getListCurrentByBook(2);
		loanDao.getListCurrentByMember(5);
		// creation of loan
		loanDao.create(4, 5, LocalDate.now());
		// loan get for the first execution, ID = 7
		// I verify if the method function well
		loanDao.getById(7);
		// update
		loanDao.update(loan);
		// Verification methode recherche by id
		loanDao.getById(1);
		// verify method count
		loanDao.count();

		// *****************************************************************************************************

		// init object book
		Book livre = new Book(11, "Leonardi", "Alessandro", "elegidoisbn");
		// init dao 
		IBookDao bookDao = BookDao.getInstance();
		//verify method getById
		bookDao.getById(1);
		//verify create 
		Book livre_2= new Book( "Leonardi", "Alessandro", "elegidoisbn");
		bookDao.create(livre_2);
		//verify update 
		bookDao.update(livre);
		//verify the recherche 
		bookDao.getById(11);
		//verify delate 
		bookDao.delete(11);
		//verify if the following execution is empty
		bookDao.getById(11);
		//verification of count
		bookDao.count();
		
	//***********************************************************************************************************
	
	//init dao
		IMemberDao membreDao =  MemberDao.getInstance();
	//init member object
		Member membre = new Member (50,"LeonardiBelen","AlessandroA","ParisFrance","meemail@email.com","0145", SubscriptionType.PREMIUM);
	//verify getById
		membreDao.getById(1);
	//verify create
		Member membre_2 = new Member ("LeonardiBelen","AlessandroA","ParisFrance","meemail@email.com","0145", SubscriptionType.PREMIUM);
		membreDao.create(membre_2);
		membreDao.getById(13);
	//membreDao.update(membre);
	//VERIFICATION COUNT	
		membreDao.count();
	//VERIFICATION GETLIST
		membreDao.getList();
	
		}
}
