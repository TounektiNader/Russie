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
import com.company.Entites.Bet;
import com.company.Entites.Equipe;
import com.company.Entites.Partie;
import com.company.Entites.Resultat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nader
 */
public class BetService {
      public void ajoutBet(Bet bet) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8001/addbet/" + bet.getValeurr() + "/" + bet.getUser().getId()+"/"+bet.getPartie().getIdMatch();
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
      
      
    public ArrayList<Bet> getList2(int id) {
        ArrayList<Bet> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8001/mesBets/"+ id);
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
                        Bet bet = new Bet();
                  float id = Float.parseFloat(obj.get("idbet").toString());
                  float valeur = Float.parseFloat(obj.get("valeur").toString());
                  
                          bet.setIdBet((int)id);
                          bet.setValeurr((int)valeur);
                          bet.setEtat(obj.get("etat").toString());
                  
                  Map<String, Object> listpartie = (Map<String, Object>) obj.get("idpartie");
                       
                             
                       Partie partie = new Partie();
                       float idpartie = Float.parseFloat(listpartie.get("id").toString());
                       partie.setIdMatch((int)idpartie);
                       //partie.setGroup(listpartie.get("groupe").toString());
                       partie.setHeurePartie(listpartie.get("heurepartie").toString());
                       partie.setTour(listpartie.get("tour").toString());
                       partie.setEtatMatch(listpartie.get("etatmatch").toString());
                      
                       Map<String, Object> Home = (Map<String, Object>) listpartie.get("home");
                        Equipe equipeHome = new Equipe();
                        float idEquipeHome = Float.parseFloat(Home.get("idequipe").toString());
                       equipeHome.setIDEquipe((int)idEquipeHome);
                       equipeHome.setNomEquipe(Home.get("nomequipe").toString());
                       equipeHome.setEntraineur(Home.get("entraineur").toString());
                       equipeHome.setContinent(Home.get("continent").toString());
                    //hethi bech nzidha fazet localhost
                       equipeHome.setDrapeau("http://localhost/java/imagess/"+Home.get("drapeau").toString()); 
                       equipeHome.setGroupe(Home.get("groupe").toString()); 
                       
                       float butMarqueHome = Float.parseFloat(Home.get("butmarque").toString());
                       equipeHome.setButMarque((int)butMarqueHome); 
                      
                       float butEncaisseeHome = Float.parseFloat(Home.get("butencaisse").toString());
                       equipeHome.setButEncaisse((int)butEncaisseeHome); 
                      
                       float matchJoueeHome = Float.parseFloat(Home.get("matchjouee").toString());
                       equipeHome.setMatchJouee((int)matchJoueeHome); 
                      
                       float matchGagneHome = Float.parseFloat(Home.get("matchgagne").toString());
                       equipeHome.setMatchGagne((int)matchGagneHome); 
                      
                       float matchPerdueHome = Float.parseFloat(Home.get("matchperdu").toString());
                       equipeHome.setMatchperdu((int)matchPerdueHome);
                       
                       float nbPointHome = Float.parseFloat(Home.get("nombrepoints").toString());
                       equipeHome.setNombrePoints((int)nbPointHome); 
                      
                        partie.setHome(equipeHome);
                        
                        
                        
                          Map<String, Object> Away = (Map<String, Object>) listpartie.get("away");
                        Equipe equipeAway = new Equipe();
                        float idEquipeAway = Float.parseFloat(Away.get("idequipe").toString());
                       equipeAway.setIDEquipe((int)idEquipeAway);
                       equipeAway.setNomEquipe(Away.get("nomequipe").toString());
                       equipeAway.setEntraineur(Away.get("entraineur").toString());
                       equipeAway.setContinent(Away.get("continent").toString());
                    //hethi bech nzidha fazet localhost
                       equipeAway.setDrapeau("http://localhost/java/imagess/"+Away.get("drapeau").toString()); 
                       equipeAway.setGroupe(Away.get("groupe").toString()); 
                       
                       float butMarqueAway = Float.parseFloat(Away.get("butmarque").toString());
                       equipeAway.setButMarque((int)butMarqueAway); 
                      
                       float butEncaisseeAway = Float.parseFloat(Away.get("butencaisse").toString());
                       equipeAway.setButEncaisse((int)butEncaisseeAway); 
                      
                       float matchJoueeAway = Float.parseFloat(Away.get("matchjouee").toString());
                       equipeAway.setMatchJouee((int)matchJoueeAway); 
                      
                       float matchGagneAway = Float.parseFloat(Away.get("matchgagne").toString());
                       equipeAway.setMatchGagne((int)matchGagneAway); 
                      
                       float matchPerdueAway = Float.parseFloat(Away.get("matchperdu").toString());
                       equipeAway.setMatchperdu((int)matchPerdueAway);
                       
                       float nbPointaway = Float.parseFloat(Away.get("nombrepoints").toString());
                       equipeAway.setNombrePoints((int)nbPointaway); 
                      
                        partie.setAway(equipeAway);
                        
                       
                         
                        
                        
                        bet.setPartie(partie);
                        listTasks.add(bet);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    
    
      
}
