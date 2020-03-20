package dao;

import java.time.LocalDate;
import java.util.List;

import exception.DaoException;
import model.Loan;

public interface ILoanDao {
	/**
	 * Get the list of all loans
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List<Loan> getList() throws DaoException;

	/**
	 * Get the list of all active loans (in which the books are not returned)
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List<Loan> getListCurrent() throws DaoException;

	/**
	 * Get the list of all active loans of the member by his id
	 * 
	 * @param idMember
	 * @return
	 * @throws DaoException
	 */
	public List<Loan> getListCurrentByMember(int idMember) throws DaoException;
	
	/**
	 * Get the list of all active loans of the book by its id
	 * 
	 * @param idBook
	 * @return
	 * @throws DaoException
	 */
	public List<Loan> getListCurrentByBook(int idBook) throws DaoException;
	
	/**
	 * Get the loan by its id
	 *  
	 */
	public Loan getById(int id) throws DaoException;

	/**
	 * Create a new loan
	 * 
	 * @param idMember
	 * @param idBook
	 * @param loanDate
	 * @throws DaoException
	 */
	public void create(int idMember, int idBook, LocalDate loanDate) throws DaoException;
	
	/**
	 * Update the information of an existing loan
	 * 
	 */
	public void update(Loan loan) throws DaoException;
	
	/**
	 * Get the number of loans in the database
	 * 
	 */
	public int count() throws DaoException;
}
