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
import service.ILoanService;
import service.impl.BookService;
import service.impl.LoanService;
import model.Book;
import model.Loan;

public class BookDetailsServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action == "/livre_details") {
            // Set default value of the "id" option:
            int id = -1;
			// Change it while receiving an another value:
            if (request.getParameter("id") != null)
				id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
            
            // Get the list of loans of a chosen book:
            ILoanService loanService = LoanService.getInstance();
            List<Loan> loanList = new ArrayList<Loan>();

			try {
                if (id > -1) {
                    request.setAttribute("id", id);
                    loanList = loanService.getListCurrentByLivre(id);
                    request.setAttribute("loanList", loanList);
                }
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
            }
        
			// Submit gathered information th the appropriate .jsp:
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_details.jsp");
			dispatcher.forward(request, response);
		}
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IBookService bookService = BookService.getInstance();
		ILoanService loanService = LoanService.getInstance();
		ServletException se = new ServletException("Can't update a book, some data hasn't been received.");
		
		try {
			if (request.getParameter("titre") == null || request.getParameter("auteur") == null || request.getParameter("isbn") == null)
				throw se;
			else {
                bookService.update(new Book(Integer.parseInt(request.getParameter("id")), request.getParameter("titre"), request.getParameter("auteur"), request.getParameter("isbn")));
				request.setAttribute("loanList", loanService.getListCurrentByLivre(Integer.parseInt(request.getParameter("id"))));
				request.setAttribute("id", Integer.parseInt(request.getParameter("id")));
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_details.jsp");
				dispatcher.forward(request, response);
            }
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ServletException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}