package com.example.mediatek86formations.vue;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mediatek86formations.*;
import com.example.mediatek86formations.controleur.Controle;
import com.example.mediatek86formations.modele.Formation;
import com.example.mediatek86formations.outils.MesOutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class UneFormationActivity extends AppCompatActivity {

    /**
     * propriétés
     */
    private ImageButton btnPicture;

    /**
     * Création de l'interface graphique
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_une_formation);
        init();
    }

    /**
     * Remplissage des objets graphiques
     */
    private void init(){
        Controle controle = Controle.getInstance(this);
        TextView txtPublishedAt = (TextView) findViewById(R.id.txtPublishedAt);
        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) findViewById(R.id.txtDescription);
        btnPicture = (ImageButton) findViewById(R.id.btnPicture);
        Formation formation = controle.getFormation();
        if(formation!=null) {
            txtPublishedAt.setText(formation.getPublishedAtToString());
            txtTitle.setText(formation.getTitle());
            txtDescription.setText(formation.getDescription());
            MesOutils.loadMapPreview(btnPicture, formation.getPicture());
        }
        ecouteBtnPicture();
    }

    /**
     * Procédure événementielle sur le clic du bouton btnPicture
     */
    private void ecouteBtnPicture(){
        btnPicture.setOnClickListener(v -> {
            Activity activity = UneFormationActivity.this;
            Intent intent = new Intent(activity, VideoActivity.class);
            activity.startActivity(intent);
        });
    }

}