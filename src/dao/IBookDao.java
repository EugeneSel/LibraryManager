package dao;

import java.util.List;

import exception.DaoException;
import model.Book;

public interface IBookDao {
	public List<Book> getList() throws DaoException;
	public Book getById(int id) throws DaoException;
	public int create(Book book) throws DaoException;
	public void update(Book book) throws DaoException;
	public void delete(int id) throws DaoException;
	public int count() throws DaoException;
}
