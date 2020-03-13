package dao.impl;

import java.sql.Connection;
import java.sql.Date;
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

public class LoanDao implements ILoanDao {
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
    
    private static final String SELECT_ALL_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre ORDER BY dateRetour DESC;";
	private static final String SELECT_NOT_RETURNED_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL;";
	private static final String SELECT_NOT_RETURNED_MEM_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND membre.id = ?;";
	private static final String SELECT_NOT_RETURNED_LIV_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND livre.id = ?;";
	private static final String SELECT_ONE_QUERY = "SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE e.id = ?;";
	private static final String CREATE_QUERY = "INSERT INTO Emprunt (idMembre, idLivre, dateEmprunt, dateRetour) VALUES (?, ?, ?, ?);";
	private static final String UPDATE_QUERY = "UPDATE Emprunt SET idMembre=?, idLivre=?,dateEmprunt=?, dateRetour=? WHERE id=?;";
	private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM emprunt;";



    
    @Override
    public List<Loan> getList() throws DaoException {
        List<Loan> loans = new ArrayList<>();

        try (Connection connection = EstablishConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet result = preparedStatement.executeQuery();) {

            IMemberDao memberDao = MemberDao.getInstance();
            IBookDao bookDao = BookDao.getInstance();

            while (result.next()) {
                loans.add(new Loan(result.getInt("id"), memberDao.getById(result.getInt("idMembre")), bookDao.getById(result.getInt("idLivre")), result.getDate("dateEmprunt").toLocalDate(), result.getDate("dateRetour").toLocalDate()));
            }

            System.out.println("List of loans: " + loans);
        } catch (SQLException e) {
            throw new DaoException("Error while uploading list of loans from the database", e);
        }

        return loans;
    };

    @Override
    public List<Loan> getListCurrent() throws DaoException {
        List<Loan> currentLoans = new ArrayList<>();

        try (Connection connection = EstablishConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOT_RETURNED_QUERY);
             ResultSet result = preparedStatement.executeQuery();) {

            IMemberDao memberDao = MemberDao.getInstance();
            IBookDao bookDao = BookDao.getInstance();

            while (result.next()) {
                currentLoans.add(new Loan(result.getInt("id"), memberDao.getById(result.getInt("idMembre")), bookDao.getById(result.getInt("idLivre")), result.getDate("dateEmprunt").toLocalDate(), result.getDate("dateRetour").toLocalDate()));
            }

            System.out.println("List of active loans: " + currentLoans);
        } catch (SQLException e) {
            throw new DaoException("Error while uploading list of active loans from the database", e);
        }

