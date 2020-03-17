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
import service.ILoanService;
import service.impl.LoanService;
import model.Loan;

public class ReturnLoanServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action == "/emprunt_return") {
            // Set default value of the "id" option:
            int id = -1;
			// Change it while receiving an another value:
			System.out.println(request.getParameter("id"));
            if (request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id"));
				System.out.println(id);
				
			// Get the list of active loans:
			ILoanService loanService = LoanService.getInstance();
			List<Loan> loanList = new ArrayList<>();
			
			try {
                loanList = loanService.getListCurrent();
                if (id > -1)
                    request.setAttribute("id", id);
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			request.setAttribute("loanList", loanList);

			// Submit gathered information th the appropriate .jsp:
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp");
			dispatcher.forward(request, response);
		}
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ILoanService loanService = LoanService.getInstance();
        ServletException se = new ServletException("Error. No loan has been chosen.");
        
        try {
            if (request.getParameter("id") == null)
                throw se;
            else {
				System.out.println(request.getParameter("id"));
				loanService.returnBook(Integer.parseInt(request.getParameter("id")));
			
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