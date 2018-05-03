/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;

import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.company.Entites.Bet;
import com.company.Entites.Partie;
import com.codename1.io.NetworkEvent;
import com.company.Entites.Equipe;
import com.company.Entites.Resultat;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 *
 * @author sana
 */
public class ServiceResultat {
  public int idequipe=0;
      public  String s="";
    public ArrayList<Resultat> getList2() {
        ArrayList<Resultat> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/resultatJ");
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
                        Resultat resultat = new Resultat();
                  float id = Float.parseFloat(obj.get("idresultat").toString());
                  float butHome = Float.parseFloat(obj.get("buthome").toString());
                  float butAway = Float.parseFloat(obj.get("butaway").toString());
                       resultat.setIdResultat((int)id);
                        resultat.setButHome((int)butHome);
                        resultat.setButAway((int)butAway);
                 
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
                        
                        
                        
                        
                         Map<String, Object> datePartie = (Map<String, Object>) listpartie.get("datepartie");
                         System.out.println(datePartie.toString());
                         Map<String,Object> list3=(Map<String,Object>) datePartie.get("timezone");
                         
                        String num = datePartie.get("timestamp").toString();
                         System.out.println("//////"+num+"//////");
                         int index=num.indexOf("E");
                         System.out.println(index+"index************");
                        String debut=num.substring(0,1);
                         System.out.println("//////"+debut+"//////");
                        String fin=num.substring(2,index);
                         System.out.println("//////"+fin+"//////");
                         int nb = 10-(index-2);
                         String date1="";
                         System.out.println(nb);
                         if(nb==3){
                          date1=debut+fin+"00";
                         }
                         else if (nb==4){ date1=debut+fin+"000";}
                          else if(nb==6){ date1=debut+fin+"00000";}
                       
                        System.out.println("//////"+date1+"//////");
                        float date2=Float.parseFloat(date1);
                        
                        long unix_seconds = (long)date2;
   //convert seconds to milliseconds
   Date date = new Date( unix_seconds * 1000L); 
   // format of the date
                        SimpleDateFormat jdf = new SimpleDateFormat("dd/MM/yyyy");
   
   String java_date = jdf.format(date);
   
   System.out.println("\n"+java_date+"\n");
                        partie.setDatematch(java_date);
                       resultat.setPartie(partie);
                        listTasks.add(resultat);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    
    
    
    
    public int getEquipe(int idpartie) {
       
        ArrayList<Equipe> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/EquipeGagne/"+idpartie);
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
                
                        Equipe equipe = new Equipe();
                         // Map<String, Object> listequipe = (Map<String, Object>) tasks.get("idequipe");
                        float id = Float.parseFloat(tasks.get("idequipe").toString());
                                            System.out.println(id);

                        idequipe = (int) id;

                
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return idequipe;
    }

   
        
   public String code(){
   ArrayList<Equipe> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/numbr");
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
                
                        Equipe equipe = new Equipe();
                         // Map<String, Object> listequipe = (Map<String, Object>) tasks.get("idequipe");
                        s = (tasks.get("groupe").toString());
                                            System.out.println(s);

                       

                
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
       
       return s ;           
   }   
   
     public void envoieSMS(String num , String code) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8000/envoieSMS/" + num + "/" + code;
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

}
