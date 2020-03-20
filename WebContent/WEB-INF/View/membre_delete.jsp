<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%! private List<Member> memberList = new ArrayList<>();%>
<% memberList = (List) request.getAttribute("memberList"); %>

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

      <% if (request.getAttribute("errorMessage") != null) { %>
        <div>
          <p><%= (String) request.getAttribute("errorMessage") %></p>
        </div>
      <% } %>

      <div class="row">
      <div class="container">
      <h5>Suppression de membre</h5>
        <div class="row">
          <form action="membre_delete" method="post" class="col s12">
            <div class="row">
              <div class="input-field col s12">
                <select id="id" name="id" class="browser-default">
                  <% if (request.getAttribute("id") != null && !memberList.isEmpty()) {
                    for (Member member : memberList) {
                      if (member.getId() == (int) request.getAttribute("id")) { %>
                        <option value='<%= member.getId() %>' selected><%= member.getFirstName() %> <%= member.getLastName() %>, adresse: <%= member.getAddress() %>, email: <%= member.getEmail() %>, téléphone: <%= member.getPhoneNumber() %></option>
                      <% }
                    }
                  } else { %>
                    <option value="" default disabled selected>---</option>
                  <% }

                  if (!memberList.isEmpty()) {
                    for (Member member : memberList) { 
                      if (request.getAttribute("id") == null || member.getId() != (int) request.getAttribute("id")) { %>
                        <option value='<%= member.getId() %>'><%= member.getFirstName() %> <%= member.getLastName() %>, adresse: <%= member.getAddress() %>, email: <%= member.getEmail() %>, téléphone: <%= member.getPhoneNumber() %></option>
                      <% }
                    }
                  } %>
                </select>
              </div>
            </div>
            <div class="row center">
              <button class="btn waves-effect waves-light" type="submit">Supprimer le membre</button>
              <button class="btn waves-effect waves-light orange" type="reset">Annuler</button>
            </div>
          </form>
	      </div>
	    </div>	    
      </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
</body>
</html>
