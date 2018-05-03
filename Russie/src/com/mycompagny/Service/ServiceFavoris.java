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
import com.company.Entites.Equipe;
import com.company.Entites.Favoris;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Amouri Aziz
 */
public class ServiceFavoris {
    
     public void ajoutFavoris(Favoris com) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8000/Fav/"+com.getUser().getId()+ "/" + com.getEquipe().getIDEquipe() ;
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
 public Favoris getFavoris1() {
     Favoris fav = new Favoris();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/AfficheJoueurrrr1");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> Favo = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" +Favo.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) Favo.get("root");
                    
                    for (Map<String, Object> obj : list) {
                        Equipe equipe = new Equipe();
                        Map<String, Object> list2 = (Map<String, Object>) obj.get("idequipe");
                        
                        float IDEquipe =Float.parseFloat(list2.get("idequipe").toString());

                        
                        equipe.setIDEquipe((int)IDEquipe);
                        equipe.setNomEquipe(list2.get("nomequipe").toString());
                        equipe.setDrapeau(list2.get("drapeau").toString());
                        equipe.setGroupe(list2.get("groupe").toString());
                       
                        
fav.setEquipe(equipe);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return fav;
    }
    
 
 public Favoris getFavoris2() {
     Favoris fav = new Favoris();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/AfficheJoueurrrr2");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> Favo = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" +Favo.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) Favo.get("root");
                    
                    for (Map<String, Object> obj : list) {
                        Equipe equipe = new Equipe();
                        Map<String, Object> list2 = (Map<String, Object>) obj.get("idequipe");
                        
                        float IDEquipe =Float.parseFloat(list2.get("idequipe").toString());

                        
                        equipe.setIDEquipe((int)IDEquipe);
                        equipe.setNomEquipe(list2.get("nomequipe").toString());
                        equipe.setDrapeau(list2.get("drapeau").toString());
                        equipe.setGroupe(list2.get("groupe").toString());
                       
                        
fav.setEquipe(equipe);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return fav;
    }
 
 public Favoris getFavoris3() {
     Favoris fav = new Favoris();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/AfficheJoueurrrr3");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> Favo = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" +Favo.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) Favo.get("root");
                    
                    for (Map<String, Object> obj : list) {
                        Equipe equipe = new Equipe();
                        Map<String, Object> list2 = (Map<String, Object>) obj.get("idequipe");
                        
                        float IDEquipe =Float.parseFloat(list2.get("idequipe").toString());

                        
                        equipe.setIDEquipe((int)IDEquipe);
                        equipe.setNomEquipe(list2.get("nomequipe").toString());
                        equipe.setDrapeau(list2.get("drapeau").toString());
                        equipe.setGroupe(list2.get("groupe").toString());
                       
                        
fav.setEquipe(equipe);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return fav;
    }
 
 
 public Favoris getFavoris4() {
     Favoris fav = new Favoris();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/AfficheJoueurrrr4");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> Favo = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" +Favo.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) Favo.get("root");
                    
                    for (Map<String, Object> obj : list) {
                        Equipe equipe = new Equipe();
                        Map<String, Object> list2 = (Map<String, Object>) obj.get("idequipe");
                        
                        float IDEquipe =Float.parseFloat(list2.get("idequipe").toString());

                        
                        equipe.setIDEquipe((int)IDEquipe);
                        equipe.setNomEquipe(list2.get("nomequipe").toString());
                        equipe.setDrapeau(list2.get("drapeau").toString());
                        equipe.setGroupe(list2.get("groupe").toString());
                       
                        
fav.setEquipe(equipe);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return fav;
    }
 
 
 public Favoris getFavoris5() {
     Favoris fav = new Favoris();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/AfficheJoueurrrr5");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> Favo = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("roooooot:" +Favo.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) Favo.get("root");
                    
                    for (Map<String, Object> obj : list) {
                        Equipe equipe = new Equipe();
                        Map<String, Object> list2 = (Map<String, Object>) obj.get("idequipe");
                        
                        float IDEquipe =Float.parseFloat(list2.get("idequipe").toString());

                        
                        equipe.setIDEquipe((int)IDEquipe);
                        equipe.setNomEquipe(list2.get("nomequipe").toString());
                        equipe.setDrapeau(list2.get("drapeau").toString());
                        equipe.setGroupe(list2.get("groupe").toString());
                       
                        
fav.setEquipe(equipe);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return fav;
    }
 
}
