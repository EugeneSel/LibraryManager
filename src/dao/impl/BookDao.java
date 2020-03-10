public class IBookDao implements IBookDao {
    public static BookDao instance;
    private IBookDao() { }	
    public static IBookDao getInstance() {
		if(instance == null) {
			instance = new IBookDao();
		}
		return instance;
	}

    private static final String SELECT_ALL_QUERY = "SELECT * FROM livre ";
    private static final String SELECT_ONE_QUERY = "SELECT * FROM livre WHERE id=?;";
    private static final String CREATE_QUERY = "INSERT INTO livre(title, auteur, isbn) VALUES (?, ?, ?);";
	private static final String UPDATE_QUERY = "UPDATE livre SET title = ?, auteur = ?, isbn = ? WHERE id = ?";
	private static final String DELETE_QUERY = "DELETE FROM livre WHERE id=?;";
    private static final String COUNT_QUERY = "SELECT count(id) AS count FROM livre";
    
    
    @Override
	public List<Book> getList() throws DaoException{
		List<Book> books = new ArrayList<Book>();
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
			res = preparedStatement.executeQuery();
			while(res.next()) {
				Book book  = new Book(res.getInt("id"), res.getString("titre"), res.getString("auteur"), res.getString("isbn"));
				books.add(book);			//creation de la list à partir des resultats de la requete
			}
			System.out.println("GET: " + Books);
		} catch (SQLException e) {
			throw new DaoException("Problème lors de la récupération de la liste des Books", e);
		} finally {
			try {
				res.close();
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
		return books;

    };

    @Override
	public Book getById(int id) throws DaoException;
    {
        Member book = new Book();
        ResultSet result = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = EstablishConnection.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ONE_QUERY);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();
            
            if (result.next()) {
                member.setId(result.getInt("id"));
                member.setTitle(result.getString("title"));
                member.setAuthor(result.getString("auteur"));
                member.setIsbn(result.getString("isbn"));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while uploading a book whose id is " + id + " from the database", e);
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

        return book;
    };

    @Override
	public int create(Book book) throws DaoException{
        ResultSet result = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = EstablishConnection.getConnection();
            preparedStatement = connection.prepareStatement(CREATE_QUERY);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();
            
            if (result.next()) {
                member.setId(result.getInt("id"));
                member.setTitle(result.getString("title"));
                member.setAuthor(result.getString("auteur"));
                member.setIsbn(result.getString("isbn"));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while uploading a book whose id is " + id + " from the database", e);
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

        return book;
    };
    @Override
	public void update(Book book) throws DaoException{

    };

    @Override
	public void delete(int id) throws DaoException{

    };

    @Override
	public int count() throws DaoException{

    };
}