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
import com.company.Entites.Cadeau;
import com.company.Entites.User;
import com.company.Entites.Promo;
import com.company.utils.Local;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 *
 * @author 21650
 */
public class PromoService {
    public void ajoutTask() {
        ConnectionRequest con = new ConnectionRequest();
       
        String Url = "http://127.0.0.1:8001/promo";
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
    public ArrayList<Promo> getList2() {
        ArrayList<Promo> listPromo = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8001/promo2");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Promo p=new Promo();
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        p.setCoupon(obj.get("coupon").toString());
                        p.setPromotion((int) Float.parseFloat(obj.get("promotion").toString()));
                        Map<String,Object> list2=(Map<String,Object>)obj.get("expiration");
                        System.out.println("list2"+list2.toString()+"////////////");
                        
//                        float id = Float.parseFloat(obj.get("idcadeau").toString());
//                         float jeton = Float.parseFloat(obj.get("jeton").toString());
//                        cadeau.setIdCadeau((int) id);
//                        cadeau.setCategorie(obj.get("categorie").toString());
//                        cadeau.setJeton((int) jeton);
//                        cadeau.setType(obj.get("type").toString());
                        
              
                        String num = list2.get("timestamp").toString();
                         System.out.println("//////"+num+"//////");
                         int index=num.indexOf("E");
                         System.out.println(index+"index************");
                        String debut=num.substring(0,1);
                         System.out.println("//////"+debut+"//////");
                        String fin=num.substring(2,index);
                         System.out.println("//////"+fin+"//////");
                         int nb=10-(index-2);
                         String date1="";
                         if(nb==2){
                         date1=debut+fin+"0";}
                         if(nb==1){date1=debut+fin;}
                        
                        
                        if(!date1.equals(""))
                        {
                        float date2=Float.parseFloat(date1);
                        
                        long unix_seconds = (long)date2;
   //convert seconds to milliseconds
   Date date = new Date( unix_seconds * 1000L);
                        System.out.println("/////////////////////datebouda"+date);
   // format of the date
   SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   
   String java_date = jdf.format(date);
   p.setExpiration(java_date);
   
   System.out.println("\n"+java_date+"\n");
                        listPromo.add(p);
                        }
                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPromo;
    } 
    public void deleteTask(String coupon,int id) {
        ConnectionRequest con = new ConnectionRequest();
        RecompenseService x=new RecompenseService();
       int total=x.getTotal(id);
        String Url = "http://127.0.0.1:8001/deletepromo/"+coupon+"/"+id+"/"+total;
        con.setUrl(Url);
        ArrayList<Promo> tt=getList2();
        Local ttt=new Local();
        System.out.println( ttt.getUser().getJeton()+"///////anciena aaasolde//");
        int solde=ttt.getUser().getJeton()+(int) Math.ceil((total*tt.get(0).getPromotion())/100.0);
        ttt.updatejeton(solde, id);
       
        System.out.println( ttt.getUser().getJeton()+"///////nouveau solde//");
        
        
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
