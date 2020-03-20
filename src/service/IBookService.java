
package service;

import java.util.List;

import exception.ServiceException;
import model.Book;
public interface IBookService {
	/**
	 * Get the list of all books
	 * 
	 * @return
	 * @throws ServiceException
	 */

	public List<Book> getList() throws ServiceException;

	/**
	 * Get the list of all available books
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<Book> getListDispo() throws ServiceException;

	/**
	 * Get the book by its id
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 */

	public Book getById(int id) throws ServiceException;

	/**
	 * Create a new book
	 * 
	 * @param livre
	 * @return
	 * @throws ServiceException
	 */
	public int create(Book livre) throws ServiceException;

	/**
	 * Update the information of an existing book
	 * 
	 * @param livre
	 * @throws ServiceException
	 */
	public void update(Book livre) throws ServiceException;

	/**
	 * Delete a book by its id
	 * 
	 * @param id
	 * @throws ServiceException
	 */
	public void delete(int id) throws ServiceException;

	/**
	 * Get the number of books in the database
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public int count() throws ServiceException;
}