package dao;

import java.time.LocalDate;
import java.util.List;

import exception.DaoException;
import model.Loan;

public interface ILoanDao {
	public List<Loan> getList() throws DaoException;
	public List<Loan> getListCurrent() throws DaoException;
	public List<Loan> getListCurrentByMember(int idMember) throws DaoException;
	public List<Loan> getListCurrentByBook(int idBook) throws DaoException;
	public Loan getById(int id) throws DaoException;
	public void create(int idMember, int idBook, LocalDate loanDate) throws DaoException;
	public void update(Loan loan) throws DaoException;
	public int count() throws DaoException;
}
