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

public class FormationsActivity extends AppCompatActivity {

    /**
     * propriétés privées
     */
    private Controle controle;
    private Button btnFiltrer;
    private EditText txtFiltre;

    /**
     * Création de l'interface graphique
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formations);
        init();
    }

    /**
     * initialisations
     */
    private void init(){
        controle = Controle.getInstance(this);
        btnFiltrer = (Button) findViewById(R.id.btnFiltrer);
        txtFiltre = (EditText) findViewById(R.id.txtFiltre);
        ecouteBtnFiltrer();
    }

    /**
     * création de la liste adapter
     */
    private void creerListe(ArrayList<Formation> lesFormations){
        if(lesFormations != null) {
            Collections.sort(lesFormations, Collections.<Formation>reverseOrder());
            ListView listView = (ListView) findViewById(R.id.lstFormations);
            FormationListAdapter adapter = new FormationListAdapter(lesFormations, FormationsActivity.this);
            listView.setAdapter(adapter);
        }
    }

    /**
     * Procédure événementielle sur le clic du bouton btnFiltrer
     */
    private void ecouteBtnFiltrer() {
        creerListe(controle.getLesFormations());
        btnFiltrer.setOnClickListener(v -> {
            String filtre = txtFiltre.getText().toString();
            if (filtre.equals("")) {
                creerListe(controle.getLesFormations());
            } else {
                creerListe(controle.getLesFormationFiltre(filtre));
                    txtFiltre.setText("");
            }
        });
    }
}