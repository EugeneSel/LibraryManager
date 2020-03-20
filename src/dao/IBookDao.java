package dao;

import java.util.List;

import exception.DaoException;
import model.Book;

public interface IBookDao {
	/**
	 * Get the list of all books
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List<Book> getList() throws DaoException;
	
	/**
	 * Get the book by its id
	 *  
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Book getById(int id) throws DaoException;

	/**
	 * Create a new book
	 *  
	 * @param book
	 * @return
	 * @throws DaoException
	 */
	public int create(Book book) throws DaoException;

	/**
	 * Update the information of an existing book
	 * 
	 * @param book
	 * @throws DaoException
	 */
	public void update(Book book) throws DaoException;

	/**
	 * Delete a book using its id
	 *  
	 * @param id
	 * @throws DaoException
	 */
	public void delete(int id) throws DaoException;

	/**
	 * Get the number of books in the database 
	 * 
	 * @return
	 * @throws DaoException
	 */
	public int count() throws DaoException;
}
