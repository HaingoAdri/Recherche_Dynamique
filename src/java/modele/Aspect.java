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
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author Haingo
 */
public class Aspect {
    public static String requette(int type, String aspect, String parametre, int nbrr){
        String nbr = String.valueOf(nbrr);
        switch(type){
            case 1:
                if(aspect.equals("meilleur rapport qualite prix")){
                    return "select * from produits where rapport > 1 and qualite>6 order by rapport desc";
                }
                break;
            case 2:
                if(aspect.equals("pire rapport qualite prix")){
                    return "select * from produits where rapport < 1";
                }
                break;
            case 3:
                if(aspect.equals("meilleur qualite")){
                    return "select * from produits where qualite>7";
                }
                break;
            case 4:
                if(aspect.equals("meilleur prix")){
                    return "select * from produits order by prix asc limit 10";
                }
                break;
            case 5:
                if(aspect.equals("moins chere")){
                    return "select * from produits order by prix asc limit 10";
                }
                break;
            case 6:
                if(aspect.equals("plus chere")){
                    return "select * from produits order by prix desc limit 10";
                }
                break;
            case 7:
                if(aspect.equals("bonne qualite")){
                    return "select * from produits where qualite>5 and qualite=<7";
                }
                break;
            case 8:
                if(aspect.equals("mauvaise qualite")){
                    return "select *from produits where qualite<=3";
                }
                break;
            case 9:
                if(aspect.equals("qualite moyenne")){
                    return "select *from produits where qualite>3 and qualite<=5";
                }
                break;
            case 10:
                if(aspect.equals(parametre+" qualite moyenne")){
                    return "select *from produits where qualite>3 and qualite<=5 and categorie = '"+parametre+"'";
                }
                break;
            case 11:
                if(aspect.equals(parametre+" mauvaise qualite")){
                    return "select *from produits where qualite<=3 and categorie = '"+parametre+"'";
                }
                break;
            case 12:
                if(aspect.equals(parametre+" bonne qualite")){
                    return "select * from produits where qualite>5 and qualite=<7 and categorie = '"+parametre+"'";
                }
                break;
            case 13:
                if(aspect.equals(parametre+" moins chere")){
                    return "select * from produits where categorie = '"+parametre+"' order by prix asc";
                }
                break;
            case 14:
                if(aspect.equals(parametre+" plus chere")){
                    return "select * from produits where categorie = '"+parametre+"' order by prix desc";
                }
                break;
            case 15:
                if(aspect.equals(nbr+"produits")){
                    return "select * from produits order by rapport desc limit "+nbr;
                }
                break;
            case 16:
                if(aspect.equals("top "+nbr+" produits")){
                    return "select * from produits order by rapport desc limit "+nbr;
                }
                break;
            case 17:
                if(aspect.equals("top "+nbr+" fruits moins chere")){
                    return "select * from produits where categorie = 'Fruits' order by prix asc limit "+nbr;
                }
                break;
            case 18:
                if(aspect.equals(nbr+" fruits moins chere")){
                    return "select * from produits where categorie = 'Fruits' order by prix asc limit "+nbr;
                }
                break;
            case 19:
                if(aspect.equals("top 3 legumes moins chere")){
                    return "select * from produits where categorie = 'Legumes' order by prix asc limit 3";
                }
                break;
            case 20:
                if(aspect.equals(nbr+" legumes moins chere")){
                    return "select * from produits where categorie = 'Legumes' order by prix asc limit "+nbr;
                }
                break;
            case 21:
                if(aspect.equals("3 produits plus chere")){
                    return "select * from produits order by prix desc limit 3";
                }
                break;
            case 22:
                if(aspect.equals("top "+nbr+" produits plus chere")){
                    return "select * from produits order by prix desc limit 3";
                }
                break;
            case 23:
                if(aspect.equals("top "+nbr+" fruits plus chere")){
                    return "select * from produits where categorie = 'Fruits' order by prix desc limit "+nbr;
                }
                break;
            case 24:
                if(aspect.equals("3 fruits plus chere")){
                    return "select * from produits where categorie = 'Fruits' order by prix desc limit "+nbr;
                }
                break;
            case 25:
                if(aspect.equals("top "+nbr+" legumes plus chere")){
                    return "select * from produits where categorie = 'Legumes' order by prix desc limit "+nbr;
                }
                break;
            case 26:
                if(aspect.equals("3 legumes plus chere")){
                    return "select * from produits where categorie = 'Legumes' order by prix desc limit 3";
                }break;
            default:
                return "requette non trouver";
        }
        return "requette non trouver";
    }
    
    public static String toMaj(String mot){
        return mot.toUpperCase().substring(0, 1)+mot.toLowerCase().substring(1);
    }
    
