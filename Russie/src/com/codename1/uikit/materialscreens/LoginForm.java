/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.materialscreens;

import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import static com.codename1.uikit.materialscreens.Affichage.resultat;
import com.company.Entites.User;
import com.company.utils.Local;


import com.mycompagny.Service.Authentification;
import com.mycompagny.Service.ServiceResultat;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Random;


/**
 * The Login form
 *
 * @author Shai Almog
 */
public class LoginForm extends Form {
 String message="";
    public LoginForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label(" ", "WelcomeWhite"),
                    new Label("", "WelcomeBlue")
        );
        
        getTitleArea().setUIID("Container");
       
      
        
        TextField login = new TextField("", "Login", 15, TextField.EMAILADDR) ;
        TextField password = new TextField("", "Password", 15, TextField.PASSWORD) ;
        login.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 5);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 5);
        
        Button loginButton = new Button("Connexion");
        loginButton.setUIID("LoginButton");
        loginButton.addActionListener(e -> {
               // if(!login.getText().equals("") && !password.getText().equals("")){
             if((login.getText().trim().length() > 0) && (password.getText().trim().length() > 0)){
                try {
                    ConnectionRequest request = new ConnectionRequest("http://localhost/mobile/russia/login.php?username="+login.getText()+"&password="+password.getText());

                    NetworkManager.getInstance().addToQueueAndWait(request);
                    Map<String,Object> result= new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
                  
                      message= new String(request.getResponseData(), "utf-8");
                                if(message.equals("error")){
                                
                                   ToastBar.Status s = ToastBar.getInstance().createStatus();
                            s.setMessage("Vérifier Vos Cordonées");
                          
                            Image i = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"));
                            s.setIcon(i);
                            s.setExpires(5000);
                            s.show();
                                
                                }
                                else{
                                  
                    
                    if(!result.get("id").equals("-1")) 
                    { System.out.println(Integer.parseInt((String)result.get("id")));
                        Authentification a = new Authentification();
                        User u =   a.login(Integer.parseInt((String)result.get("id")));
                    
                      
                        
                     //if(p.verif(password.getText(),u.getPassword()))
                      //{
                        Local dblocal = new Local();
                        dblocal.insert(u);
                        
                          if(u.getEnabled()==0){
                            Dialog.show("ERROR","Vous Devez Confirmer votre Compte ","OK",null);
                              new CodeConfirmation(theme).show();
                        }
                                
                          else{ 
                        
                     Dialog.show("Succès","Bienvenue "+u.getUsername(),"OK",null);
                
                        
            new WalkthruForm(theme).show();}
                       }
                   
           // Toolbar.setGlobalToolbar(true);
                        
                        
                        //open home
                    //  nomE.setText(u.getEmail());
                     // motE.setText(u.getUsername());
                      //  f1.show();
                    
                    
                                }
                   
                  } catch (IOException ex) {
                    //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                   
                  }

            }
            
            
                else{
                      ToastBar.Status s = ToastBar.getInstance().createStatus();
                            s.setMessage("Vous devez Remplir tous les champs");
                          
                            Image i = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"));
                            s.setIcon(i);
                            s.setExpires(5000);
                            s.show();
                
                }
                
         
         
        });
        
        Button createNewAccount = new Button("CREATE NEW ACCOUNT");
        createNewAccount.setUIID("CreateNewAccountButton");
        createNewAccount.addActionListener(e -> {
            /**
             * ************** page detail ************
             */
             
             new InsertUser(theme).show();
            /**
             * **************************************
             */
        });
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        
        
        Container by = BoxLayout.encloseY(
                welcome,
                
                spaceLabel,
                BorderLayout.center(login).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                loginButton,
                createNewAccount
        );
        add(BorderLayout.CENTER, by);
        
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }
    
 
    

}
