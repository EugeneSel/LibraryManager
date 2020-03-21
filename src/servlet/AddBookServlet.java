package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.ServiceException;
import service.IBookService;
import service.ILoanService;
import service.impl.BookService;
import service.impl.LoanService;
import model.Book;

public class AddBookServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action == "/livre_add") {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_add.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IBookService bookService = BookService.getInstance();
		ILoanService loanService = LoanService.getInstance();	
		
		try {
			int bookId = bookService.create(new Book(request.getParameter("titre"), request.getParameter("auteur"), request.getParameter("isbn")));

			request.setAttribute("id", bookId);
			request.setAttribute("loanList", loanService.getListCurrentByLivre(bookId));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_details.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

			request.setAttribute("errorMessage", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_add.jsp");
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}