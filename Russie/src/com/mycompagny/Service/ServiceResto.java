/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.company.Entites.Restos;
import com.company.Entites.Villes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sana
 */
public class ServiceResto {

    public void ajoutTask(Restos ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8000/cafesjson" + ta.getNom() + "/" + ta.getDetails();
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
    

    public ArrayList<Restos> getList2() {
        ArrayList<Restos> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
//        con.setUrl("http://localhost/validationweb/russia/web/app_dev.php/restosjson");
        con.setUrl("http://127.0.0.1:8000/restosjson");
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
                        Restos task = new Restos();   
                        float id = Float.parseFloat(obj.get("idresto").toString());
                        Map<String, Object> etab = (Map<String, Object>) obj.get("idville");
                        task.setId((int) id);
                        task.setNom(obj.get("nomresto").toString());
                        task.setDetails(obj.get("detailsresto").toString());
                        task.setPosition(obj.get("positionresto").toString());
                        task.setPhoto(obj.get("photoresto").toString());
                        task.getRestoVille().setNom(etab.get("nomville").toString());
                        System.out.println(etab.get("nomville").toString());
                        listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        con.setDisposeOnCompletion(dlg);
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    public ArrayList<String> count() {
        ArrayList<String> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();

<<<<<<< HEAD
=======
//        con.setUrl("http://localhost/validationweb/russia/web/app_dev.php/villesjson");
<<<<<<< HEAD
=======
        con.setUrl("http://127.0.0.1:8000/restocount");
<<<<<<< HEAD
=======
<<<<<<< HEAD

=======
>>>>>>> 82a33ede1017194d51d53f14160065b90c53ac0c

        con.setUrl("http://127.0.0.1:8001/restocount");
>>>>>>> 00f33bf6d9123248eda29dabdb2b74090d19a5a1

        con.setUrl("http://127.0.0.1:8000/restocount");

<<<<<<< HEAD
//        con.setUrl("http://127.0.0.1:8001/restocount");
=======
<<<<<<< HEAD
=======


>>>>>>> 822277305a3a1db7e3ba5fd5967e5e0f1d1d9c28
>>>>>>> 82a33ede1017194d51d53f14160065b90c53ac0c
>>>>>>> 1467675d20a2d3972df70cd31d0c27e31052f238
>>>>>>> 00f33bf6d9123248eda29dabdb2b74090d19a5a1

     //   con.setUrl("http://localhost/validationweb/russia/web/app_dev.php/villesjson");
     //   con.setUrl("http://127.0.0.1:8000/restocount");

     //   con.setUrl("http://localhost/validationweb/russia/web/app_dev.php/villesjson");
//        con.setUrl("http://127.0.0.1:8000/restocount");

     //   con.setUrl("http://localhost/validationweb/russia/web/app_dev.php/villesjson");
//        con.setUrl("http://127.0.0.1:8000/restocount");

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
