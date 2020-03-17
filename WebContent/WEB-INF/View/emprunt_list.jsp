<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Loan" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%! private List<Loan> loanList = new ArrayList<>();%>
<% loanList = (List) request.getAttribute("loanList"); %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Library Management</title>
  <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <jsp:include page='menu.jsp'></jsp:include>
  <main>
    <section class="content">
      <div class="page-announce valign-wrapper">
        <a href="#" data-activates="slide-out" class="button-collapse valign hide-on-large-only"><i class="material-icons">menu</i></a>
        <h1 class="page-announce-text valign">Liste des emprunts</h1>
      </div>
      <div class="row">
        <div class="container">
	        <div class="col s12">
            <a href='emprunt_list?show=<%= (String) request.getAttribute("show") %>'>Show <%= (String) request.getAttribute("show") %> loans</a>
	          <table class="striped">
                <thead>
                    <tr>
                        <th>Livre</th>
                        <th>Membre emprunteur</th>
                        <th>Date d'emprunt</th>
                        <th>Retour</th>
                    </tr>
                </thead>
                <tbody id="results">
                  <% if (!loanList.isEmpty()) {
                    for (Loan loan : loanList) { %>
                      <tr>
                        <td><%= loan.getBook().getTitle() %>, <em><%= loan.getBook().getAuthor() %></em></td>
                        <td><%= loan.getMember().getLastName() %> <%= loan.getMember().getFirstName() %></td>
                        <td><%= loan.getLoanDate() %></td>
                        <td>
                          <% if (loan.getReturnDate() == null) { %>
                            <a href='emprunt_return?id=<%= loan.getId() %>'><ion-icon class="table-item" name="log-in"></a>
                          <% } else { %>
                            <%= loan.getReturnDate() %>
                          <% } %>
                        </td>
                      </tr>
                    <% }
                  } %>
                </tbody>
            </table>
          </div>
        </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
</body>
</html>
