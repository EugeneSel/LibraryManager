package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

import exception.ServiceException;
import service.IBookService;
import service.ILoanService;
import service.IMemberService;
import service.impl.BookService;
import service.impl.LoanService;
import service.impl.MemberService;
import model.Book;
import model.Member;

public class AddLoanServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action == "/emprunt_add") {
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
		
		try {
			if (request.getParameter("idMembre") == null || request.getParameter("idLivre") == null)
				throw se;
			else {
				loanService.create(Integer.parseInt(request.getParameter("idMembre")), Integer.parseInt(request.getParameter("idLivre")), LocalDate.now());
			
				response.sendRedirect("emprunt_list");
			}
			// request.setAttribute("show", "all");
			// RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp");
			// dispatcher.forward(request, response);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ServletException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}