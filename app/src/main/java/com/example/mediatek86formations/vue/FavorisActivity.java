package com.example.mediatek86formations.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mediatek86formations.*;
import com.example.mediatek86formations.controleur.Controle;
import com.example.mediatek86formations.modele.Formation;

import java.util.ArrayList;
import java.util.Collections;

public class FavorisActivity extends AppCompatActivity{

    /**
     * propriété privée
     */
    private Controle controle;

    /**
     * Création de l'interface graphique
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);
        init();
    }

    /**
     * initialisations
     */
    private void init(){
        controle = Controle.getInstance(this);
        creerListe();
    }

    /**
     * création de la liste adapter
     */
    private void creerListe(){
        ArrayList<Formation> lesFavoris = controle.getLesFavoris();
        if(lesFavoris != null) {
            Collections.sort(lesFavoris, Collections.<Formation>reverseOrder());
            ListView listView = (ListView) findViewById(R.id.lstFormations);
            FavoriListAdapter adapter = new FavoriListAdapter(lesFavoris, FavorisActivity.this);
            listView.setAdapter(adapter);
        }
    }
}
