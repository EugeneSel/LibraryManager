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
import service.impl.MemberService;
import model.Member;

public class DeleteMemberServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action == "/membre_delete") {
            // Set default value of the "id" option:
            int id = -1;
			// Change it while receiving an another value:
            if (request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id"));
				
			// Get the list of all members:
			IMemberService memberService = MemberService.getInstance();
			List<Member> memberList = new ArrayList<>();
			
			try {
                memberList = memberService.getList();
                if (id > -1)
                    request.setAttribute("id", id);
			} catch (ServiceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			request.setAttribute("memberList", memberList);

			// Submit gathered information th the appropriate .jsp:
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_delete.jsp");
			dispatcher.forward(request, response);
		}
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IMemberService memberService = MemberService.getInstance();
        ServletException se = new ServletException("Error. No member has been chosen.");
		List<Member> memberList = new ArrayList<>();
		
        try {
            if (request.getParameter("id") == "")
                throw se;
            else {
				memberService.delete(Integer.parseInt(request.getParameter("id")));
			
				// Get the list of the current members :
				memberList = memberService.getList();
                
				request.setAttribute("memberList", memberList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_delete.jsp");
				dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ServletException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

			// Get the list of the current members:
			try {
				memberList = memberService.getList();
			} catch (ServiceException serviceException) {
				System.out.println(serviceException.getMessage());
				serviceException.printStackTrace();
			}
                
			request.setAttribute("memberList", memberList);
			request.setAttribute("errorMessage", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_delete.jsp");
			dispatcher.forward(request, response);
		}
	}
}