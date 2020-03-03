// package dao.impl;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.util.ArrayList;
// import java.util.List;

// import dao.IMemberDao;
// import exception.DaoException;
// import model.Member;
// import utils.EstablishConnection;

// public class MemberDao implements IMemberDao {
//     private static MemberDao instance;
// 	private MemberDao() { }	
// 	public static IMemberDao getInstance() {
// 		if(instance == null) {
// 			instance = new MemberDao();
// 		}
// 		return instance;
// 	}
    
//     private static final String SELECT_ALL_QUERY = "SELECT * FROM membre ORDER BY nom, prenom;";
//     private static final String SELECT_ONE_QUERY = "SELECT * FROM membre WHERE id=?;";
//     private static final String CREATE_QUERY = "INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, ?);";
// 	private static final String UPDATE_QUERY = "UPDATE membre SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ?, abonnement = ? WHERE id = ?";
// 	private static final String DELETE_QUERY = "DELETE FROM membre WHERE id=?;";
//     private static final String COUNT_QUERY = "SELECT count(id) AS count FROM membre";

//     @Override
//     public List<Member> getList() throws DaoException {
//         List<Member> members = new ArrayList<>();

//         try (Connection connection = EstablishConnection.getConnection();
//              PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
//              ResultSet result = preparedStatement.executeQuery();) {

//             while (result.next()) {
//                 members.add(new Member(result.getInt("id"), ))
//             }
//         }
//     };

// 	public Member getById(int id) throws DaoException;
// 	public int create(String lastName, String firstName, String address, String email, String phoneNumber) throws DaoException;
// 	public void update(Member member) throws DaoException;
// 	public void delete(int id) throws DaoException;
// 	public int count() throws DaoException;   
// }