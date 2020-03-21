package service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import service.ILoanService;
import dao.ILoanDao;
import dao.impl.LoanDao;
import exception.DaoException;
import exception.ServiceException;
import model.Loan;
import model.Member;

public class LoanService implements ILoanService{
	//Singleton
	/**
	 * 
	 */
	private static LoanService instance = new LoanService();
	/**
	 * 
	 */
	private LoanService() { }	
	/**
	 * 
	 * @return
	 */
	public static ILoanService getInstance() {		
		return instance;
	}
	
	@Override
	public List<Loan> getList() throws ServiceException {
		ILoanDao empruntDao = LoanDao.getInstance();
		List<Loan> emprunts = new ArrayList<Loan>();
				
		try {
			emprunts = empruntDao.getList();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunts;
	}

	@Override
	public List<Loan> getListCurrent() throws ServiceException {
		ILoanDao empruntDao = LoanDao.getInstance();
		List<Loan> emprunts = new ArrayList<Loan>();

		try {
			emprunts = empruntDao.getListCurrent();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}

		return emprunts;
	}

	@Override
	public List<Loan> getListCurrentByMembre(int idMembre) throws ServiceException {
		ILoanDao empruntDao = LoanDao.getInstance();
		List<Loan> emprunts = new ArrayList<Loan>();		
		try {
			emprunts = empruntDao.getListCurrentByMember(idMembre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunts;
	}

	@Override
	public List<Loan> getListCurrentByLivre(int idLivre) throws ServiceException {
		ILoanDao empruntDao = LoanDao.getInstance();
		List<Loan> emprunts = new ArrayList<Loan>();		
		try {
			emprunts = empruntDao.getListCurrentByBook(idLivre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunts;
	}

	@Override
	public Loan getById(int id) throws ServiceException {
		ILoanDao loanDao = LoanDao.getInstance();
		Loan emprunt = new Loan();
		try {
			emprunt = loanDao.getById(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunt;
	}

	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException {
		ILoanDao loanDao = LoanDao.getInstance();

		try {
			loanDao.create(idMembre, idLivre, dateEmprunt);
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}

	@Override
	public void returnBook(int id) throws ServiceException {
		ILoanDao empruntDao = LoanDao.getInstance();
		try {
			Loan loan = empruntDao.getById(id);
			loan.setReturnDate(LocalDate.now());

			// update pour fixer la date de retour:
			empruntDao.update(loan);
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}

	@Override
	public int count() throws ServiceException {
		ILoanDao empruntDao = LoanDao.getInstance();
		int i = -1;

		try {
			i = empruntDao.count();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());
		}

		return i;
	}

	@Override
	public boolean isLivreDispo(int idLivre) throws ServiceException {
		ILoanDao empruntDao = LoanDao.getInstance();
		List<Loan> cur = new ArrayList<Loan>();

		try {
			cur = empruntDao.getListCurrentByBook(idLivre);
			return cur.isEmpty();		
			//je fais un retour pour voir si le livre n'est pas dans la liste des livres emprunts
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}

		return false;
	}


	@Override
	public boolean isEmpruntPossible(Member membre) throws ServiceException {
		ILoanDao empruntDao = LoanDao.getInstance();
		List<Loan> cur = new ArrayList<Loan>();
		
		try {
			cur = empruntDao.getListCurrentByMember(membre.getId());
			return cur.size() < membre.getSubscription().getIndex() + 1;		
			// je vais voir si la personne peut obtenir un livre
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());
		}

		return false;
	}
}