
package service;

import java.util.List;

import exception.ServiceException;
import model.Book;
public interface IBookService {
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<Book> getList() throws ServiceException;
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<Book> getListDispo() throws ServiceException;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public Book getById(int id) throws ServiceException;
	/**
	 * 
	 * @param livre
	 * @return
	 * @throws ServiceException
	 */
	public int create(Book livre) throws ServiceException;
	/**
	 * 
	 * @param livre
	 * @throws ServiceException
	 */
	public void update(Book livre) throws ServiceException;
	/**
	 * 
	 * @param id
	 * @throws ServiceException
	 */
	public void delete(int id) throws ServiceException;
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public int count() throws ServiceException;
}