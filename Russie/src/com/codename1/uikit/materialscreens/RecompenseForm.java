/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;

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
import com.codename1.ui.TextField;
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
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.company.Entites.Cadeau;
import com.company.Entites.Promo;
import com.company.Entites.Recompense;
import com.company.Entites.User;
import com.company.utils.Local;
import com.mycompagny.Service.CadeauService;
import com.mycompagny.Service.PromoService;
import com.mycompagny.Service.RecompenseService;
import java.io.IOException;

import java.util.ArrayList;


/**
 *
 * @author sana
 */
public class RecompenseForm extends SideMenuBaseForm {
    Form hi ;
     
     private Image imgHome;
     private Image imgAway;
    private EncodedImage enc ;
    public String cp;
    
    public RecompenseForm(Resources res) {
        super(BoxLayout.y());
        

        Button bt= new Button();
        bt.getAllStyles().setFgColor(0xfc2a30);
        
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("user-picture.png");

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Label tit = new Label("Recompenses", "Title");
        // tit.getAllStyles().setFgColor(0xE12336);
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
        // add(listCon);

//add(gov);
// gov.getAllStyles().setMargin(LEFT, 0);
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);

        System.out.println("test");
        getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
        getUnselectedStyle().setBackgroundGradientEndColor(0xFFFFFF);
        getUnselectedStyle().setBackgroundGradientStartColor(0xFFFFFF);
       
           
       
        
         RecompenseService serviceTask=new RecompenseService();
        Local r= new Local();
        User t=new User();
        t=r.getUser();
        ArrayList<Recompense> lis=serviceTask.getList2(t.getId());
         PromoService promo=new PromoService();
        ArrayList<Promo> lis2=promo.getList2();
        if(!lis2.isEmpty())
        {
            Container Code = new Container(new BorderLayout());
        
        Container Date = new Container(new BoxLayout(BoxLayout.X_AXIS));
            
               cp=lis2.get(0).getCoupon();
        Label home1 = new Label("Le coupon expire "+lis2.get(0).getExpiration());
         home1.getAllStyles().setFgColor(0xF69602);
        Label away1 = new Label("Le coupon de "+lis2.get(0).getPromotion()+" % est "+lis2.get(0).getCoupon());
         away1.getAllStyles().setFgColor(0xF69602);
       
        Code.add(BorderLayout.CENTER,home1);
        Date.add(away1);
        add(Date);
        add(Code);
        
        }
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(64,64), true);
        for(Recompense items :lis)
        {
            Cadeau item=items.getCadeau();
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
       
       
       
       Container rest = new Container(new BoxLayout(BoxLayout.X_AXIS));
          Label DatePartie = new Label();
          DatePartie.setWidth(40);
          
   
        Label re = new Label(item.getCategorie());
      
          
        rest.add(re);
        
        
      
        
        
  
        C1.add(DatePartie);
        C1.add(rest);

       
             
              Container grid = GridLayout.encloseIn(3, 
               
                      
               CHome,
                C1,                
               CAway);
              

       add(grid);   
       add(containervide);
        grid.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
        grid.getUnselectedStyle().setBackgroundGradientEndColor(0xC4D7ED);
         grid.getUnselectedStyle().setBackgroundGradientStartColor(0xeae4e4); 
        bt.getUnselectedStyle().setBorder(
        RoundBorder.create().rectangle(true)
               .color(0xf1b00a)
);
        bt.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
        bt.getUnselectedStyle().setBackgroundGradientEndColor(0xf1b00a);
        }
          
           
           Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
           Container d = new Container(new BoxLayout(BoxLayout.X_AXIS));
           
           Label label= new Label("Coupon");
           bt.setText("Valide");
           
            
        
           TextField coupon=new TextField();
           
     
           coupon.getAllStyles().setFgColor(0xf1b00a);
           c.add(label);
           c.add(coupon);
           coupon.setText(cp);
           bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
PromoService h=new PromoService();
                    Local l=new Local();
        User x= new User();
        x=l.getUser();
        if(coupon.getText().length()==0)
            Dialog.show("Erreur","Le champ est vide !!!!!","Ok",null);
        
        else
                   {
        h.deleteTask(coupon.getText(),x.getId());
                      coupon.clear();
                Dialog.show("Informations","Felicitations pour la promotion","Ok",null);
                new RecompenseForm(res).show();
                   }

                         }
        });
           d.add(bt);
           add(c);
           add(d);
           
           setupSideMenu(res);
    
    //FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 5);
      
 
        
        
        
    }

   @Override
    protected void showOtherForm(Resources res) {
        new RecompenseForm(res).show();
    }

}
