package com.example.mediatek86formations.controleur;

import android.content.Context;

import com.example.mediatek86formations.modele.AccesDistant;
import com.example.mediatek86formations.modele.Formation;
import com.example.mediatek86formations.modele.AccesLocal;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;

public class Controle {

    /**
     * propriétés privées
     */
    private static Controle instance = null ;
    private ArrayList<Formation> lesFormations = new ArrayList<>();
    private static Formation formation = null;
    private static AccesLocal accesLocal;
    private static ArrayList<Formation> lesFavoris = new ArrayList<>();
    private static ArrayList<Integer> lesFavorisId = new ArrayList<>();

    /**
     * constructeur privé
     */
    private Controle(){
        super();
    }

    /**
     * récupération de l'instance unique de Controle
     * @return instance
     */
    public static final Controle getInstance(Context context){
        if(Controle.instance == null){
            Controle.instance = new Controle();
            AccesDistant accesDistant = new AccesDistant();
            accesDistant.envoi("tous", null);
            accesLocal = new AccesLocal(context);
            lesFavorisId = accesLocal.recupFavoris();
        }
        return Controle.instance;
    }

    /**
     * ajout d'une formation dans les favoris
     * @param formation
     */
    public void creerFormation(Formation formation){
        accesLocal.ajout(formation);
    }

    /**
     * suppression d'une formation des favoris
     * @param formation
     */
    public void supprFormation(Formation formation){
        accesLocal.suppr(formation);
    }

    /**
     * getter sur la formation
     * @return formation
     */
    public Formation getFormation() {
        return formation;
    }

    /**
     * setter sur la formation
     * @param formation
     */
    public static void setFormation(Formation formation) {
        Controle.formation = formation;
    }

    /**
     * getter sur les formations
     * @return formations
     */
    public ArrayList<Formation> getLesFormations() {
        return lesFormations;
    }

    /**
     * retourne les favoris
     * @return favoris
     */
    public ArrayList<Formation> getLesFavoris() {
        for(Formation uneFormation: lesFormations) {
            if (lesFavorisId.contains(uneFormation.getId()) && !lesFavoris.contains(uneFormation)) {
                lesFavoris.add(uneFormation);
            }
        }
        return lesFavoris;
    }

    /**
     * retourne les id des formations favorites
     * @return id des favoris
     */
    public static ArrayList<Integer> getLesFavorisId() {
        lesFavorisId = accesLocal.recupFavoris();
        return lesFavorisId;
    }

    /**
     * retourne les formations dont le titre contient le filtre
     * @param filtre
     * @return les formations filtrées
     */
    public ArrayList<Formation> getLesFormationFiltre(String filtre){
        ArrayList<Formation> lesFormationsFiltre = new ArrayList<>();
        for(Formation uneFormation : lesFormations){
            if(uneFormation.getTitle().toUpperCase().contains(filtre.toUpperCase())){
                lesFormationsFiltre.add(uneFormation);
            }
        }
        return lesFormationsFiltre;
    }

    /**
     * setter sur les formations
     * @param lesFormations
     */
    public void setLesFormations(ArrayList<Formation> lesFormations) {
        this.lesFormations = lesFormations;
    }
}
