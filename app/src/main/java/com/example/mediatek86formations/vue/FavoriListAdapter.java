package com.example.mediatek86formations.vue;
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
public class FavoriListAdapter extends BaseAdapter{

    /**
     * propriétés privées
     */
    private ArrayList<Formation> lesFavoris;
    private LayoutInflater inflater;
    private Controle controle;
    private Context context;

    /**
     * Liste Adapter des favoris
     * @param lesFavoris
     * @param context
     */
    public FavoriListAdapter(ArrayList<Formation> lesFavoris, Context context) {
        this.lesFavoris = lesFavoris;
        this.controle = Controle.getInstance(context);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * retourne le nombre de formations
     * @return nombre de favoris
     */
    @Override
    public int getCount() {
        return lesFavoris.size();
    }

    /**
     * retourne l'item à partir de son index
     * @param i position de l'item
     * @return valeur à cette position
     */
    @Override
    public Object getItem(int i) {
        return lesFavoris.get(i);
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
     * Construction de la ligne
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        FavoriListAdapter.ViewProperties viewProperties;
        if(view == null){
            viewProperties = new FavoriListAdapter.ViewProperties();
            view = inflater.inflate(R.layout.layout_liste_favoris, null);
            viewProperties.txtListeTitle = (TextView)view.findViewById(R.id.txtListTitle);
            viewProperties.txtListPublishedAt = (TextView)view.findViewById(R.id.txtListPublishedAt);
            viewProperties.btnListFavori = (ImageButton)view.findViewById(R.id.btnListFavori);
            view.setTag(viewProperties) ;
        }else{
            viewProperties = (FavoriListAdapter.ViewProperties)view.getTag();
        }
        viewProperties.txtListeTitle.setText(lesFavoris.get(i).getTitle());
        viewProperties.txtListPublishedAt.setText(lesFavoris.get(i).getPublishedAtToString());
        viewProperties.txtListeTitle.setTag(i);
        viewProperties.txtListeTitle.setOnClickListener(this::ouvrirUneFormationActivity);
        viewProperties.txtListPublishedAt.setTag(i);
        viewProperties.txtListPublishedAt.setOnClickListener(this::ouvrirUneFormationActivity);
        viewProperties.btnListFavori.setTag(i);
        viewProperties.btnListFavori.setOnClickListener(v -> {
            supprFavori(v);
            notifyDataSetChanged();
        });
        return view;
    }

    /**
     * Ouvre la page du détail du favori
     * @param v
     */
    private void ouvrirUneFormationActivity(View v){
        int indice = (int)v.getTag();
        controle.setFormation(lesFavoris.get(indice));
        Intent intent = new Intent(context, UneFormationActivity.class);
        context.startActivity(intent);
    }

    /**
     * Supprime une formation des favoris
     * @param v
     */
    private void supprFavori(View v) {
        int indice = (int) v.getTag();
        controle.supprFormation(lesFavoris.get(indice));
        lesFavoris.remove(lesFavoris.get(indice));
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
