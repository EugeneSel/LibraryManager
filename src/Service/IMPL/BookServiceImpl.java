package Service.IMPL;

import java.util.ArrayList;
import java.util.List;

import Service.BookService;
import Service.LoanService;
import dao.IBookDao;
import dao.impl.BookDao;
import exception.DaoException;
import exception.ServiceException;
import model.Book;

public class BookServiceImpl implements BookService{

	//Singleton
	private static BookServiceImpl instance = new BookServiceImpl();
	private BookServiceImpl() { }	
	public static BookService getInstance() {		
		return instance;
	}
	
	@Override
	public List<Book> getList() throws ServiceException {
		// TODO Auto-generated method stub
		IBookDao livreDao = BookDao.getInstance();
		List<Book> livres = new ArrayList<>();		
		try {
			livres = livreDao.getList();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return livres;
	}

	@Override
	public List<Book> getListDispo() throws ServiceException {
		// TODO Auto-generated method stub
		IBookDao livreDao = BookDao.getInstance();
        List<Book>    livres = new ArrayList<>();	
		List<Book> livresDispo = new ArrayList<>();
		LoanService emp = LoanServiceImpl.getInstance();
		try {
			livres = livreDao.getList(); 
			for(int i = 0; i< livres.size();i++) {
				if(emp.isLivreDispo(livres.get(i).getId())) {
					livresDispo.add(livres.get(i));
				}
			}	
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return livresDispo;

	}

	@Override
	public Book getById(int id) throws ServiceException {
		// TODO Auto-generated method stub
		IBookDao livreDao = BookDao.getInstance();
		Book livre = new Book();
		try {
			livre = livreDao.getById(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return livre;
	}

	@Override
	public int create(Book livre) throws ServiceException {
		// TODO Auto-generated method stub
		IBookDao livreDao = BookDao.getInstance();
		int i =-1;
		try {
			i = livreDao.create(livre);
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		} 
		return i;
	}

	@Override
	public void update(Book livre) throws ServiceException {
		// TODO Auto-generated method stub
		IBookDao livreDao = BookDao.getInstance();
		try {
			livreDao.update(livre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());
			}
	}

	@Override
	public void delete(int id) throws ServiceException {
		// TODO Auto-generated method stub
		IBookDao livreDao = BookDao.getInstance();
		try {
			livreDao.delete(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());
			}
	}

	@Override
	public int count() throws ServiceException {
		// TODO Auto-generated method stub
		IBookDao livreDao = BookDao.getInstance();
		int i=-1;
		try {
			i = livreDao.count();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());
			}
		return i;
	}

}
