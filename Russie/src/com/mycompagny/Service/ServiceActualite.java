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
import com.codename1.ui.Image;
import com.codename1.components.*;
import com.codename1.ui.*;
import com.company.Entites.Actualite;
import com.codename1.ui.events.ActionEvent;
import com.codename1.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.codename1.ui.util.*;
import com.codename1.util.Base64;
import com.company.Entites.User;
import com.company.utils.Local;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 *
 * @author sana
 */
public class ServiceActualite {
    String imageName = "";
    

    public ArrayList<Actualite> getList2() {
        ArrayList<Actualite> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/mobile");
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
                        Actualite task = new Actualite();   
                        float id = Float.parseFloat(obj.get("idactualite").toString());
                        
                        task.setIdactualite((int) id);
                        task.setTitre(obj.get("titre").toString());
                        task.setTexte(obj.get("texte").toString());
                        try {
                              task.setImage(obj.get("nomImage2").toString());
                        } catch (NullPointerException e) {
                       
                            task.setImage(null);    
                        }
                       
                       
                      
                        listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
        
        
        

}
    
    public void setImage(String filePath, ImageViewer iv) {
        try {
            //creation d'image apartir du filepath
            Image i1 = Image.createImage(filePath).scaled(400, 400);
            iv.setImage(i1);
            if (i1 != null) {
                   //FileSystemStorage  
                //trodek tnajm testoki l image en binaire
                ImageIO imgIO = ImageIO.getImageIO();
                //stocker l'inage dans le flux
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                imgIO.save(i1, out, File.separator, 1);
                //recuperer l image du flux dans un tab binaire
                byte[] ba = out.toByteArray();
                //cryptage de l image binaire
                String Imagecode = Base64.encode(ba);
                ConnectionRequest request = new ConnectionRequest();
                request.addResponseListener((NetworkEvent evt) -> {
                    byte[] data = (byte[]) evt.getMetaData();
                    imageName = new String(data);
                    System.out.println("metadata " + imageName);
                    iv.getImage().setImageName(imageName);
                });
                request.setPost(true);
                request.setHttpMethod("POST");
                // imagecode sequence binaire de l image coder
                request.addArgument("Image", Imagecode);
                request.setUrl("http://localhost/Upload.php");
                NetworkManager.getInstance().addToQueueAndWait(request);
            } else {
                System.out.println("Unable to upload");
            }
            iv.getParent().revalidate();

        } catch (Exception ex) {

        }

    }

    public void browseImage(ImageViewer im) {
        //open gallery
        Display.getInstance().openGallery((ActionListener) (ActionEvent ev) -> {

            if (ev != null && ev.getSource() != null) {
                String filePath = (String) ev.getSource();
                // retenue de nom d'image
//             int fileNameIndex = filePath.lastIndexOf("/") + 1;
//             String fileName = filePath.substring(fileNameIndex);
                // Do something

                setImage(filePath, im); 
            }
        }, Display.GALLERY_IMAGE);

    }
    
    public void ajouteactu(Actualite ac) {
        Local db = new Local();
        User u = new Local().getUser();
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8000/ajoutActuJ/"+ac.getTitre()+"/"+ac.getTexte()+"/"+u.getId();
                 
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            System.out.println(ac.getTexte());
            System.out.println(ac.getTitre());
            System.out.println(u.getUsername());
            System.err.println(imageName);
            System.out.println("ajout ok");
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

    
