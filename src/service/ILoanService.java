package service;

import model.Member;
import java.time.LocalDate;
import java.util.List;



import exception.ServiceException;
import model.Loan;

public interface ILoanService {
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<Loan> getList() throws ServiceException;
	/**
	 * 
	 */
	public List<Loan> getListCurrent() throws ServiceException;
	/**
	 * 
	 * @param idMembre
	 * @return
	 * @throws ServiceException
	 */
	public List<Loan> getListCurrentByMembre(int idMembre) throws ServiceException;
	/**
	 * 
	 * @param idLivre
	 * @return
	 * @throws ServiceException
	 */
	public List<Loan> getListCurrentByLivre(int idLivre) throws ServiceException;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public Loan getById(int id) throws ServiceException;
	/**
	 * 
	 * @param idMembre
	 * @param idLivre
	 * @param dateEmprunt
	 * @throws ServiceException
	 */
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException;
	/**
	 * 
	 * @param id
	 * @throws ServiceException
	 */
	public void returnBook(int id) throws ServiceException;
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public int count() throws ServiceException;
	/**
	 * 
	 * @param idLivre
	 * @return
	 * @throws ServiceException
	 */
	public boolean isLivreDispo(int idLivre) throws ServiceException;
	/**
	 * 
	 * @param membre
	 * @return
	 * @throws ServiceException
	 */
	public boolean isEmpruntPossible(Member membre) throws ServiceException ;
}
