<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Member" %>
<%@ page import="service.IMemberService" %>
<%@ page import="service.impl.MemberService" %>
<%@ page import="model.Loan" %>
<%@ page import="java.util.List" %>

<%! private Member member = new Member();%>
<%! private IMemberService memberService = MemberService.getInstance();%>
<% member = memberService.getById((int) request.getAttribute("id"));%>
<%! private List<Loan> loanList = new ArrayList<Loan>();%>
<% loanList = (List) request.getAttribute("loanList");%>

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
        <h1 class="page-announce-text valign">Fiche membre</h1>
      </div>
      <div class="row">
      <div class="container">
      <h5>Dètails du membre n�<%= member.getId() %></h5>
        <div class="row">
	      <form action='membre_details?id=<%= member.getId() %>' method="post" class="col s12">
	        <div class="row">
	          <div class="input-field col s4">
	            <input id="nom" type="text" value='<%= member.getLastName() %>' name="nom">
	            <label for="nom">Nom</label>
	          </div>
	          <div class="input-field col s4">
	            <input id="prenom" type="text" value='<%= member.getFirstName() %>' name="prenom">
	            <label for="prenom">Pr�nom</label>
	          </div>
	          <div class="input-field col s4">
	            <select name="abonnement" class="browser-default">
	              <option value="BASIC" <%= (member.getSubscription().toString() == "BASIC") ? "selected" : "" %>>Abonnement BASIC</option>
	              <option value="PREMIUM" <%= (member.getSubscription().toString() == "PREMIUM") ? "selected" : "" %>>Abonnement PREMIUM</option>
	              <option value="VIP" <%= (member.getSubscription().toString() == "VIP") ? "selected" : "" %>>Abonnement VIP</option>
	            </select>
	          </div>
	        </div>
	        <div class="row">
	          <div class="input-field col s12">
	            <input id="adresse" type="text" value='<%= member.getAddress() %>' name="adresse">
	            <label for="adresse">Adresse</label>
	          </div>
	        </div>
	        <div class="row">
	          <div class="input-field col s6">
	            <input id="email" type="email" value='<%= member.getEmail() %>' name="email">
	            <label for="email">E-mail</label>
	          </div>
	          <div class="input-field col s6">
	            <input id="telephone" type="tel" value='<%= member.getPhoneNumber() %>' name="telephone">
	            <label for="telephone">Téléphone</label>
	          </div>
	        </div>
	        <div class="row center">
	          <button class="btn waves-effect waves-light" type="submit">Enregistrer</button>
	          <button class="btn waves-effect waves-light orange" type="reset">Annuler</button>
	        </div>
	      </form>
	      
	      <form action="membre_delete" method="get" class="col s12">
	        <input type="hidden" value='<%= member.getId() %>' name="id">
	        <div class="row center">
	          <button class="btn waves-effect waves-light red" type="submit">Supprimer le membre
	            <i class="material-icons right">delete</i>
	          </button>
	        </div>
	      </form>
	    </div>
        <div class="row">
          <div class="col s12">
	        <table class="striped">
              <thead>
                <tr>
                  <th>Livre</th>
                  <th>Date d'emprunt</th>
                  <th>Retour</th>
                </tr>
              </thead>
              <tbody id="results">
                <% if (!loanList.isEmpty()) {
                  for (Loan loan : loanList) { %>
                    <tr>
                      <td><%= loan.getBook().getTitle() %>, de <%= loan.getBook().getAuthor() %></td>
                      <td><%= loan.getLoanDate() %></td>
                      <td>
                        <a href='emprunt_return?id=<%= loan.getId() %>'><ion-icon class="table-item" name="log-in"></a>
                      </td>
                    </tr>
                  <% }
                } %>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
</body>
</html>
