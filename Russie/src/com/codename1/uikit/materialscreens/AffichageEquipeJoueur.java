/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

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
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.company.Entites.Equipe;
import com.company.Entites.Favoris;
import com.company.Entites.Joueurs;
import com.company.Entites.User;
import com.company.utils.Local;

import com.mycompagny.Service.ServiceEquipe;
import com.mycompagny.Service.ServiceFavoris;
import com.mycompagny.Service.ServiceJoueur;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Amouri Aziz
 */
public class AffichageEquipeJoueur extends SideMenuBaseForm
{
    Form f;
    String url2="/img";
    String url = "http://localhost/russia/web/public/images/";
    EncodedImage enc;
    int id;
      Image profilePic ;

    public AffichageEquipeJoueur(Resources res)  
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
        Label tit = new Label("Equipes", "Title");
         tit.getAllStyles().setFgColor(0xE12336);
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
        Label Liste = new Label("Pariez");

        Label Liste0 = new Label(" ");
        Liste.getAllStyles().setFgColor(0xE12336);

        Container listCon = BoxLayout.encloseY(
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                Liste
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
           Button favoriss = new Button("Favoris");

        favoriss.addActionListener(new ActionListener() {
      
            

                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                    ServiceFavoris fav = new ServiceFavoris();
                   new AffichageTop5(res).getF().show();
                }
            });    
        

        ServiceEquipe se = new ServiceEquipe();
      //  ServiceJoueur js = new ServiceJoueur();
        
        ArrayList<Equipe> p = se.getList2();
      //  ArrayList<Joueurs> j = js.getjou(id);
        
        Container ctnlistProduct = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        for (Equipe pr : p) {

  
                        Button btn3 = new Button("Voir les joueurs");
                        btn3.setIcon(FontImage.createMaterial(FontImage.MATERIAL_INFO, btn3.getUnselectedStyle()));                    
                        
                    btn3.addActionListener(new ActionListener() 
                        {
                            @Override
            public void actionPerformed(ActionEvent evt) {
           ServiceJoueur js = new ServiceJoueur();
           ArrayList<Joueurs> j = js.getjou(pr.getIDEquipe());
            Form f2;
            f2 = new Form(pr.getNomEquipe(),BoxLayout.y());
            
            Toolbar tb = f2.getToolbar();
            
            tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e->{
            
            f.showBack();
            
            });
            
            
        //    URLImage imgsv = URLImage.createToStorage(enc, url2+pr.getDrapeau(), url+pr.getDrapeau());
               // ImageViewer imgvb = new ImageViewer(imgsv);
                for (Joueurs jr:j)
                {
                     Label name = new Label("Le nom du joueur: "+jr.getNomJoueur());
                     Label prenom=new  Label("Le prenom du joueur: "+jr.getPrenomJoueur());
                     Label position=new Label("La position du joueur: "+jr.getPosition());
                f2.add(name);
                f2.add(prenom);
                f2.add(position);
                f2.show();
                }
             
    }
            
      });
                            Button favoris = new Button("Favoris");

        favoris.addActionListener(new ActionListener() {
      
            

                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                    ServiceFavoris fav = new ServiceFavoris();
                    Local l = new Local();
     User u=new User(); 
    // Joueurs j= new Joueurs();
         Equipe e = new Equipe();

     u=l.getUser();
    e.setIDEquipe(pr.getIDEquipe());
     Favoris f1;
                    f1 = new Favoris(u,e);

    fav.ajoutFavoris(f1);
                       // fav.ajoutFavoris(f1);
                }
            });    
        
     //-------------------------------------------------------------------------------------------------------------              
   
                        Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
                        Label label = new Label();
                                                label.getStyle().setBorder(Border.createLineBorder(1, 1));

                        System.out.println(pr.getDrapeau());
                        int deviceWidth = Display.getInstance().getDisplayWidth() / 4;
                                Image placeholder = Image.createImage(deviceWidth, deviceWidth); //square image set to 10% of screen width
                                EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                                label.setIcon(URLImage.createToStorage(encImage,
                                        "Large_" + url2 +pr.getDrapeau()+
                                                "", url +pr.getDrapeau()+
                                                        "", URLImage.RESIZE_SCALE_TO_FILL));
                        c.add(label);
                       
                        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        cnt.add("Nom de l'equipe: "+pr.getNomEquipe());
                        cnt.add("Nom de lentraineur: "+pr.getEntraineur());
                        cnt.add("Nombre de points: "+pr.getNombrePoints());
                        cnt.add("Groupe: " +pr.getGroupe());
                        cnt.add(btn3);
                        c.add(cnt);
                        Container cnt1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                        
                        cnt1.add(favoris);
                        Container cc = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        cc.getStyle().setBorder(Border.createLineBorder(1, 1));
                        cc.add(c);
                        cc.add(cnt1);
                        ctnlistProduct.add(cc);

        }
        f.add(favoriss);
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
