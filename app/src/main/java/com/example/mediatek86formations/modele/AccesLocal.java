package com.example.mediatek86formations.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.Normalizer;
import java.util.ArrayList;
import com.example.mediatek86formations.outils.MesOutils;
import com.example.mediatek86formations.outils.MySQLiteOpenHelper;
import com.example.mediatek86formations.modele.Formation;

import java.sql.PreparedStatement;
import java.util.Date;

public class AccesLocal {

    private String nomBase = "bdMediatekformations.sqlite";
    private Integer versionBase = 1;
    private  MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    /**
     * constructeur : valorise l'accès à la BDD
     * @param context
     */
    public AccesLocal(Context context){
        accesBD = new MySQLiteOpenHelper(context, nomBase, versionBase);
    }

    /**
     * ajout d'une formation dans les favoris dans la BDD
     * @param formation
     */
    public void ajout(Formation formation){
        bd = accesBD.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", formation.getId());
        values.put("published_at", formation.getPublishedAtToString());
        values.put("title", formation.getTitle());
        values.put("description", formation.getDescription());
        values.put("miniature", formation.getMiniature());
        values.put("picture", formation.getPicture());
        values.put("video_id", formation.getVideoId());
        bd.insert("formation", null, values);
        bd.close(); 
    }
    /**
     * suppression d'une formation des favoris dans la BDD
     * @param formation
     */
    public void  suppr(Formation formation){

        bd = accesBD.getWritableDatabase();
         bd.delete("formation", "id=?", new String[]{String.valueOf(formation.getId())});
        bd.close();
    }

    /**
     * retourne les id des favoris enregistrés dans la BDD
     * @return id des favoris
     */
      public ArrayList<Integer> recupFavoris(){
      ArrayList<Integer> lesFormationsFavoris= new ArrayList<>();
      bd = accesBD.getReadableDatabase();
      String req = "select * from formation";
      Cursor curseur = bd.rawQuery(req, null);
      curseur.moveToFirst();
      while(!curseur.isAfterLast()){
          Integer id = curseur.getInt(0);
          Log.d("id", "***************** id = "+id);
          lesFormationsFavoris.add(id);
          curseur.moveToNext();
      }
      curseur.close();
      return lesFormationsFavoris;
    }
}
