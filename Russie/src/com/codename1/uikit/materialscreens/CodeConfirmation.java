/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.company.Entites.User;
import com.company.utils.Local;
import com.mycompagny.Service.Authentification;
import com.mycompagny.Service.ServiceResultat;

/**
 *
 * @author Nader
 */
public class CodeConfirmation extends Form  {
        Local dblocal = new Local();
    User u = dblocal.getUser();
     public CodeConfirmation(Resources res) {
       super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
       //setUIID("LoginForm");
       getAllStyles().setBgImage(res.getImage("login-background.jpg"));
      
     
          addButtonBottom(u, 0xd997f1, true,res);
     }
     
     private void addButtonBottom(User u, int color, boolean first,Resources res) {
          
   Label label = new Label("Votre Code Confirmation");
   
         
         TextField nom = new TextField("", "Votre Code ", 15, TextField.EMAILADDR);
       
       nom.getAllStyles().setFgColor(0xF69602);
       nom.getAllStyles().setBgColor(0xEFF0FF);
        nom.getAllStyles().setMargin(LEFT, 0);
         Label nomIcon = new Label("", "TextField");
          nomIcon.getAllStyles().setMargin(RIGHT, 0);
           FontImage.setMaterialIcon(nomIcon, FontImage.MATERIAL_LOCK_OUTLINE, 5);
       Button loginButton = new Button("Confirmer");
 loginButton.addActionListener(e -> {
 
     System.out.println(nom.getText());
     System.out.println(u.getCode());
 
     if(nom.getText().trim().length() > 0){
     if(nom.getText().equals(u.getCode())){
  Dialog.show("Succès","Votre Compte a été Confirmer ","OK",null);
 Authentification a = new Authentification();
 a.updateUser(u);
 new LoginForm(res).show();
 }
 else{
  Dialog.show("ERROR","Vérifier Votre Code ","OK",null);
 }}
     else{
      ToastBar.Status s = ToastBar.getInstance().createStatus();
                            s.setMessage("Vous devez Remplir tous les champs");
                          
                            Image i = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"));
                            s.setIcon(i);
                            s.setExpires(5000);
                            s.show();
     
     }
 });
          loginButton.getAllStyles().setFgColor(0xFFFFFF);
     
        loginButton.getUnselectedStyle().setBorder(
        RoundBorder.create().rectangle(true)
               .color(0x183152)
);
        Container by = BoxLayout.encloseY(
               
          
                 BorderLayout.center(label),
                  BorderLayout.center(nom).add(BorderLayout.WEST, nomIcon),
                loginButton
        );

by.getStyle().setBgTransparency(50);
        add(BorderLayout.CENTER, by);
      
    }

}
