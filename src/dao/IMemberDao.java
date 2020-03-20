package dao;

import java.util.List;

import exception.DaoException;
import model.Member;

public interface IMemberDao {
		/**
	 * Get the list of all members
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List<Member> getList() throws DaoException;
	
	/**
	 * Get the member by his id
	 *  
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Member getById(int id) throws DaoException;

	/**
	 * Create a new member
	 *  
	 * @param member
	 * @return
	 * @throws DaoException
	 */
	public int create(Member member) throws DaoException;

	/**
	 * Update the information of an existing member
	 * 
	 * @param member
	 * @throws DaoException
	 */
	public void update(Member member) throws DaoException;

	/**
	 * Delete a member using his id
	 *  
	 * @param id
	 * @throws DaoException
	 */
	public void delete(int id) throws DaoException;

	/**
	 * Get the number of members in the database 
	 * 
	 * @return
	 * @throws DaoException
	 */
	public int count() throws DaoException;
}