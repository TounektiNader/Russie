/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.utils;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.company.Entites.User;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Nader
 */
public class Local {
       Database db;
       
     
    public void insert(User u ) throws IOException{
   
      delete();
       
     try {
           db = Database.openOrCreate("Russia");
           db.execute("create table if not exists user (id INTEGER PRIMARY KEY, nom TEXT, prenom TEXT,username TEXT,jeton INTEGER,mail TEXT,nationalite TEXT,password TEXT,num TEXT,code TEXT);");

         System.out.println(u.toString());
         db.execute("insert into user(id,nom,prenom,username,jeton,nationalite,mail,password,num,code)  values ('"+u.getId()+"','"+u.getNom()+"','"+u.getPrenom()+"','"+u.getUsername()+"','"+u.getJeton()+"','"+u.getNationalite()+"','"+u.getEmail()+"','"+u.getPassword()+"','"+u.getNum()+"','"+u.getCode()+"' );");

              System.out.println("OK");
                
                } catch (IOException ex) {
                       System.out.println("NOK");
                }
    }
    
    public User getUser() {
       User u  = new User();
        try {
             
               try {
                   db = Database.openOrCreate("Russia");
               } catch (IOException ex) {
                 
               }
               Cursor cur= db.executeQuery("select * from user ");
               
               int columns = cur.getColumnCount();
               
               if(columns > 0) {
                   boolean next = cur.next();
                   if(next) {
                       ArrayList<String[]> data = new ArrayList<>();
                       String[] columnNames = new String[columns];
                       for(int iter = 0 ; iter < columns ; iter++) {
                           columnNames[iter] = cur.getColumnName(iter);
                       }
                       while(next) {
                           Row currentRow = cur.getRow();
                           String[] currentRowArray = new String[columns];
                           for(int iter = 0 ; iter < columns ; iter++) {
                               currentRowArray[iter] = currentRow.getString(iter);
                           }
                           data.add(currentRowArray);
                           next = cur.next();
                           
                           u.setId(Integer.parseInt(data.get(0)[0]));
                           
                           u.setNom(data.get(0)[1]);
                           u.setPrenom(data.get(0)[2]);
                           u.setUsername(data.get(0)[3]);
                           u.setJeton(Integer.parseInt(data.get(0)[4]));
                           u.setEmail(data.get(0)[5]);
                           u.setNationalite(data.get(0)[6]);
                           u.setPassword(data.get(0)[7]);
                            u.setNum(data.get(0)[8]);
                             u.setCode(data.get(0)[9]);
                           
                           
                       }
                       
                   }
                   
               }
              
               
           } catch (IOException ex) {
              
           }
     return u ;
    }
    
  
      public void update(User u ) throws IOException{
   
     
       
     try {
           db = Database.openOrCreate("Russia");

         System.out.println(u.toString());
         db.execute("update user SET nom='"+u.getNom()+"',prenom='"+u.getPrenom()+"',username='"+u.getUsername()+"',nationalite='"+u.getNationalite()+"',mail='"+u.getEmail()+"',num='"+u.getNum()+"' where id='"+u.getId()+"'");

              System.out.println("OK");
                
                } catch (IOException ex) {
                       System.out.println("NOK");
                }
    }
    
    
    
    public void delete(){
    
      try {
           db = Database.openOrCreate("Russia");
           db.execute("Drop table user ");

    } catch (IOException ex) {
                       System.out.println("NOK");
                }
}
    
    public void updatejeton(int solde, int id)
    {
        
    try {
           db = Database.openOrCreate("Russia");
           db.execute("update user SET jeton='"+solde+"' where id='"+id+"'" );

    } catch (IOException ex) {
                       System.out.println("NOK");
                }
    }
    
    public void updatecode(String  code, int id)
    {
        
    try {
           db = Database.openOrCreate("Russia");
           db.execute("update user SET jeton='"+code+"' where id='"+id+"'" );

    } catch (IOException ex) {
                       System.out.println("NOK");
                }
    }

}
