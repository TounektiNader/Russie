/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.company.Entites.Stades;
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

/**
 *
 * @author sana
 */
public class ServiceStade {

    public void ajoutTask(Stades ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/validationweb/russia/web/app_dev.php/cafesjson" + ta.getNom() + "/" + ta.getCapacite();
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
    

    public ArrayList<Stades> getList2() {
        ArrayList<Stades> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/validationweb/russia/web/app_dev.php/stadesjson");
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
                        Stades task = new Stades();   
                        float id = Float.parseFloat(obj.get("idstade").toString());
                        task.setId((int) id);
                        task.setNom(obj.get("nomstade").toString());
                        task.setCapacite(obj.get("capacitestade").toString());
                        task.setEquipelocale(obj.get("equipestade").toString());
                        task.setFondation(obj.get("fondationstade").toString());
                        task.setPosition(obj.get("positionstade").toString());
                        task.setPhoto(obj.get("photostade").toString());
                        listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

}