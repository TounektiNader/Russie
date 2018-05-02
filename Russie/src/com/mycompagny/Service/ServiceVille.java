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
import com.company.Entites.Villes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sana
 */
public class ServiceVille {

    public void ajoutTask(Villes ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8000" + ta.getNom() + "/" + ta.getLogoequipe();
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
    

    public ArrayList<Villes> getList2() {
        ArrayList<Villes> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
<<<<<<< HEAD

//        con.setUrl("http://localhost/validationweb/russia/web/app_dev.php/villesjson");
        con.setUrl("http://127.0.0.1:8001/villesjson");
        con.setUrl("http://localhost/validationweb/russia/web/app_dev.php/villesjson");
 //       con.setUrl("http://127.0.0.1:8000/villesjson");

=======
//        con.setUrl("http://localhost/validationweb/russia/web/app_dev.php/villesjson");
        con.setUrl("http://127.0.0.1:8001/villesjson");
        
        //con.setUrl("http://localhost/validationweb/russia/web/app_dev.php/villesjson");
 //       con.setUrl("http://127.0.0.1:8000/villesjson");
>>>>>>> d573a2c96a1f645188bd5878e8f96e217f24a0b1
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
                        Villes task = new Villes();   
                        float id = Float.parseFloat(obj.get("idville").toString());
                        task.setIdville((int) id);
                        task.setNom(obj.get("nomville").toString());
                        task.setFondation(obj.get("fondationville").toString());
                        task.setPopulation(obj.get("populationville").toString());
                        task.setTimezone(obj.get("timezone").toString());
                        task.setPhotoville(obj.get("photoville").toString());
                        task.setEquipelocale(obj.get("equipelocale").toString());
                        task.setCoordonnees(obj.get("coordonnees").toString());
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
        con.setUrl("http://127.0.0.1:8001/villecount");

   //     con.setUrl("http://localhost/validationweb/russia/web/app_dev.php/villesjson");
<<<<<<< HEAD
        con.setUrl("http://127.0.0.1:8000/villecount");

=======
//        con.setUrl("http://127.0.0.1:8000/villecount");
>>>>>>> d573a2c96a1f645188bd5878e8f96e217f24a0b1
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
