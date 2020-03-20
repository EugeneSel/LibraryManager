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
import service.IMemberService;
import service.ILoanService;
import service.impl.MemberService;
import service.impl.LoanService;
import model.Member;
import model.Loan;

public class MemberDetailsServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action == "/membre_details") {
            // Set default value of the "id" option:
            int id = -1;
			// Change it while receiving an another value:
            if (request.getParameter("id") != null)
				id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id);

            // Get the list of loans of a chosen member:
            ILoanService loanService = LoanService.getInstance();
            List<Loan> loanList = new ArrayList<Loan>();

			try {
                if (id > -1) {
                    request.setAttribute("id", id);
                    loanList = loanService.getListCurrentByMembre(id);
                    request.setAttribute("loanList", loanList);
                }
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
            }
        
			// Submit gathered information th the appropriate .jsp:
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
			dispatcher.forward(request, response);
		}
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IMemberService memberService = MemberService.getInstance();
		ILoanService loanService = LoanService.getInstance();
		ServletException se = new ServletException("Can't update a member, some data hasn't been received.");
		
		try {
			if (request.getParameter("nom") == "" || request.getParameter("prenom") == "" || request.getParameter("adresse") == ""
                || request.getParameter("email") == "" || request.getParameter("telephone") == "" || request.getParameter("abonnement") == "")
				throw se;
			else {
                memberService.update(new Member(Integer.parseInt(request.getParameter("id")), request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("adresse"),
                                                                request.getParameter("email"), request.getParameter("telephone"), Member.SubscriptionType.valueOf(request.getParameter("abonnement"))));
				request.setAttribute("loanList", loanService.getListCurrentByMembre(Integer.parseInt(request.getParameter("id"))));
				request.setAttribute("id", Integer.parseInt(request.getParameter("id")));
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
				dispatcher.forward(request, response);
            }
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ServletException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

			try {
				request.setAttribute("loanList", loanService.getListCurrentByMembre(Integer.parseInt(request.getParameter("id"))));
			} catch (ServiceException serviceException) {
				System.out.println(serviceException.getMessage());
				serviceException.printStackTrace();
			}
			
			request.setAttribute("id", Integer.parseInt(request.getParameter("id")));
			request.setAttribute("errorMessage", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
			dispatcher.forward(request, response);
		}
	}
}