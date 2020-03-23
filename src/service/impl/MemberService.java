package service.impl;

import java.util.ArrayList;
import java.util.List;

import service.ILoanService;
import service.IMemberService;
import dao.ILoanDao;
import dao.IMemberDao;
import dao.impl.LoanDao;
import dao.impl.MemberDao;
import exception.DaoException;
import exception.ServiceException;
import model.Loan;
import model.Member;


public class MemberService implements IMemberService {
	//Singleton
	/**
	 * 
	 */
	private static MemberService instance = new MemberService();
	/**
	 * 
	 */
	private MemberService() { }	
	/**
	 * 
	 * @return
	 */
	public static IMemberService getInstance() {		
		return instance;
	}
	
	@Override
	public List<Member> getList() throws ServiceException {
		IMemberDao membreDao = MemberDao.getInstance();
		List<Member> membres = new ArrayList<Member>();		
		try {
			membres = membreDao.getList();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return membres;
	}

	@Override
	public List<Member> getListMembreEmpruntPossible() throws ServiceException {		//list pour voir qui eut emprunter un livre
		IMemberDao membreDao = MemberDao.getInstance();
		List<Member> membres = new ArrayList<Member>();	
		List<Member> membresEmpruntDispo = new ArrayList<Member>();
		ILoanService emp = LoanService.getInstance();
		try {																			//cycle sur la list
			membres = membreDao.getList(); 
			for (int i = 0; i < membres.size(); i++) {
				if (emp.isEmpruntPossible(membres.get(i))) {
					membresEmpruntDispo.add(membres.get(i));
				}
			}
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return membresEmpruntDispo;														//return la list correct
	}

	@Override
	public Member getById(int id) throws ServiceException {
		IMemberDao membreDao = MemberDao.getInstance();
		Member membre = new Member();
		try {
			membre = membreDao.getById(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return membre;
	}

	@Override
	public int create(Member membre) throws ServiceException {
		IMemberDao membreDao = MemberDao.getInstance();
		int id = -1;

		if(membre.getFirstName() == null || membre.getFirstName() == "" || membre.getLastName() == null || membre.getLastName() == "") {
			throw new ServiceException("Can't add a new member, some data hasn't been received (First name or Last name).");
		}

		try {
			membre.setLastName(membre.getLastName().toUpperCase());
			id = membreDao.create(membre);
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		} 
		return id;
	}

	@Override
	public void update(Member membre) throws ServiceException {
		IMemberDao membreDao = MemberDao.getInstance();

		if (membre.getFirstName() == null || membre.getFirstName() == "" || membre.getLastName() == null || membre.getLastName() == "") {
			throw new ServiceException("Can't update a member, some data hasn't been received (First name or Last name).");
		}

		try {
            membre.setLastName(membre.getLastName().toUpperCase());
			membreDao.update(membre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}

	@Override
	public void delete(int id) throws ServiceException {
		IMemberDao membreDao = MemberDao.getInstance();

		try {
			membreDao.delete(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}

	@Override
	public int count() throws ServiceException {
		IMemberDao membreDao = MemberDao.getInstance();
		int i = -1;
		try {
			i = membreDao.count();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return i;
	}
}