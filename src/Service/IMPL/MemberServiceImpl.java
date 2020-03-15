package Service.IMPL;

import java.util.ArrayList;
import java.util.List;

import Service.LoanService;
import Service.MemberService;
import dao.IMemberDao;
import dao.impl.MemberDao;
import exception.DaoException;
import exception.ServiceException;
import model.Member;


public class MemberServiceImpl implements MemberService {

	//Singleton
	private static MemberServiceImpl instance = new MemberServiceImpl();
	private MemberServiceImpl() { }	
	public static MemberService getInstance() {		
		return instance;
	}
	
	@Override
	public List<Member> getList() throws ServiceException {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		IMemberDao membreDao = MemberDao.getInstance();
		List<Member> membres = new ArrayList<Member>();	
		List<Member> membresEmpruntDispo = new ArrayList<Member>();
		LoanService emp = LoanServiceImpl.getInstance();
		try {																			//cycle sur la list
			membres = membreDao.getList(); 
			for(int i = 0; i < membres.size(); i++) {
				if(emp.isEmpruntPossible(membres.get(i))) {
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub;
		IMemberDao livreDao = MemberDao.getInstance();
		int i = -1;
		if(membre.getFirstName() == null || membre.getFirstName() == "" || membre.getLastName() == null || membre.getLastName() == "") {
			throw new ServiceException("Nom ou Prenom null ou vide");					//return l'exception comme demand
		}
		try {
			i = livreDao.create(membre);
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		} 
		return i;
	}

	@Override
	public void update(Member membre) throws ServiceException {
		// TODO Auto-generated method stub
		IMemberDao membreDao = MemberDao.getInstance();
		if(membre.getFirstName() == null ||membre.getFirstName() == "" || membre.getLastName() == null || membre.getLastName() == "") {
			throw new ServiceException("Nom ou Prenom null ou vide");					//return l'exception comme demand
		}
		try {
            membre.setFirstName(membre.getFirstName().toUpperCase());
            membre.setLastName(membre.getFirstName().toUpperCase());
			membreDao.update(membre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}

	@Override
	public void delete(int id) throws ServiceException {
		// TODO Auto-generated method stub
		IMemberDao membreDao = MemberDao.getInstance();
		try {
			membreDao.delete(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}

	@Override
	public int count() throws ServiceException {
		// TODO Auto-generated method stub
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
