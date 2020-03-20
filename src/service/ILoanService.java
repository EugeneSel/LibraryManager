package service;

import model.Member;
import java.time.LocalDate;
import java.util.List;



import exception.ServiceException;
import model.Loan;

public interface ILoanService {
	/**
	 * Get the list of all the loans
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<Loan> getList() throws ServiceException;

	/**
	 * Get the list of active loans
	 * 
	 */
	public List<Loan> getListCurrent() throws ServiceException;

	/**
	 * Get the list of active loans of a member by his id
	 * 
	 * @param idMembre
	 * @return
	 * @throws ServiceException
	 */
	public List<Loan> getListCurrentByMembre(int idMembre) throws ServiceException;
	
	/**
	 * Get the list of active loans of a book by its id
	 * 
	 * @param idLivre
	 * @return
	 * @throws ServiceException
	 */
	public List<Loan> getListCurrentByLivre(int idLivre) throws ServiceException;
	
	/**
	 * Get a loan by its id
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public Loan getById(int id) throws ServiceException;
	
	/**
	 * Create a new loan
	 * 
	 * @param idMembre
	 * @param idLivre
	 * @param dateEmprunt
	 * @throws ServiceException
	 */
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException;
	
	/**
	 * Delete a loan by its id
	 * 
	 * @param id
	 * @throws ServiceException
	 */
	public void returnBook(int id) throws ServiceException;

	/**
	 * Get the number of loans in the database
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public int count() throws ServiceException;

	/**
	 * Verify if a book is available by its id
	 * 
	 * @param idLivre
	 * @return
	 * @throws ServiceException
	 */
	public boolean isLivreDispo(int idLivre) throws ServiceException;

	/**
	 * Verify if a member can make another loan by his id
	 * 
	 * @param membre
	 * @return
	 * @throws ServiceException
	 */
	public boolean isEmpruntPossible(Member membre) throws ServiceException ;
}
