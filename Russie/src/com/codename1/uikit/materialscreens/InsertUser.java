/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import static com.codename1.uikit.materialscreens.UpdateUser.reponse;
import com.company.Entites.User;
import com.mycompagny.Service.Authentification;
import com.mycompagny.Service.ServiceResultat;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 *
 * @author Nader
 */
public class InsertUser extends Form {
    
    public InsertUser(Resources res) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
         Label tit = new Label("Register ", "Title");
           Container titleCmp = BoxLayout.encloseY(
              
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                
                                tit
                        )
                ),
                GridLayout.encloseIn(2)
        );
        tb.setTitleComponent(titleCmp);
getAllStyles().setBgImage(res.getImage("rrussia2018.jpg"));
//getAllStyles().setBgImage(res.getImage("russia2018.jpg"));
User u = new User();
            addButtonBottom(u, 0xd997f1, true,res);

    }
    
    
    
     private void addButtonBottom(User u, int color, boolean first,Resources res) {
        Container c = new Container(BoxLayout.y());
        TextField login = new TextField(u.getUsername(), "Username", 15, TextField.EMAILADDR);
        login.getAllStyles().setFgColor(0xF69602);
         TextField password = new TextField("", "Password", 15, TextField.PASSWORD);
        password.getAllStyles().setFgColor(0xF69602);
        //  TextField password = new TextField("", "Password", 15, TextField.PASSWORD) ;
        TextField nom = new TextField(u.getNom(), "Nom", 15, TextField.EMAILADDR);
        nom.getAllStyles().setFgColor(0xF69602);
        TextField prenom = new TextField(u.getPrenom(), "Prenom", 15, TextField.EMAILADDR);
        prenom.getAllStyles().setFgColor(0xF69602);
        TextField mail = new TextField(u.getEmail(), "Mail", 15, TextField.EMAILADDR);
        mail.getAllStyles().setFgColor(0xF69602);
        TextField num = new TextField(u.getNum(), "+216XXXXXXXX", 15, TextField.PHONENUMBER);
        num.getAllStyles().setFgColor(0xF69602);
        ComboBox natio = new ComboBox("Tunisien", "Belge", "Français", "Marocain", "Islandais");

        natio.getAllStyles().setBgColor(0xFFFFFF);
        natio.setHeight(10);
        natio.setWidth(10);
        login.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        nom.getAllStyles().setMargin(LEFT, 0);
        prenom.getAllStyles().setMargin(LEFT, 0);
        mail.getAllStyles().setMargin(LEFT, 0);
        num.getAllStyles().setMargin(LEFT, 0);
        natio.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label loginPass = new Label("", "TextField");
        Label nomIcon = new Label("", "TextField");
        Label prenomIcon = new Label("", "TextField");
        Label mailIcon = new Label("", "TextField");
        Label numIcon = new Label("", "TextField");
        Label natioanliteIcon = new Label("", "TextField");

        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        loginPass.getAllStyles().setMargin(RIGHT, 0);
        nomIcon.getAllStyles().setMargin(RIGHT, 0);
        prenomIcon.getAllStyles().setMargin(RIGHT, 0);
        mailIcon.getAllStyles().setMargin(RIGHT, 0);
        numIcon.getAllStyles().setMargin(RIGHT, 0);
        natioanliteIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 5);
        FontImage.setMaterialIcon(loginPass, FontImage.MATERIAL_LOCK_OUTLINE, 5);
        FontImage.setMaterialIcon(nomIcon, FontImage.MATERIAL_PERSON_OUTLINE, 5);
        FontImage.setMaterialIcon(prenomIcon, FontImage.MATERIAL_PERSON_OUTLINE, 5);
        FontImage.setMaterialIcon(mailIcon, FontImage.MATERIAL_MAIL, 5);
        FontImage.setMaterialIcon(numIcon, FontImage.MATERIAL_PHONE, 5);
        FontImage.setMaterialIcon(natioanliteIcon, FontImage.MATERIAL_NATURE, 5);

        Button loginButton = new Button("Créer");

          loginButton.getAllStyles().setFgColor(0xFFFFFF);
     
        loginButton.getUnselectedStyle().setBorder(
        RoundBorder.create().rectangle(true)
               .color(0x183152)
);

        loginButton.addActionListener(e -> {
            /**
             * ************** page detail ************
             */
            u.setEmail(mail.getText());
            u.setEmail_canonical(mail.getText());
            u.setUsername(login.getText());
            u.setUsername_canonical(login.getText());
            u.setNom(nom.getText());
            u.setPrenom(prenom.getText());
            u.setNum(num.getText());
            u.setNationalite(natio.getSelectedItem().toString());
            u.setPassword(password.getText());
 Authentification a = new Authentification();
 ServiceResultat rs = new ServiceResultat();
                        
String z=rs.code();
                            System.out.println(z);
 a.ajoutBet(u,z);
             new CodeConfirmation(res).show();
        });
        Container by = BoxLayout.encloseY(
                BorderLayout.center(login).add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).add(BorderLayout.WEST, loginPass),
                BorderLayout.center(nom).add(BorderLayout.WEST, nomIcon),
                BorderLayout.center(prenom).add(BorderLayout.WEST, prenomIcon),
                BorderLayout.center(mail).add(BorderLayout.WEST, mailIcon),
                BorderLayout.center(num).add(BorderLayout.WEST, numIcon),
                BorderLayout.center(natio).add(BorderLayout.WEST, natioanliteIcon),
                loginButton
        );
        by.setWidth(1000);
         by.setHeight(1000);
     
     //   by.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
      //  by.getUnselectedStyle().setBackgroundGradientEndColor(0xE1E6FA);
       // by.getUnselectedStyle().setBackgroundGradientStartColor(0xeae4e4);
       
      // by.getStyle().setBgColor(0x99CCCC);
by.getStyle().setBgTransparency(50);
        add(BorderLayout.CENTER, by);

        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);

    }

    
    
}

