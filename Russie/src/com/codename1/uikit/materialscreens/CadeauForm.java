/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MediaPlayer;
import com.codename1.components.SpanLabel;
import com.codename1.components.MediaPlayer;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.company.Entites.Cadeau;
import com.company.Entites.User;
import com.company.utils.Local;
import com.mycompagny.Service.CadeauService;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;


/**
 *
 * @author sana
 */
public class CadeauForm extends SideMenuBaseForm {
    Form hi ;
     
     private Image imgHome;
     private Image imgAway;
    private EncodedImage enc ;
    public CadeauForm(Resources res) {
        super(BoxLayout.y());
        
                    
                    
                   
                
             
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("user-picture.png");

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        Label tit = new Label("Cadeaux", "Title");
        
        // tit.getAllStyles().setFgColor(0xE12336);
        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                
                                tit
                        )
                ),
                
                GridLayout.encloseIn(3)
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
        
        // add(listCon);

//add(gov);
// gov.getAllStyles().setMargin(LEFT, 0);
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);

        System.out.println("test");
        getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
        getUnselectedStyle().setBackgroundGradientEndColor(0xFFFFFF);
        getUnselectedStyle().setBackgroundGradientStartColor(0xFFFFFF);
        
       Button bt2 = new Button("Mes Recompenses");
        bt2.getAllStyles().setFgColor(0xfc2a30);
        bt2.addActionListener(e-> new RecompenseForm(res).show());
         add(bt2);  
       
        
         CadeauService serviceTask=new CadeauService();
        ArrayList<Cadeau> lis=serviceTask.getList2();
        
        
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(64,64), true);
        for(Cadeau item :lis)
        {
            Image logo=res.getImage("shopping-cart.png");
            String url="http://localhost/uploads/images/"+item.getImg();
            System.out.println(url);
            Image imgHome = URLImage.createToStorage(placeholder,"uploads/images/"+item.getImg(), url); 
            
            
              
        Container containervide = new Container(BoxLayout.x());
        Label spaceLabel0 = new Label(" ");
        Label spaceLabel2 = new Label(" ");
        containervide.add(spaceLabel0);
        containervide.add(spaceLabel2);

         containervide.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
        containervide.getUnselectedStyle().setBackgroundGradientEndColor(0xFFFFFF);
        containervide.getUnselectedStyle().setBackgroundGradientStartColor(0xFFFFFF);
               
               
               

   

imgHome.fill( 30, 30);
               
      
        

       
               
             
               
               
               
        Container CHome = new Container(new BoxLayout(BoxLayout.X_AXIS));
        CHome.setWidth(20);
        CHome.setHeight(20);
        Container CAway = new Container(new BoxLayout(BoxLayout.X_AXIS));
        CAway.setWidth(20);
        CAway.setHeight(20);      
               
        Label home = new Label(item.getType());
         home.getAllStyles().setFgColor(0xF69602);
        home.setIcon(imgHome.scaledWidth(30));
        Label away = new Label(""+item.getJeton());
         away.getAllStyles().setFgColor(0xF69602);
       
        CHome.add(home);
        CAway.add(away);
        
       Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       
       Button bt = new Button("Acheter");
      

        bt.getAllStyles().setFgColor(0xfc2a30);
     bt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    CadeauService h=new CadeauService();
                    Local l=new Local();
        User x= new User();
        x=l.getUser();
        if(x.getJeton()>=item.getJeton())
        {
         int solde=x.getJeton()-item.getJeton();
            l.updatejeton(solde,x.getId());
             h.ajoutTask(item);
             Dialog.show("Informations","Votre achat est validé", "ok", null);
             
        }
        else
        {
         Dialog.show("Error","Votre solde est insuffisant", "ok", null);
               
        }
                }
            });
        bt.getUnselectedStyle().setBorder(
        RoundBorder.create().rectangle(true)
               .color(0xf1b00a)
);
        bt.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
        bt.getUnselectedStyle().setBackgroundGradientEndColor(0xf1b00a);
        
       
       Container rest = new Container(new BoxLayout(BoxLayout.X_AXIS));
          Label DatePartie = new Label();
          DatePartie.setWidth(40);
          
   
        Label re = new Label(item.getCategorie());
      
          
        rest.add(re);
        
        
      
        
        
  
        C1.add(DatePartie);
        C1.add(rest);
        C1.add(bt);
       
             
              Container grid = GridLayout.encloseIn(3, 
               
                      
               CHome,
                C1,                
               CAway);
              

       add(grid);   
       add(containervide);
        grid.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
        grid.getUnselectedStyle().setBackgroundGradientEndColor(0xC4D7ED);
         grid.getUnselectedStyle().setBackgroundGradientStartColor(0xeae4e4); }
        
          
                
    
           setupSideMenu(res);
    
    //FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 5);
      
 
        
        
        
    }

   @Override
    protected void showOtherForm(Resources res) {
        new RecompenseForm(res).show();
    }

}
