package Service.IMPL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Service.LoanService;
import dao.ILoanDao;
import dao.impl.LoanDao;
import exception.DaoException;
import exception.ServiceException;
import model.Loan;
import model.Member;

public class LoanServiceImpl implements LoanService{

	//Singleton
	private static LoanServiceImpl instance = new LoanServiceImpl();
	private LoanServiceImpl() { }	
	public static LoanService getInstance() {		
		return instance;
	}
	
	@Override
	public List<Loan> getList() throws ServiceException {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		ILoanDao empruntDao = LoanDao.getInstance();
		List<Loan> emprunts = new ArrayList<Loan>();		
		try {
			emprunts = empruntDao.getListCurrentByMember(idLivre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunts;
	}

	@Override
	public Loan getById(int id) throws ServiceException {
		// TODO Auto-generated method stub
		ILoanDao filmDao = LoanDao.getInstance();
		Loan emprunt = new Loan();
		try {
			emprunt = filmDao.getById(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunt;
	}

	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException {
		// TODO Auto-generated method stub
		ILoanDao filmDao = LoanDao.getInstance();
		try {
			filmDao.create(idMembre, idLivre, dateEmprunt);
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}

	@Override
	public void returnBook(int id) throws ServiceException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ILoanDao empruntDao = LoanDao.getInstance();
		try {
            empruntDao.update(empruntDao.getById(id));		
            //update pour fixer la date de retour
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}

	}

	@Override
	public int count() throws ServiceException {
		// TODO Auto-generated method stub
		ILoanDao empruntDao = LoanDao.getInstance();
		int i=-1;
		try {
			i = empruntDao.count();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());
			}
		return i;
	}

	@Override
	public boolean isLivreDispo(int idLivre) throws ServiceException {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		ILoanDao empruntDao = LoanDao.getInstance();
		List<Loan> cur = new ArrayList<Loan>();
		try {
			 cur = empruntDao.getListCurrentByMember(membre.getId());
             return cur.size() < membre.getSubscription().getIndex();		
             //je vais voir si la personne peut obtenir un livre
				
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return false;
	}



}
