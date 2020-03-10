package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.CellEditor;

import dao.IMemberDao;
import exception.DaoException;
import model.Member;
import utils.EstablishConnection;

public class MemberDao implements IMemberDao {
    private static MemberDao instance;
	private MemberDao() { }	
	public static IMemberDao getInstance() {
		if(instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}
    
    private static final String SELECT_ALL_MEMBERS_QUERY = "SELECT * FROM membre ORDER BY nom, prenom;";
    private static final String SELECT_MEMBER_BY_ID_QUERY = "SELECT * FROM membre WHERE id=?;";
    private static final String CREATE_QUERY = "INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, ?);";
	private static final String UPDATE_QUERY = "UPDATE membre SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ?, abonnement = ? WHERE id = ?";
	private static final String DELETE_QUERY = "DELETE FROM membre WHERE id=?;";
    private static final String COUNT_QUERY = "SELECT count(id) AS count FROM membre";

    @Override
    public List<Member> getList() throws DaoException {
        List<Member> members = new ArrayList<>();

        try (Connection connection = EstablishConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MEMBERS_QUERY);
             ResultSet result = preparedStatement.executeQuery();) {

            while (result.next()) {
                members.add(new Member(result.getInt("id"), result.getString("nom"), result.getString("prenom"), result.getString("adresse"),
                                        result.getString("email"), result.getString("telephone"), Member.SubscriptionType.valueOf(result.getString("abonnement"))));
            }

            System.out.println("List of members: " + members);

            try {
				result.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

        } catch (SQLException e) {
            throw new DaoException("Error while uploading list of members from the database", e);
        }

        return members;
    };
    @Override
	public Member getById(int id) throws DaoException {
        Member member = new Member();
        ResultSet result = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = EstablishConnection.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_MEMBER_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();
            
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
        } finally {
			try {
				result.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

        return member;
    };

	public int create(Member member) throws DaoException {
        ResultSet result = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = EstablishConnection.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_MEMBER_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();
            
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
        } finally {
			try {
				result.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

        return member;
    };
	public void update(Member member) throws DaoException;
	public void delete(int id) throws DaoException;
	public int count() throws DaoException;   
}