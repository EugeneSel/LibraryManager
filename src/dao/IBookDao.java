package dao;

import java.util.List;

import exception.DaoException;
import model.Book;

public interface IBookDao {
	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List<Book> getList() throws DaoException;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Book getById(int id) throws DaoException;
	/**
	 * 
	 * @param book
	 * @return
	 * @throws DaoException
	 */
	public int create(Book book) throws DaoException;
	/**
	 * 
	 * @param book
	 * @throws DaoException
	 */
	public void update(Book book) throws DaoException;
	/**
	 * 
	 * @param id
	 * @throws DaoException
	 */
	public void delete(int id) throws DaoException;
	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public int count() throws DaoException;
}
