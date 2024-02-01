/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Haingo
 */
public class Produits {
    int id;
    String nom;
    String categorie;
    int qualite;
    double prix;
    double rapport;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getQualite() {
        return qualite;
    }

    public void setQualite(int qualite) {
        this.qualite = qualite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getRapport() {
        return rapport;
    }

    public void setRapport(double rapport) {
        this.rapport = rapport;
    }
    
    

    public Produits(int id, String nom, String categorie, int qualite, double prix, double rapport) {
        this.setId(id);
        this.setNom(nom);
        this.setCategorie(categorie);
        this.setQualite(qualite);
        this.setPrix(prix);
        this.setRapport(rapport);
    }
    
    public static List<Produits> listeProduits(Connection c) throws SQLException{
        List<Produits> liste = new ArrayList<>();
        String sql = "select * from produits";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String categories = rs.getString("categorie");
            int qualite = rs.getInt("qualite");
            double prix = rs.getDouble("prix");
            double rapport = rs.getDouble("rapport");
            Produits p = new Produits(id,nom,categories,qualite,prix,rapport);
            liste.add(p);
        }
        return liste;
    }
    
    public static List<Produits> listeProduitsDynamiser(Connection c, String sql) throws SQLException{
        List<Produits> liste = new ArrayList<>();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String categories = rs.getString("categorie");
            int qualite = rs.getInt("qualite");
            double prix = rs.getDouble("prix");
            double rapport = rs.getDouble("rapport");
            Produits p = new Produits(id,nom,categories,qualite,prix,rapport);
            liste.add(p);
        }
        System.out.println(sql);
        return liste;
    }
}
