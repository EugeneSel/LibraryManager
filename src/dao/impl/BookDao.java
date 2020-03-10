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
        List<Book> books = new ArrayList<>();

        try (Connection connection = EstablishConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet result = preparedStatement.executeQuery();) {

            while (result.next()) {
                books.add(new Book(result.getInt("id"), result.getString("title"), result.getString("auteur"), result.getString("isbn")));
            }

            System.out.println("List of books: " + books);
        } catch (SQLException e) {
            throw new DaoException("Error while uploading list of books from the database", e);
        }

        return books;
    };

    public ResultSet prepareGetByIdStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    @Override
	public Book getById(int id) throws DaoException;
    {
        Book book = new Book);
        
        try (Connection connection = EstablishConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_QUERY);
             ResultSet result = prepareGetByIdStatement(preparedStatement, id);) {
            
            if (result.next()) {
                book.setId(result.getInt("id"));
                book.setTitle(result.getString("title"));
                book.setAuthor(result.getString("auteur"));
                book.setIsbn(result.getString("isbn"));
            }
        } catch (SQLException e) {
            throw new DaoException("Error while uploading a books whose id is " + id + " from the database", e);
        }

        return book;
    };


    public ResultSet prepareCreateStatement(PreparedStatement preparedStatement, Book book) throws SQLException {
        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setString(2, book.getAuthor());
        preparedStatement.setString(3, book.getIsbn());
        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
    } 

    @Override
	public int create(Book book) throws DaoException{
         int id = -1;
        
        try (Connection connection = EstablishConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);
             ResultSet result = prepareCreateStatement(preparedStatement, book);) {

            if (result.next()) {
                id = result.getInt(1);
            }

            System.out.println("The new book: " + book + "was successfully created.");
        } catch (SQLException e) {
            throw new DaoException("Error while creating a book " + book + " in the database", e);
        }

        return id;
    };
    @Override
	public void update(Book book) throws DaoException{
        try (Connection connection = EstablishConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);) {
            
			preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getIsbn());
            preparedStatement.executeUpdate();

			System.out.println("The book " + book + "was successfully updated.");
        } catch (SQLException e) {
			throw new DaoException("Error while updating a book " + book + " in the database", e);
		}
    };

    @Override
	public void delete(int id) throws DaoException{

        try (Connection connection = EstablishConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);) {
            
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            
			System.out.println("The book whose id is " + id + " was successfully deleted.");
		} catch (SQLException e) {
			throw new DaoException("Error while deleting a book whose id is " + id + " from the database", e);
		}
    };

    @Override
	public int count() throws DaoException{
    int numberOfBooks = -1;

        try (Connection connection = EstablishConnection.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(COUNT_QUERY);
             ResultSet result = preparedStatement.executeQuery();) {

            if (result.next()) {
                numberOfBooks = result.getInt(1);
                System.out.println("The number of books in the database: " + numberOfBooks);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while counting the number of books in the database", e);
        } 
        
        return numberOfBooks;
    };
}