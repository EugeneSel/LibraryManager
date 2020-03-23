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
import service.IMemberService;
import service.impl.BookService;
import service.impl.LoanService;
import service.impl.MemberService;
import model.Loan;

public class DashboardServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action.equals("/dashboard")) {
			// Get the number of members:
			IMemberService memberService = MemberService.getInstance();
			int numberOfMembers = -1;
			
			try {
				numberOfMembers = memberService.count();
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			request.setAttribute("numberOfMembers", numberOfMembers);

			// Get the number of books:
			IBookService bookService = BookService.getInstance();
			int numberOfBooks = -1;
			
			try {
				numberOfBooks = bookService.count();
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			request.setAttribute("numberOfBooks", numberOfBooks);

			// Get the number of loans:
			ILoanService loanService = LoanService.getInstance();
			int numberOfLoans = -1;
			
			try {
				numberOfLoans = loanService.count();
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			request.setAttribute("numberOfLoans", numberOfLoans);

			// Get the number of active loans:
			List<Loan> currentLoans = new ArrayList<>();
			
			try {
				currentLoans = loanService.getListCurrent();
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			request.setAttribute("currentLoans", currentLoans);

			// Submit gathered information th the appropriate .jsp:
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/dashboard.jsp");
			dispatcher.forward(request, response);
		}
    }
}