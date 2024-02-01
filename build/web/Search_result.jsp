<%-- 
    Document   : Liste_Voyage
    Created on : 19 déc. 2023, 12:07:13
    Author     : razafinjatovo
--%>

<%@page import="modele.Produits"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Produits> liste = (List<Produits>)request.getAttribute("liste");
    String recherche = (String)request.getAttribute("search");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">

    <title>Liste</title>

    <!-- Bootstrap core CSS -->
    <link href="dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="jumbotron-narrow.css" rel="stylesheet">

    <script src="assets/js/ie-emulation-modes-warning.js"></script>

  </head>

  <body>

    <div class="container">
      <div class="header clearfix">
        <p></p>
        <p></p>
        <nav>
          <ul class="nav nav-pills pull-right">
            <li role="presentation" class="active"><a href="Produits_Servlet">Home</a></li>
          </ul>
        </nav>
        <h3 class="text-muted">Project Recherche</h3>
      </div>
      <hr>
      <div class="card">
        <div class="card_header">
            <h1>Resultat de recherche de produits pour : <%=recherche%></h1>
        </div>
        <p></p>
        <div class="card_body">
            <div class="row marketing">
                
                    <div class="table-responsive">
                        <table class="table table-striped">
                          <thead>
                            <tr>
                              <th>#</th>
                              <th>Nom</th>
                              <th>Categories</th>
                              <th>Quanlite</th>
                              <th>Prix</th>
                              <th>Rapport</th>
                            </tr>
                          </thead>
                          <tbody>
                            <%for(Produits p : liste){%>
                            <tr>
                              <td><%=p.getId()%></td>
                              <td><%=p.getNom()%></td>
                              <td><%=p.getCategorie()%></td>
                              <td><%=p.getQualite()%></td>
                              <td><%=p.getPrix()%> $</td>
                              <td><%=p.getRapport()%></td>
                            </tr>
                            <%}%>
                        </tbody>
                        </table>
                    </div>
                   
              </div>
        </div>
      </div>
      
      <hr>
      <footer class="footer">
        <p>&copy; 2069</p>
      </footer>

    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
