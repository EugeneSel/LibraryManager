package dao;

import java.time.LocalDate;
import java.util.List;

import exception.DaoException;
import model.Loan;

public interface ILoanDao {
	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List<Loan> getList() throws DaoException;
	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List<Loan> getListCurrent() throws DaoException;
	/**
	 * 
	 * @param idMember
	 * @return
	 * @throws DaoException
	 */
	public List<Loan> getListCurrentByMember(int idMember) throws DaoException;
	/**
	 * 
	 * @param idBook
	 * @return
	 * @throws DaoException
	 */
	public List<Loan> getListCurrentByBook(int idBook) throws DaoException;
	/**
	 * 
	 */
	public Loan getById(int id) throws DaoException;
	/**
	 * 
	 * @param idMember
	 * @param idBook
	 * @param loanDate
	 * @throws DaoException
	 */
	public void create(int idMember, int idBook, LocalDate loanDate) throws DaoException;
	/**
	 * 
	 */
	public void update(Loan loan) throws DaoException;
	/**
	 * 
	 */
	public int count() throws DaoException;
}
