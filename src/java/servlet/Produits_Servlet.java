/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import connexion.DbConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Aspect;
import modele.Produits;

/**
 *
 * @author Haingo
 */
@WebServlet(name = "Produits_Servlet", urlPatterns = {"/Produits_Servlet"})
public class Produits_Servlet extends HttpServlet {
    DbConnection c = new DbConnection();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        List<Produits> liste = Produits.listeProduits(c.connectToPostgres());
        request.setAttribute("liste", liste);
        RequestDispatcher dispatch = request.getRequestDispatcher("Recherche.jsp");
        dispatch.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Produits_Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String recherche = request.getParameter("recherche");
        String requette = Aspect.getRequette(recherche);
        String[] mot = requette.split(" ");
        if(mot[0].equals("select")){
            try {
                List<Produits> liste = Produits.listeProduitsDynamiser(c.connectToPostgres(), requette);
                request.setAttribute("liste", liste);
                request.setAttribute("search",recherche);
                RequestDispatcher dispatch = request.getRequestDispatcher("Search_result.jsp");
                dispatch.forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(Produits_Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            response.sendRedirect("Produits_Servlet?Error=true");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