        return currentLoans;
    };

    /**
     * The function to set id of wanted member in the "select current loans by member id query" 
     * 
     * @param preparedStatement
     * @param idMember
     * @return
     * @throws SQLException
     */
    public ResultSet prepareGetListCurrentByMemberStatement(PreparedStatement preparedStatement, int idMember) throws SQLException {
        preparedStatement.setInt(1, idMember);
        return preparedStatement.executeQuery();
    }

    @Override
	public List<Loan> getListCurrentByMember(int idMember) throws DaoException {
        List<Loan> currentMemberLoans = new ArrayList<>();
        
        try (Connection connection = EstablishConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOT_RETURNED_MEM_QUERY);
             ResultSet result = prepareGetListCurrentByMemberStatement(preparedStatement, idMember);) {

            IMemberDao memberDao = MemberDao.getInstance();
            IBookDao bookDao = BookDao.getInstance();

            while (result.next()) {
                currentMemberLoans.add(new Loan(result.getInt("id"), memberDao.getById(result.getInt("idMembre")), bookDao.getById(result.getInt("idLivre")), result.getDate("dateEmprunt").toLocalDate(), result.getDate("dateRetour").toLocalDate()));
            }

            System.out.println("List of active loans of the member whose id is " + idMember + ": " + currentMemberLoans);
        } catch (SQLException e) {
            throw new DaoException("Error while uploading list of active loans from the database", e);
        }

        return currentMemberLoans;
    };

    /**
     * The function to set id of wanted book in the "select current loans by book id query" 
     * 
     * @param preparedStatement
     * @param idBook
     * @return
     * @throws SQLException
     */
    public ResultSet prepareGetListCurrentByBookStatement(PreparedStatement preparedStatement, int idBook) throws SQLException {
        preparedStatement.setInt(1, idBook);
        return preparedStatement.executeQuery();
    }

    @Override
	public List<Loan> getListCurrentByBook(int idBook) throws DaoException {
        List<Loan> currentBookLoans = new ArrayList<>();
        
        try (Connection connection = EstablishConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOT_RETURNED_LIV_QUERY );
             ResultSet result = prepareGetListCurrentByBookStatement(preparedStatement, idBook);) {

            IMemberDao memberDao = MemberDao.getInstance();
            IBookDao bookDao = BookDao.getInstance();

            while (result.next()) {
                currentBookLoans.add(new Loan(result.getInt("id"), memberDao.getById(result.getInt("idMembre")), bookDao.getById(result.getInt("idLivre")), result.getDate("dateEmprunt").toLocalDate(), result.getDate("dateRetour").toLocalDate()));
            }

            System.out.println("List of active loans of the Book which id is " + idBook + ": " + currentBookLoans);
        } catch (SQLException e) {
            throw new DaoException("Error while uploading list of active loans from the database", e);
        }

        return currentBookLoans;
    };

    /**
     * The function to set id of wanted loan in the "select loan by id query" 
     * 
     * @param preparedStatement
     * @param id
     * @return
     * @throws SQLException
     */
    public ResultSet prepareGetByIdStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    @Override
	public Loan getById(int id) throws DaoException {
        Loan currentLoan = new Loan();
        
        try (Connection connection = EstablishConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_QUERY);
             ResultSet result = prepareGetListCurrentByBookStatement(preparedStatement, id);) {

            IMemberDao memberDao = MemberDao.getInstance();
            IBookDao bookDao = BookDao.getInstance();

            if (result.next()) {
                currentLoan = new Loan(result.getInt("id"), memberDao.getById(result.getInt("idMembre")), bookDao.getById(result.getInt("idLivre")), result.getDate("dateEmprunt").toLocalDate(), result.getDate("dateRetour").toLocalDate());
            }

            System.out.println("A loan which id is " + id + ": " + currentLoan);
        } catch (SQLException e) {
            throw new DaoException("Error while uploading list of active loans from the database", e);
        }

        return currentLoan;
    };

    /**
     * The function to fullfill the "create query" with appropriate data
     * 
     * @param preparedStatement
     * @param idMember
     * @param idBook
     * @param loanDate
     * @return
     * @throws SQLException
     */
    public ResultSet prepareCreateStatement(PreparedStatement preparedStatement, int idMember, int idBook, LocalDate loanDate) throws SQLException {
        preparedStatement.setInt(1, idMember);
        preparedStatement.setInt(2, idBook);
        preparedStatement.setDate(3, Date.valueOf(loanDate));
        preparedStatement.setDate(4, null);
        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
    }
    
    @Override
	public void create(int idMember, int idBook, LocalDate loanDate) throws DaoException {
        Loan loan = new Loan();
        
        try (Connection connection = EstablishConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);
             ResultSet result = prepareCreateStatement(preparedStatement, idMember, idBook, loanDate);) {

            if (result.next()) {
                IMemberDao memberDao = MemberDao.getInstance();
                IBookDao bookDao = BookDao.getInstance();
        
                loan = new Loan( memberDao.getById(idMember), bookDao.getById(idBook), loanDate, null);
            }

            System.out.println("The new loan: " + loan + "was successfully created.");
        } catch (SQLException e) {
            throw new DaoException("Error while creating a loan " + loan + " in the database", e);
        }
    };

    @Override
	public void update(Loan loan) throws DaoException {
        try (Connection connection = EstablishConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);) {
            
			preparedStatement.setInt(1, loan.getMember().getId());
            preparedStatement.setInt(2, loan.getBook().getId());
            preparedStatement.setDate(3, Date.valueOf(loan.getLoanDate()));
            preparedStatement.setDate(4, Date.valueOf(loan.getReturnDate()));
			preparedStatement.setInt(5, loan.getId());
			preparedStatement.executeUpdate();

			System.out.println("The loan " + loan + "was successfully updated.");
        } catch (SQLException e) {
			throw new DaoException("Error while updating a loan " + loan + " in the database", e);
		}
    };

    @Override
	public int count() throws DaoException {
        int numberOfLoans = -1;

        try (Connection connection = EstablishConnection.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(COUNT_QUERY);
             ResultSet result = preparedStatement.executeQuery();) {

            if (result.next()) {
                numberOfLoans = result.getInt(1);
                System.out.println("The number of loans in the database: " + numberOfLoans);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while counting the number of loans in the database", e);
        } 
        
        return numberOfLoans;
    };
}