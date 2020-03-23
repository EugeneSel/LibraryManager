/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-03-23 01:00:35 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.View;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import model.Book;
import service.IBookService;
import service.impl.BookService;
import model.Loan;
import java.util.List;

public final class livre_005fdetails_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

 private Book book = new Book();
 private IBookService bookService = BookService.getInstance();
 private List<Loan> loanList = new ArrayList<Loan>();
  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write('\n');
      out.write('\n');
 book = bookService.getById((int) request.getAttribute("id"));
      out.write('\n');
      out.write('\n');
 loanList = (List) request.getAttribute("loanList");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("  <meta charset=\"UTF-8\">\n");
      out.write("  <title>Library Management</title>\n");
      out.write("  <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>\n");
      out.write("  <link href=\"https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css\">\n");
      out.write("  <link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">\n");
      out.write("  <link href=\"assets/css/custom.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("  ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "menu.jsp", out, false);
      out.write("\n");
      out.write("  <main>\n");
      out.write("    <section class=\"content\">\n");
      out.write("      <div class=\"page-announce valign-wrapper\">\n");
      out.write("        <a href=\"#\" data-activates=\"slide-out\" class=\"button-collapse valign hide-on-large-only\"><i class=\"material-icons\">menu</i></a>\n");
      out.write("        <h1 class=\"page-announce-text valign\">Fiche livre</h1>\n");
      out.write("      </div>\n");
      out.write("\n");
      out.write("      ");
 if (request.getAttribute("errorMessage") != null) { 
      out.write("\n");
      out.write("        <div>\n");
      out.write("          <p align=\"center\">");
      out.print( (String) request.getAttribute("errorMessage") );
      out.write("</p>\n");
      out.write("        </div>\n");
      out.write("      ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("      <div class=\"row\">\n");
      out.write("      <div class=\"container\">\n");
      out.write("      <h5>Détails du livre n:");
      out.print( book.getId() );
      out.write("</h5>\n");
      out.write("        <div class=\"row\">\n");
      out.write("\t      <form action='livre_details?id=");
      out.print( book.getId() );
      out.write("' method=\"post\" class=\"col s12\">\n");
      out.write("\t        <div class=\"row\">\n");
      out.write("\t          <div class=\"input-field col s12\">\n");
      out.write("\t            <input id=\"titre\" type=\"text\" value='");
      out.print( book.getTitle() );
      out.write("' name=\"titre\">\n");
      out.write("\t            <label for=\"titre\">Titre</label>\n");
      out.write("\t          </div>\n");
      out.write("\t        </div>\n");
      out.write("\t        <div class=\"row\">\n");
      out.write("\t          <div class=\"input-field col s6\">\n");
      out.write("\t            <input id=\"auteur\" type=\"text\" value='");
      out.print( book.getAuthor() );
      out.write("' name=\"auteur\">\n");
      out.write("\t            <label for=\"auteur\">Auteur</label>\n");
      out.write("\t          </div>\n");
      out.write("\t          <div class=\"input-field col s6\">\n");
      out.write("\t            <input id=\"isbn\" type=\"text\" value='");
      out.print( book.getIsbn() );
      out.write("' name=\"isbn\">\n");
      out.write("\t            <label for=\"isbn\">ISBN 13</label>\n");
      out.write("\t          </div>\n");
      out.write("\t        </div>\n");
      out.write("\t        <div class=\"row center\">\n");
      out.write("\t          <button class=\"btn waves-effect waves-light\" type=\"submit\">Enregistrer</button>\n");
      out.write("\t          <button class=\"btn waves-effect waves-light orange\" type=\"reset\">Annuler</button>\n");
      out.write("\t        </div>\n");
      out.write("\t      </form>\n");
      out.write("\t      \n");
      out.write("\t      <form action='livre_delete' method=\"get\" class=\"col s12\">\n");
      out.write("\t        <input type=\"hidden\" value='");
      out.print( book.getId() );
      out.write("' name=\"id\">\n");
      out.write("\t        <div class=\"row center\">\n");
      out.write("\t          <button class=\"btn waves-effect waves-light red\" type=\"submit\">Supprimer le livre\n");
      out.write("\t            <i class=\"material-icons right\">delete</i>\n");
      out.write("\t          </button>\n");
      out.write("\t        </div>\n");
      out.write("\t      </form>\n");
      out.write("\t    </div>\n");
      out.write("        <div class=\"row\">\n");
      out.write("          <div class=\"col s12\">\n");
      out.write("\t        <table class=\"striped\">\n");
      out.write("              <thead>\n");
      out.write("                <tr>\n");
      out.write("                  <th>Emprunteur</th>\n");
      out.write("                  <th>Date d'emprunt</th>\n");
      out.write("                  <th>Retour</th>\n");
      out.write("                </tr>\n");
      out.write("              </thead>\n");
      out.write("              <tbody id=\"results\">\n");
      out.write("                ");
 if (!loanList.isEmpty()) {
                  for (Loan loan : loanList) { 
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                      <td>");
      out.print( loan.getMember().getFirstName() );
      out.write(' ');
      out.print( loan.getMember().getLastName() );
      out.write("</td>\n");
      out.write("                      <td>");
      out.print( loan.getLoanDate() );
      out.write("</td>\n");
      out.write("                      <td>\n");
      out.write("                        <a href='emprunt_return?id=");
      out.print( loan.getId() );
      out.write("'><ion-icon class=\"table-item\" name=\"log-in\"></a>\n");
      out.write("                      </td>\n");
      out.write("                    </tr>\n");
      out.write("                  ");
 }
                } 
      out.write("\n");
      out.write("              </tbody>\n");
      out.write("            </table>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("      </div>\n");
      out.write("      </div>\n");
      out.write("    </section>\n");
      out.write("  </main>\n");
      out.write("  ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
