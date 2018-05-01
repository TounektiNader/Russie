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
import com.company.Entites.Cafes;
import com.company.Entites.Villes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sana
 */
public class ServiceCafe {

    public void ajoutTask(Cafes ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8001/cafesjson" + ta.getNom() + "/" + ta.getDetails();
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
    

    public ArrayList<Cafes> getList2() {
        ArrayList<Cafes> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
//        con.setUrl("http://localhost/validationweb/russia/web/app_dev.php/cafesjson");
        con.setUrl("http://127.0.0.1:8001/cafesjson");
        con.addResponseListener(new ActionListener<NetworkEvent>() 
        {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) 
                    {
                        
                        Cafes task = new Cafes(); 
                        
                        float id = Float.parseFloat(obj.get("idcafe").toString());
                        Map<String, Object> etab = (Map<String, Object>) obj.get("idville");
                        task.setId((int) id);
                        task.setNom(obj.get("nomcafe").toString());
                        task.setDetails(obj.get("detailscafe").toString());
                        task.setPosition(obj.get("positioncafe").toString());
                        task.setPhoto(obj.get("photocafe").toString());
                        task.getCafeVille().setNom(etab.get("nomville").toString());
                        System.out.println(etab.get("nomville").toString());
                        listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    public ArrayList<String> count() {
        ArrayList<String> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
//        con.setUrl("http://localhost/validationweb/russia/web/app_dev.php/villesjson");
        con.setUrl("http://127.0.0.1:8000/cafecount");
        con.addResponseListener(new ActionListener<NetworkEvent>() 
        {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    //System.out.println(tasks);
                    List<Map<String, String>> list = (List<Map<String, String>>) tasks.get("root");
                    for (Map<String, String> obj : list) 
                    {
                        Object i = obj.get("1");
                    listTasks.add((String) i);
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

}
