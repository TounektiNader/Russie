/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.company.Entites.Joueurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Amouri Aziz
 */
public class ServiceJoueur {
     public ArrayList<Joueurs> getjou(int id) {
        ArrayList<Joueurs> listjoueurs = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/AffCliJoueurs1/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> Joueur = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" +Joueur.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) Joueur.get("root");

                    for (Map<String, Object> obj : list) {
                        Joueurs js = new Joueurs();
                        int IDJoueur = (int) Float.parseFloat(obj.get("idjoueur").toString());

                        
                        js.setIdjoueur((int) IDJoueur);
                        js.setNomJoueur(obj.get("nomjoueur").toString());
                        js.setPrenomJoueur(obj.get("prenomjoueur").toString());
                        js.setPosition(obj.get("postion").toString());
                        listjoueurs.add(js);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listjoueurs;
    }
    
}
