package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.IMemberDao;
import exception.DaoException;
import model.Member;
import utils.EstablishConnection;

public class MemberDao implements IMemberDao {
    /**
     * According to Singleton pattern
     */
    private static MemberDao instance;

    private MemberDao() {};
    	
	public static IMemberDao getInstance() {
		if(instance == null) {
			instance = new MemberDao();
        }
        
		return instance;
	}
    
    private static final String SELECT_ALL_QUERY = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre ORDER BY nom, prenom;";
	private static final String SELECT_ONE_QUERY = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre WHERE id=?;";
	private static final String CREATE_QUERY = "INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, ?);";
	private static final String UPDATE_QUERY = "UPDATE membre SET nom=?, prenom=?, adresse=?, email=?, telephone=?, abonnement=? WHERE id=?;";
	private static final String DELETE_QUERY = "DELETE FROM membre WHERE id=?;";
	private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM membre;";


    @Override
    public List<Member> getList() throws DaoException {
        List<Member> members = new ArrayList<>();

        try (Connection connection = EstablishConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet result = preparedStatement.executeQuery();) {

            while (result.next()) {
                members.add(new Member(result.getInt("id"), result.getString("nom"), result.getString("prenom"), result.getString("adresse"),
                                        result.getString("email"), result.getString("telephone"), Member.SubscriptionType.valueOf(result.getString("abonnement"))));
            }

            System.out.println("List of members: " + members);
        } catch (SQLException e) {
            throw new DaoException("Error while uploading list of members from the database", e);
        }

        return members;
    };

    /**
     * The function to set id of wanted member in the "select member by id query" 
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
	public Member getById(int id) throws DaoException {
        Member member = new Member();
        
        try (Connection connection = EstablishConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_QUERY);
             ResultSet result = prepareGetByIdStatement(preparedStatement, id);) {
            
            if (result.next()) {
                member.setId(result.getInt("id"));
                member.setLastName(result.getString("nom"));
                member.setFirstName(result.getString("prenom"));
                member.setAddress(result.getString("adresse"));
                member.setEmail(result.getString("email"));
                member.setPhoneNumber(result.getString("telephone"));
                member.setSubscription(Member.SubscriptionType.valueOf(result.getString("abonnement")));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while uploading a members whose id is " + id + " from the database", e);
        }

        return member;
    };

    /**
     * The function to fullfill the "create query" with appropriate data 
     * 
     * @param preparedStatement
     * @param member
     * @return
     * @throws SQLException
     */
    public ResultSet prepareCreateStatement(PreparedStatement preparedStatement, Member member) throws SQLException {
        preparedStatement.setString(1, member.getLastName());
        preparedStatement.setString(2, member.getFirstName());
        preparedStatement.setString(3, member.getAddress());
        preparedStatement.setString(4, member.getEmail());
        preparedStatement.setString(5, member.getPhoneNumber());
        preparedStatement.setString(6, member.getSubscription().toString());
        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
    }

    @Override
	public int create(Member member) throws DaoException {
        int id = -1;
        
        try (Connection connection = EstablishConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
             ResultSet result = prepareCreateStatement(preparedStatement, member);) {

            if (result.next()) {
                id = result.getInt(1);
            }

            System.out.println("The new member: " + member + " was successfully created.");
        } catch (SQLException e) {
            throw new DaoException("Error while creating a member " + member + " in the database", e);
        }

        return id;
    };

    @Override
	public void update(Member member) throws DaoException {
        try (Connection connection = EstablishConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);) {
            
			preparedStatement.setString(1, member.getLastName());
            preparedStatement.setString(2, member.getFirstName());
            preparedStatement.setString(3, member.getAddress());
            preparedStatement.setString(4, member.getEmail());
            preparedStatement.setString(5, member.getPhoneNumber());
            preparedStatement.setString(6, member.getSubscription().toString());
			preparedStatement.setInt(7, member.getId());
			preparedStatement.executeUpdate();

			System.out.println("The member " + member + " was successfully updated.");
        } catch (SQLException e) {
			throw new DaoException("Error while updating a member " + member + " in the database", e);
		}
    };

    @Override
	public void delete(int id) throws DaoException {
		try (Connection connection = EstablishConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);) {
            
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            
			System.out.println("The member whose id is " + id + " was successfully deleted.");
		} catch (SQLException e) {
			throw new DaoException("Error while deleting a member whose id is " + id + " from the database", e);
		}
    };

    @Override
	public int count() throws DaoException {
        int numberOfMembers = -1;

        try (Connection connection = EstablishConnection.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(COUNT_QUERY);
             ResultSet result = preparedStatement.executeQuery();) {

            if (result.next()) {
                numberOfMembers = result.getInt(1);
                
                System.out.println("The number of members in the database: " + numberOfMembers);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while counting the number of members in the database", e);
        } 
        
        return numberOfMembers;
    };   
}