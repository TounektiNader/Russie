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
import com.company.Entites.Hotels;
import com.company.Entites.Villes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sana
 */
public class ServiceHotel {

    public void ajoutTask(Hotels ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/validationweb/russia/web/app_dev.php/cafesjson" + ta.getNom() + "/" + ta.getDetails();
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
    

    public ArrayList<Hotels> getList2() {
        ArrayList<Hotels> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/validationweb/russia/web/app_dev.php/hotelsjson");
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
                    for (Map<String, Object> obj : list) {
                        Hotels task = new Hotels();   
                        float id = Float.parseFloat(obj.get("idhotel").toString());
                        task.setId((int) id);
                        task.setNom(obj.get("nomhotel").toString());
                        task.setDetails(obj.get("detailshotel").toString());
                        task.setPosition(obj.get("positionhotel").toString());
                        task.setPhoto(obj.get("photohotel").toString());
//                        Villes v = new Villes();
//                        float id = Float.parseFloat(obj.get("idville").toString());
//                        v.setIdville((int)id);
//                        task.setCafeVille(v);
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
