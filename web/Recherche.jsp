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
            <h1>Faire recherche :</h1>
        </div>
        <p></p>
        <div class="card_body">
            <div class="row marketing">
                <form action="Produits_Servlet" method="post">
                    <div class="row">
                    <div class="input-group ">
                        <span class="input-group-addon" id="sizing-addon2">Recherche :</span>
                        <input type="text" class="form-control" placeholder="Faire votre recherche" aria-describedby="sizing-addon2" name="recherche">
                    </div>
                    <p></p>
                    <input type="submit" class="btn btn-success" value="Rechercher">
                  </div><!-- /.row -->
                </form>
              </div>
        </div>
      </div>
      <div class="card">
        <div class="card_header">
            <h1>Liste de produits :</h1>
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
    <script>
            function getUrlParameter(name) {
                name = name.replace(/[[]/, "\\[").replace(/[\]]/, "\\]");
                var regex = new RegExp("[\\?&]" + name + "=([^&#]*)");
                var results = regex.exec(location.search);
                return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
            }

            var errorParam = getUrlParameter("Error");
            if (errorParam === "true") {
                window.alert("Type de recherche pas prise en compte");
            }
            
</script>
  </body>
</html>
