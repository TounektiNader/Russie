/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.util.StringUtil;
import com.company.Entites.Cafes;
import com.company.Entites.Equipe;
import com.company.Entites.Favoris;
import com.company.Entites.Joueurs;
import com.company.Entites.User;
import com.company.utils.Local;
import com.mycompagny.Service.ServiceCafe;
import com.mycompagny.Service.ServiceEquipe;
import com.mycompagny.Service.ServiceFavoris;
import com.mycompagny.Service.ServiceJoueur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amouri Aziz
 */
public class AffichageTop5 extends SideMenuBaseForm
{
    Form f;
    String url2="/img";
    String url = "http://localhost/russia/web/public/images/";
    EncodedImage enc;
    int id;
      Image profilePic ;

    public AffichageTop5(Resources res)  
    {
        
        f = new Form("Liste des Equipe", BoxLayout.y());
        
        Toolbar tb = f.getToolbar();
        tb.setTitleCentered(false);
        
            
            
          if(WalkthruForm.capturedImage==null){
           profilePic =  res.getImage("user.png");     }
     
     else{  profilePic = WalkthruForm.capturedImage;}
          
          
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Label tit = new Label("Liste des Favoris", "Title");
         tit.getAllStyles().setFgColor(0xfc2a30);
        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                
                                tit
                        )
                ),
                GridLayout.encloseIn(2)
        );

        tb.setTitleComponent(titleCmp);
        

        Label Liste0 = new Label(" ");

        Container listCon = BoxLayout.encloseY(
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                
                        )
                ),
                GridLayout.encloseIn(2)
        );
        add(listCon);

//add(gov);
//gov.getAllStyles().setMargin(LEFT, 0);
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);

        System.out.println("test");
        getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
        getUnselectedStyle().setBackgroundGradientEndColor(0xFFFFFF);
        getUnselectedStyle().setBackgroundGradientStartColor(0xFFFFFF);
        setupSideMenu(res);
             try {
                    enc = EncodedImage.create("/giphy.gif");
                                    } catch (IOException ex) 
                                    {
                                    }
        

        ServiceFavoris sf = new ServiceFavoris();
      //  ServiceJoueur js = new ServiceJoueur();
        
        //ArrayList<Favoris> fav = sf.getFavoris();
        Favoris fav1 = sf.getFavoris1();
        Favoris fav2 = sf.getFavoris2();
        Favoris fav3 = sf.getFavoris3();
        Favoris fav4 = sf.getFavoris4();
        Favoris fav5 = sf.getFavoris5();
      
        System.err.println(fav1.toString());
        
        ArrayList<Favoris> favTop5 = new ArrayList<>();
        favTop5.add(fav1);
        favTop5.add(fav2);
        favTop5.add(fav3);
        favTop5.add(fav4);
        favTop5.add(fav5);
       
//        fav5.add(fav.get(1));
//        fav5.add(fav.get(2));
//        fav5.add(fav.get(3));
//        fav5.add(fav.get(4));
      //  ArrayList<Joueurs> j = js.getjou(id);
        
        Container ctnlistProduct = new Container(new BoxLayout(BoxLayout.Y_AXIS));
      
        for (Favoris favor : favTop5) {
     //-------------------------------------------------------------------------------------------------------------              
   
                        Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
                        Label label = new Label();
                                                label.getStyle().setBorder(Border.createLineBorder(1, 1));

                        System.out.println(favor.getEquipe().getDrapeau());
                        int deviceWidth = Display.getInstance().getDisplayWidth() / 4;
                                Image placeholder = Image.createImage(deviceWidth, deviceWidth); //square image set to 10% of screen width
                                EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                                label.setIcon(URLImage.createToStorage(encImage,
                                        "Large_" + url2 +favor.getEquipe().getDrapeau()+
                                                "", url +favor.getEquipe().getDrapeau()+
                                                        "", URLImage.RESIZE_SCALE_TO_FILL));
                        c.add(label);
                        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        cnt.add("Equipe: "+favor.getEquipe().getNomEquipe());
                        cnt.add("Groupe: "+favor.getEquipe().getGroupe());
                        c.add(cnt);
                        Container cc = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        cc.getStyle().setBorder(Border.createLineBorder(1, 1));
                        cc.add(c);
                        ctnlistProduct.add(cc);

        }
        f.add(ctnlistProduct);
    }
    public static Image getImageFromTheme(String name) {
        try {
            Resources resFile = Resources.openLayered("/theme");
            Image image = resFile.getImage(name);
            return image;
        } catch (IOException ioe) {
            System.out.println("Image " + name + " not found: " + ioe);
        }
        return null;
    }
    
    

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    @Override
    protected void showOtherForm(Resources res) {
        new AffichageEquipeJoueur(res);
    }
}
    
    
  
