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
import com.company.Entites.User;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Odil
 */
public class Authentification {
    
    public static Authentification instance;
    public static User user;
    
    
    public Authentification()
    {
    user=null;
    }
    
    
    public static Authentification getSession()
    {if(instance==null)
    instance=new Authentification();
    return instance;
    }
           
    public User login(int id){
     try {
                ConnectionRequest request = new ConnectionRequest("http://localhost/mobile/russia/getuser.php?id="+id);
                
                NetworkManager.getInstance().addToQueueAndWait(request);
                Map<String,Object> result= new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
                
                Map<String,String> userjson=(Map<String,String>) result.get("user");
                user=new User(Integer.parseInt(userjson.get("id")), userjson.get("username"), userjson.get("email"),userjson.get("nom"),userjson.get("prenom") ,Integer.parseInt(userjson.get("jeton")),userjson.get("nationalite"),userjson.get("password"),userjson.get("num"),Integer.parseInt(userjson.get("enabled")),userjson.get("confirmation_token"));
                //System.out.println(user);
                
            } catch (IOException ex) {
                //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } 
     return user ; 
    }
    
    
    
     public void ajoutBet(User u,String conf) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8001/adduser/" + u.getNom() + "/" + u.getPrenom()+"/"+u.getUsername()+ "/" +u.getUsername_canonical() +"/" +u.getEmail()+ "/" +u.getEmail_canonical()+ "/" +u.getPassword()+ "/" +conf+ "/" +u.getNum()+ "/" +u.getNationalite();
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
    
     
     
     
     public void updateUser(User u) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8001/valideU/" + u.getId() ;
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
    
     
 
       
    public boolean connected(){
        return user!=null;
    }
    
    public void logout(){
        user=null;
    }
    
    public User getUser(){
        if (connected()) return user;
        return null;
    }
}
