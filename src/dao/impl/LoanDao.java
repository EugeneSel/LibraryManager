package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.ILoanDao;
import dao.*;
import exception.DaoException;
import model.Loan;
import utils.EstablishConnection;

class LoanDao implements ILoanDao {
    /**
     * According to Singleton pattern
     */
    private static LoanDao instance;

    private LoanDao() {};
    
	public static ILoanDao getInstance() {
		if(instance == null) {
			instance = new LoanDao();
        }
        
		return instance;
	}
    
    private static final String SELECT_ALL_LOANS_QUERY = "SELECT * FROM emprunt ORDER BY dateEmprunt, dateRetour;";
    private static final String SELECT_ALL_CURRENT_LOANS_QUERY = "SELECT * FROM emprunt WHERE dateRetour IS NOT NULL ORDER BY dateEmprunt;";
    private static final String SELECT_CURRENT_LOANS_BY_MEMBER_ID_QUERY = "SELECT * FROM emprunt WHERE idMembre = ? AND dateRetour IS NOT NULL ORDER BY dateEmprunt;";
    private static final String SELECT_CURRENT_LOANS_BY_BOOK_ID_QUERY = "SELECT * FROM emprunt WHERE idLivre = ? AND dateRetour IS NOT NULL ORDER BY dateEmprunt;";
    private static final String SELECT_LOAN_BY_ID_QUERY = "SELECT * FROM emprunt WHERE id = ?;";
    private static final String CREATE_QUERY = "INSERT INTO emprunt(nom, prenom, adresse, email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, ?);";
	private static final String UPDATE_QUERY = "UPDATE emprunt SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ?, abonnement = ? WHERE id = ?";
	// private static final String DELETE_QUERY = "DELETE FROM emprunt WHERE id=?;";
    private static final String COUNT_QUERY = "SELECT count(id) AS count FROM emprunt";
    
    @Override
    public List<Loan> getList() throws DaoException {
        List<Loan> loans = new ArrayList<>();

        try (Connection connection = EstablishConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOANS_QUERY);
             ResultSet result = preparedStatement.executeQuery();) {

            IMemberDao memberDao = MemberDao.getInstance();
            IBookDao bookDao = BookDao.getInstance();

            while (result.next()) {
                loans.add(new Loan(result.getInt("id"), MemberDaoresult.getInt("idMembre"), result.getInt("idLivre"), result.getDate("dateEmprunt").toLocalDate(), result.getDate("dateRetour").toLocalDate()));
            }

            System.out.println("List of loans: " + loans);
        } catch (SQLException e) {
            throw new DaoException("Error while uploading list of loans from the database", e);
        }

        return loans;
    };

    @Override
    public List<Loan> getListCurrent() throws DaoException {

    };

    @Override
	public List<Loan> getListCurrentByMember(int idMember) throws DaoException {

    };

    @Override
	public List<Loan> getListCurrentByBook(int idBook) throws DaoException {

    };

    @Override
	public Loan getById(int id) throws DaoException {

    };

    @Override
	public void create(int idMember, int idBook, LocalDate loanDate) throws DaoException {

    };

    @Override
	public void update(Loan Loan) throws DaoException {

    };

    @Override
	public int count() throws DaoException {

    };
}