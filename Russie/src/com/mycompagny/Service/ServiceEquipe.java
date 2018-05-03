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
import com.codename1.charts.views.RoundChart;
import com.company.Entites.Equipe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sana
 */
public class ServiceEquipe {
  
    public ArrayList<Equipe> getList2() {
        ArrayList<Equipe> listEquipes = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/AffCliEquipe1");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> Equipes = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" +Equipes.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) Equipes.get("root");

                    for (Map<String, Object> obj : list) {
                        Equipe eq = new Equipe();
                        int IDEquipe = (int) Float.parseFloat(obj.get("idequipe").toString());
                       int NB=(int) Float.parseFloat(obj.get("nombrepoints").toString());

                        
                        eq.setIDEquipe((int) IDEquipe);
                        eq.setNomEquipe(obj.get("nomequipe").toString());
                        eq.setEntraineur(obj.get("entraineur").toString());
                        eq.setDrapeau(obj.get("drapeau").toString());
                        eq.setGroupe(obj.get("groupe").toString());
                        eq.setNombrePoints((int) NB);
                        listEquipes.add(eq);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEquipes;
    }
    


}


