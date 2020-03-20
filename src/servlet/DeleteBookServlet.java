package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.ServiceException;
import service.IBookService;
import service.impl.BookService;
import model.Book;

public class DeleteBookServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action == "/livre_delete") {
            // Set default value of the "id" option:
            int id = -1;
			// Change it while receiving an another value:
            if (request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id"));
				
			// Get the list of all books:
			IBookService bookService = BookService.getInstance();
			List<Book> bookList = new ArrayList<>();
			
			try {
                bookList = bookService.getList();
                if (id > -1)
                    request.setAttribute("id", id);
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			request.setAttribute("bookList", bookList);

			// Submit gathered information th the appropriate .jsp:
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_delete.jsp");
			dispatcher.forward(request, response);
		}
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IBookService bookService = BookService.getInstance();
        ServletException se = new ServletException("Error. No book has been chosen.");		
		List<Book> bookList = new ArrayList<>();
		
        try {
            if (request.getParameter("id") == "")
                throw se;
            else {
				bookService.delete(Integer.parseInt(request.getParameter("id")));
			
				// Get the list of the current Books:
				bookList = bookService.getList();
                
				request.setAttribute("bookList", bookList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_delete.jsp");
				dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ServletException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

			// Get the list of the current Books:
			try {
				bookList = bookService.getList();
			} catch (ServiceException serviceException) {
				System.out.println(serviceException.getMessage());
				serviceException.printStackTrace();
			}
                
			request.setAttribute("bookList", bookList);
			request.setAttribute("errorMessage", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_delete.jsp");
			dispatcher.forward(request, response);
		}
	}
}