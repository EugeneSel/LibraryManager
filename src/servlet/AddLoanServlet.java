package servlet;

import java.io.IOException;
import java.time.LocalDate;
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
import model.Book;
import model.Loan;
import model.Member;

public class AddLoanServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action.equals("/emprunt_add")) {
			// Get the list of members who could make a loan:
			IMemberService memberService = MemberService.getInstance();
			List<Member> availableMemberList = new ArrayList<>();
			
			try {
				availableMemberList = memberService.getListMembreEmpruntPossible();
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			request.setAttribute("availableMemberList", availableMemberList);

			// Get the list of books whick wasn't loaned yet:
			IBookService bookService = BookService.getInstance();
			List<Book> availableBookList = new ArrayList<>();
			
			try {
				availableBookList = bookService.getListDispo();
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			request.setAttribute("availableBookList", availableBookList);
		
			// Submit gathered information th the appropriate .jsp:
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
			dispatcher.forward(request, response);
		}
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ILoanService loanService = LoanService.getInstance();
		ServletException se = new ServletException("Can't add a new loan, some data hasn't been received.");
		List<Loan> loanList = new ArrayList<>();

		try {
			if (request.getParameter("idMembre") == null || request.getParameter("idLivre") == null)
				throw se;
			else {
				loanService.create(Integer.parseInt(request.getParameter("idMembre")), Integer.parseInt(request.getParameter("idLivre")), LocalDate.now());
			
				// Get the list of the current loans :
				loanList = loanService.getListCurrent();
				
				request.setAttribute("loanList", loanList);
				request.setAttribute("show", "all");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp");
				dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ServletException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			// Get the list of members who could make a loan:
			IMemberService memberService = MemberService.getInstance();
			List<Member> availableMemberList = new ArrayList<>();
			
			try {
				availableMemberList = memberService.getListMembreEmpruntPossible();
			} catch (ServiceException serviceException) {
				System.out.println(serviceException.getMessage());
				serviceException.printStackTrace();
			}
			
			request.setAttribute("availableMemberList", availableMemberList);

			// Get the list of books whick wasn't loaned yet:
			IBookService bookService = BookService.getInstance();
			List<Book> availableBookList = new ArrayList<>();
			
			try {
				availableBookList = bookService.getListDispo();
			} catch (ServiceException serviceException) {
				System.out.println(serviceException.getMessage());
				serviceException.printStackTrace();
			}
			
			request.setAttribute("availableBookList", availableBookList);
			request.setAttribute("errorMessage", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
			dispatcher.forward(request, response);
		}
	}
}