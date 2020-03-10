public class IBookDao implements IBookDao {
    public static BookDao instance;
    private IBookDao() { }	
    public static IBookDao getInstance() {
		if(instance == null) {
			instance = new IBookDao();
		}
		return instance;
	}

    private static final String SELECT_ALL_QUERY = "SELECT * FROM livre ORDER BY title, auteur;";
    private static final String SELECT_ONE_QUERY = "SELECT * FROM livre WHERE id=?;";
    private static final String CREATE_QUERY = "INSERT INTO livre(title, auteur, isbn) VALUES (?, ?, ?);";
	private static final String UPDATE_QUERY = "UPDATE livre SET title = ?, auteur = ?, isbn = ? WHERE id = ?";
	private static final String DELETE_QUERY = "DELETE FROM livre WHERE id=?;";
    private static final String COUNT_QUERY = "SELECT count(id) AS count FROM livre";

	public List<Book> getList() throws DaoException{
          List<Bokk> books = new ArrayList<>();

        try (Connection connection = EstablishConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet result = preparedStatement.executeQuery();) {

            while (result.next()) {
                books.add(new Book(result.getInt("id"), result.getString("title"), result.getString("title"), result.getString("isbn"))))
                
            }
        }
    };
	public Book getById(int id) throws DaoException;
	public int create(Book book) throws DaoException{

    };
	public void update(Book book) throws DaoException;
	public void delete(int id) throws DaoException;
	public int count() throws DaoException;
}