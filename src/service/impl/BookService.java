package service.impl;

import java.util.ArrayList;
import java.util.List;

import service.IBookService;
import service.ILoanService;
import dao.IBookDao;
import dao.impl.BookDao;
import exception.DaoException;
import exception.ServiceException;
import model.Book;

public class BookService implements IBookService {
	//Singleton
	
	private static BookService instance = new BookService();
	private BookService() { }	

	
	public static IBookService getInstance() {		
		return instance;
	}
	
	@Override
	public List<Book> getList() throws ServiceException {
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
		IBookDao livreDao = BookDao.getInstance();
        List<Book>    livres = new ArrayList<>();	
		List<Book> livresDispo = new ArrayList<>();
		ILoanService emp = LoanService.getInstance();
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
		IBookDao livreDao = BookDao.getInstance();
		int id = -1;
		
		if (livre.getTitle() == null || livre.getAuthor() == null || livre.getIsbn() == null ||
			livre.getTitle() == "" || livre.getAuthor() == "" || livre.getIsbn() == "")
			throw new ServiceException("Can't add a new book, some data hasn't been received.");

		try {
			id = livreDao.create(livre);
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());		
		}

		return id;
	}

	@Override
	public void update(Book livre) throws ServiceException {
		IBookDao livreDao = BookDao.getInstance();

		if (livre.getTitle() == null || livre.getAuthor() == null || livre.getIsbn() == null ||
			livre.getTitle() == "" || livre.getAuthor() == "" || livre.getIsbn() == "")
			throw new ServiceException("Can't update a book, some data hasn't been received.");

		try {
			livreDao.update(livre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());
		}
	}

	@Override
	public void delete(int id) throws ServiceException {
		IBookDao livreDao = BookDao.getInstance();

		try {
			livreDao.delete(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());
		}
	}

	@Override
	public int count() throws ServiceException {
		IBookDao livreDao = BookDao.getInstance();
		int i = -1;

		try {
			i = livreDao.count();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());
		}

		return i;
	}
}
