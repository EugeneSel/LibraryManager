package service;

import model.Member;
import java.util.List;


import exception.ServiceException;

public interface IMemberService {
	/**
	 * Get the list of all members
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<Member> getList() throws ServiceException;

	/**
	 * Get the list of members who can make a loan
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<Member> getListMembreEmpruntPossible() throws ServiceException;
	
	/**
	 * Get a member by his id
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public Member getById(int id) throws ServiceException;
	
	/**
	 * Create a new member
	 * 
	 * @param membre
	 * @return
	 * @throws ServiceException
	 */
	public int create(Member membre) throws ServiceException;

	/**
	 * Update the information of an existing member
	 * 
	 * @param membre
	 * @throws ServiceException
	 */
	public void update(Member membre) throws ServiceException;

	/**
	 * Delete a member by his id
	 * 
	 * @param id
	 * @throws ServiceException
	 */
	public void delete(int id) throws ServiceException;

	/**
	 * Get the number of members in the database
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public int count() throws ServiceException;
}
