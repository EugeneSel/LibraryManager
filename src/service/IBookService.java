
package service;

import java.util.List;

import exception.ServiceException;
import model.Book;
public interface IBookService {
	public List<Book> getList() throws ServiceException;
	public List<Book> getListDispo() throws ServiceException;
	public Book getById(int id) throws ServiceException;
	public int create(Book livre) throws ServiceException;
	public void update(Book livre) throws ServiceException;
	public void delete(int id) throws ServiceException;
	public int count() throws ServiceException;
}