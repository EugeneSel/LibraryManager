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

public class LoanListServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action.equals("/emprunt_list")) {
            // Set default value of the "show" option:
            String show = "current";
            // Change it while receiving an another value:
            if (request.getParameter("show") != null && request.getParameter("show").contains("all"))
                show = "all";

			// Get the list of loans (all or current):
			ILoanService loanService = LoanService.getInstance();
			List<Loan> loanList = new ArrayList<>();
			
			try {
                // Making a choice according to the value of a "show" option:
                if (show == "current") {
                    loanList = loanService.getListCurrent();
                    request.setAttribute("show", "all");
                } else {
                    loanList = loanService.getList();
                    request.setAttribute("show", "current");
                }
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			request.setAttribute("loanList", loanList);

			// Submit gathered information th the appropriate .jsp:
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp");
			dispatcher.forward(request, response);
		}
    }
}