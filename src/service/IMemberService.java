package service;

import model.Member;
import java.util.List;


import exception.ServiceException;

public interface IMemberService {
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<Member> getList() throws ServiceException;
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<Member> getListMembreEmpruntPossible() throws ServiceException;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public Member getById(int id) throws ServiceException;
	/**
	 * 
	 * @param membre
	 * @return
	 * @throws ServiceException
	 */
	public int create(Member membre) throws ServiceException;
	/**
	 * 
	 * @param membre
	 * @throws ServiceException
	 */
	public void update(Member membre) throws ServiceException;
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
