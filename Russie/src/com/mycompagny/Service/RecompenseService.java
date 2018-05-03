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
import com.company.Entites.Cadeau;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.company.Entites.Recompense;
/**
 *
 * @author 21650
 */
public class RecompenseService {
    public int nb,nb2;
    public ArrayList<Recompense> getList2(int id) {
        ArrayList<Recompense> listRecompense = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/mobile/" +id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Map<String,Object> list2=(Map<String,Object>)obj.get("idcadeau");
                        Recompense rec = new Recompense();
                        Cadeau cadeau= new Cadeau();
                                               
                         float jeton = Float.parseFloat(list2.get("jeton").toString());
                       
                        cadeau.setCategorie(list2.get("categorie").toString());
                        cadeau.setJeton((int) jeton);
                        cadeau.setType(list2.get("type").toString());
                        cadeau.setImg(list2.get("image").toString());
                        rec.setCadeau(cadeau);
                        System.out.println(rec.toString());
                        listRecompense.add(rec);


                        }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listRecompense;
    }
     public int getTotal(int id) {
       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/mobile/" +id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                 int total = 0;
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Map<String,Object> list2=(Map<String,Object>)obj.get("idcadeau");
                        float jeton = Float.parseFloat(list2.get("jeton").toString());
                        total=total+((int)jeton);
                        System.out.println(jeton);
                        nb=total;
                       


                        }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return nb;
    }
     public int getNombre(int id) {
       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/mobile/" +id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                 
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Map<String,Object> list2=(Map<String,Object>)obj.get("idcadeau");

                        nb2++;
                       


                        }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return nb2;
    }
}
