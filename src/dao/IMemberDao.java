package dao;

import java.util.List;

import exception.DaoException;
import model.Member;

public interface IMemberDao {
	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List<Member> getList() throws DaoException;
	/**
	 * 
	 */
	public Member getById(int id) throws DaoException;
	/**
	 * 
	 * @param member
	 * @return
	 * @throws DaoException
	 */
	public int create(Member member) throws DaoException;
	/**
	 * 
	 * @param member
	 * @throws DaoException
	 */
	public void update(Member member) throws DaoException;
	/**
	 * 
	 */
	public void delete(int id) throws DaoException;
	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public int count() throws DaoException;
}