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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.company.Entites.Cadeau;
import com.company.Entites.User;
import com.company.utils.Local;
/**
 *
 * @author 21650
 */
public class CadeauService {
   public ArrayList<Cadeau> getList2() {
        ArrayList<Cadeau> listCadeau = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/all");
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
                        Cadeau cadeau = new Cadeau();
                        float id = Float.parseFloat(obj.get("idcadeau").toString());
                         float jeton = Float.parseFloat(obj.get("jeton").toString());
                        cadeau.setIdCadeau((int) id);
                        cadeau.setCategorie(obj.get("categorie").toString());
                        cadeau.setJeton((int) jeton);
                        cadeau.setType(obj.get("type").toString());
                        cadeau.setImg(obj.get("image").toString());
                        listCadeau.add(cadeau);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCadeau;
    } 
   public void ajoutTask(Cadeau ta) {
        ConnectionRequest con = new ConnectionRequest();
        Local c=new Local();
        User x= new User();
        x=c.getUser();
        System.out.println(x.getId()+"    "+ta.getIdCadeau());
        String Url = "http://127.0.0.1:8000/addmobile/" + ta.getIdCadeau() + "/" +x.getId() ;
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
