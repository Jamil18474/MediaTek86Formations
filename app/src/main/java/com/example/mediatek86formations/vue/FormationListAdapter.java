package com.example.mediatek86formations.vue;
import com.example.mediatek86formations.modele.AccesLocal;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.mediatek86formations.*;
import com.example.mediatek86formations.controleur.Controle;
import com.example.mediatek86formations.modele.AccesLocal;
import com.example.mediatek86formations.modele.Formation;
import com.example.mediatek86formations.outils.MesOutils;

import java.util.ArrayList;

public class FormationListAdapter extends BaseAdapter {

    /**
     * propriétés privées
     */
    private ArrayList<Formation> lesFormations;
    private LayoutInflater inflater;
    private Controle controle;
    private Context context;
    private ArrayList<Integer> lesFavorisId;
    private ArrayList<Formation> lesFavoris;

    /**
     * Liste adapter des formations
     * @param lesFormations
     * @param context
     */
    public FormationListAdapter(ArrayList<Formation> lesFormations, Context context) {
        this.lesFormations = lesFormations;
        this.controle = Controle.getInstance(context);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        lesFavorisId = controle.getLesFavorisId();
        lesFavoris = controle.getLesFavoris();
    }

    /**
     * retourne le nombre de formations
     * @return nombre de formations
     */
    @Override
    public int getCount() {
        return lesFormations.size();
    }

    /**
     * retourne l'item à partir de son index
     * @param i position de l'item
     * @return valeur à cette position
     */
    @Override
    public Object getItem(int i) {
        return lesFormations.get(i);
    }

    /**
     * retourne l'id de l'item à partir de son index
     * @param i position de l'item
     * @return id à cette position
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * Cobstruction de la ligne
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewProperties viewProperties;
        if(view == null){
            viewProperties = new ViewProperties();
            view = inflater.inflate(R.layout.layout_liste_formations, null);
            viewProperties.txtListeTitle = (TextView)view.findViewById(R.id.txtListTitle);
            viewProperties.txtListPublishedAt = (TextView)view.findViewById(R.id.txtListPublishedAt);
            viewProperties.btnListFavori = (ImageButton)view.findViewById(R.id.btnListFavori);
            view.setTag(viewProperties) ;
        }else{
            viewProperties = (ViewProperties)view.getTag();
        }
        viewProperties.txtListeTitle.setText(lesFormations.get(i).getTitle());
        viewProperties.txtListPublishedAt.setText(lesFormations.get(i).getPublishedAtToString());

        if(lesFavorisId.contains(lesFormations.get(i).getId())) {
            viewProperties.btnListFavori.setImageResource(R.drawable.coeur_rouge);
        } else {
            viewProperties.btnListFavori.setImageResource(R.drawable.coeur_gris);
        }
        viewProperties.txtListeTitle.setTag(i);
        viewProperties.txtListeTitle.setOnClickListener(this::ouvrirUneFormationActivity);
        viewProperties.txtListPublishedAt.setTag(i);
        viewProperties.txtListPublishedAt.setOnClickListener(this::ouvrirUneFormationActivity);
        viewProperties.btnListFavori.setTag(i);
        viewProperties.btnListFavori.setOnClickListener(v -> {
            int indice = (int)v.getTag();
            if(lesFavorisId.contains(lesFormations.get(indice).getId())) {
                supprFavori(v);
            } else {
                creerFavori(v);
            }
            lesFavorisId = controle.getLesFavorisId();
            notifyDataSetChanged();
        });
        return view;
    }

    /**
     * Ouvre la page du détail de la formation
     * @param v
     */
    private void ouvrirUneFormationActivity(View v){
        int indice = (int)v.getTag();
        controle.setFormation(lesFormations.get(indice));
        Intent intent = new Intent(context, UneFormationActivity.class);
        context.startActivity(intent);
    }

    /**
     * Ajoute une formation dans les favoris
     * @param v
     */
    private void creerFavori(View v){
        int indice = (int)v.getTag();
        lesFavoris.add(lesFormations.get(indice));
        controle.creerFormation(lesFormations.get(indice));
    }

    /**
     * Supprime une formation des favoris
     * @param v
     */
    private void supprFavori(View v) {
        int indice = (int) v.getTag();
        lesFavoris.remove(lesFormations.get(indice));
        controle.supprFormation(lesFormations.get(indice));
    }
    /**
     * Propriétés de la ligne
     */
    private class ViewProperties{
        ImageButton btnListFavori;
        TextView txtListPublishedAt;
        TextView txtListeTitle;
    }

}