    public static String getRequette(String phrase){
        int nbr = 0;
        int type = -1;
        String[] texte = new String[5];
        texte[0] = "le ";
        texte[1] = "la ";
        texte[2] = " par ";
        texte[3] = " de ";
        texte[4] = " ";
        
        String[] categorie = new String[5];
        categorie[0] = "Produits de boulangerie";
        categorie[1] = "Legumes";
        categorie[2] = "Produits laitiers";
        categorie[3] = "Viandes";
        categorie[4] = "Fruits";
        
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(phrase);

        HashMap<String, Integer> typesRecherche = new HashMap<>();
        typesRecherche.put("meilleur rapport qualite prix", 1);
        typesRecherche.put("pire rapport qualite prix", 2);
        typesRecherche.put("meilleur qualite", 3);
        typesRecherche.put("meilleur prix", 4);
        typesRecherche.put("moins chere", 5);
        typesRecherche.put("plus chere", 6);
        typesRecherche.put("bonne qualite", 7);
        typesRecherche.put("mauvaise qualite", 8);

        while(matcher.find()){
            String chiffre = matcher.group();
            nbr = Integer.parseInt(chiffre);
            typesRecherche.put(nbr+"produits", 15);        
            typesRecherche.put("top "+nbr+" produits", 16);
            typesRecherche.put("top "+nbr+" fruits moins chere", 17);
            typesRecherche.put(nbr+" fruits moins chere", 18);
            typesRecherche.put("top "+nbr+" legumes moins chere", 19);
            typesRecherche.put(nbr+" legumes moins chere", 20);
            typesRecherche.put(nbr+" produits plus chere", 21);        
            typesRecherche.put("top "+nbr+" produits plus chere", 22);
            typesRecherche.put("top "+nbr+" fruits plus chere", 23);
            typesRecherche.put(nbr+" fruits plus chere", 24);
            typesRecherche.put("top "+nbr+" legumes plus chere", 25);
            typesRecherche.put(nbr+"legumes plus chere", 26);
        
        }
        typesRecherche.put("qualite moyenne", 9);
        String first = "";
        for(String j : texte){

            for(String mot : phrase.split(j)){
                if(typesRecherche.containsKey(mot)){
                        type = typesRecherche.get(mot);
                        break;
                }
                else if(j!=mot){
                    String sentences[] = phrase.split(" ");
                    first = toMaj(sentences[0]);
//                    System.out.println(first);
                    typesRecherche.put(first+" qualite moyenne", 10);
                    typesRecherche.put(first+" mauvaise qualite", 11);
                    typesRecherche.put(first+" bonne qualite", 12);
                    typesRecherche.put(first+" moins chere", 13);
                    typesRecherche.put(first+" plus chere", 14);
                     for(Entry e :  typesRecherche.entrySet()){
                        if(e.getKey().equals(first+" "+sentences[1]+" "+sentences[2])){
                            type = typesRecherche.get(first+" "+sentences[1]+" "+sentences[2]);
                            break;
                        }
                    }
                }
            }
        }
        
        String aspect = "";
        String requettes = "";
        if(type==-1){
            return "Type de recherche non trouvé.";
        }
        
        switch(type){
                case 1:
                    aspect = "meilleur rapport qualite prix";
                    break;
                case 2:
                    aspect = "pire rapport qualite prix";
                    break;
                case 3:
                    aspect = "meilleur qualite";
                    break;
                case 4:
                    aspect = "meilleur prix";
                    break;
                case 5:
                    aspect = "moins chere";
                    break;
                case 6:
                    aspect = "plus chere";
                    break;
                case 7:
                    aspect = "bonne qualite";
                    break;
                case 8:
                    aspect = "mauvaise qualite";
                    break;
                case 9:
                    aspect = "qualite moyenne";
                    break;
                case 10:
                    aspect = first+" qualite moyenne";
                    break;
                case 11:
                    aspect = first+" mauvaise qualite";
                    break;
                case 12:
                    aspect = first+" bonne qualite";
                    break;
                case 13:
                    aspect = first+" moins chere";
                    break;
                case 14:
                    aspect = first+" plus chere";
                    break;
                case 15:
                    aspect =nbr+"produits";
                    break;
                case 16:
                    aspect = "top "+nbr+" produits";
                    break;
                case 17:
                    aspect = "top "+nbr+" fruits moins chere", 17;
                    break;
                case 18:
                    aspect = nbr+" fruits moins chere";
                    break;
                case 19:
                    aspect = "top "+nbr+" legumes moins chere";
                    break;
                case 20:
                    aspect = nbr+" legumes moins chere";
                    break;
                case 21:
                    aspect = "3 produits plus chere";
                    break;
                case 22:
                    aspect = "top "+nbr+" produits plus chere";
                    break;
                case 23:
                    aspect ="top "+nbr+" fruits plus chere";
                    break;
                case 24:
                    aspect = "3 fruits plus chere";
                    break;
                case 25:
                    aspect = "top "+nbr+" legumes plus chere";
                    break;
                case 26:
                    aspect = "3 legumes plus chere";
                    break;
                // Ajouter d'autres cas pour d'autres types ici au besoin
                default:
                    return "Type de recherche non pris en charge.";
            }
            requettes = requette(type, aspect,first,nbr);
        System.out.println(phrase);
        return requettes;
    }
}
