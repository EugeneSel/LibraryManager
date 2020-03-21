package servlet;

import java.io.IOException;

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

public class AddMemberServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action == "/membre_add") {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_add.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IMemberService memberService = MemberService.getInstance();
		ILoanService loanService = LoanService.getInstance();
		
		try {
			int memberId = memberService.create(new Member(request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("adresse"),
															request.getParameter("email"), request.getParameter("telephone"), Member.SubscriptionType.BASIC));

			request.setAttribute("id", memberId);
			request.setAttribute("loanList", loanService.getListCurrentByMembre(memberId));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

			request.setAttribute("errorMessage", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_add.jsp");
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}