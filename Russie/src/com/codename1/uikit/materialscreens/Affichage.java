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
import com.codename1.ui.Component;
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
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.company.Entites.Bet;
import com.company.Entites.Resultat;
import com.mycompagny.Service.BetService;
import com.mycompagny.Service.ServiceResultat;
import java.io.IOException;

import java.util.ArrayList;

/**
 *
 * @author sana
 */
public class Affichage extends SideMenuBaseForm {
    Form hi ;
     ServiceResultat sv = new ServiceResultat();
     private Image imgHome;
     private Image imgAway;
    private EncodedImage enc ;
     Image profilePic ;
 public static Resultat resultat = new Resultat();
    public Affichage(Resources res) {
        super(BoxLayout.y());

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
     if(WalkthruForm.capturedImage==null){
           profilePic =  res.getImage("user.png");     }
     
     else{  profilePic = WalkthruForm.capturedImage;}
     /////////////////////searchhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh   

     
     
     
     
     //////////////////////////////////////////////////////////////////////
     
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Label tit = new Label("Resultat Matchs ", "Title");
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
       
           
       
        
         ArrayList<Resultat> lis=sv.getList2();
int i = 0;      
int j = 0;  
           for (Resultat ress : lis){
            
              
        Container containervide = new Container(BoxLayout.x());
        Label spaceLabel0 = new Label(" ");
        Label spaceLabel2 = new Label(" ");
        containervide.add(spaceLabel0);
        containervide.add(spaceLabel2);

         containervide.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
        containervide.getUnselectedStyle().setBackgroundGradientEndColor(0xFFFFFF);
        containervide.getUnselectedStyle().setBackgroundGradientStartColor(0xFFFFFF);
               
               
        
   EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(64, 64), true);
        Image imHome = URLImage.createToStorage(placeholder, "imageEquipe" + ress.getPartie().getHome().getIDEquipe(), ress.getPartie().getHome().getDrapeau(), null);
       
    
         Image imAway = URLImage.createToStorage(placeholder, "imageEquipe" + ress.getPartie().getAway().getIDEquipe(), ress.getPartie().getAway().getDrapeau(), null);


               
             
               
               
               
        Container CHome = new Container(new BoxLayout(BoxLayout.X_AXIS));
        CHome.setWidth(20);
        CHome.setHeight(20);
        Container CAway = new Container(new BoxLayout(BoxLayout.X_AXIS));
        CAway.setWidth(20);
        CAway.setHeight(20);      
               
        Label home = new Label(ress.getPartie().getHome().getNomEquipe());
         home.getAllStyles().setFgColor(0xF69602);
        home.setIcon(imHome.fill( 30, 30));
        Label away = new Label(ress.getPartie().getAway().getNomEquipe());
         away.getAllStyles().setFgColor(0xF69602);
        away.setIcon(imAway.fill( 30, 30));
        CHome.add(home);
        CAway.add(away);
        
       Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       
       Button bt = new Button("Pariez");
      

        bt.getAllStyles().setFgColor(0x080004);
     
        bt.getUnselectedStyle().setBorder(
        RoundBorder.create().rectangle(true)
               .color(0xf1b00a)
);
        bt.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
        bt.getUnselectedStyle().setBackgroundGradientEndColor(0xf1b00a);
              
        bt.addActionListener(e -> {
            
            
            /**
             * ************** page detail ************
             */
            if(ress.getPartie().getEtatMatch().equals("Jouee")){
            Dialog.show("Error","Match a été terminée","OK",null);            }
            else if(testBet(ress.getPartie().getIdMatch())){ Dialog.show("Error","Vous Avez Jouer Ce Match","OK",null);}
            
            else{
              resultat=ress;
             new AjoutBet(res).show();
            /**
             * **************************************
             */}
        });
       
       Container rest = new Container(new BoxLayout(BoxLayout.X_AXIS));
          Label DatePartie = new Label(ress.getPartie().getDatematch());
          DatePartie.setWidth(40);
          if(ress.getPartie().getEtatMatch().equals("Jouee")){
        Label resH = new Label(""+ress.getButHome());
        Label re = new Label("VS");
        Label resA = new Label(""+ress.getButAway());
          rest.add(resH);
        rest.add(re);
        rest.add(resA);
        }
          else{
             Label resH = new Label("?");
        Label re = new Label("VS");
        Label resA = new Label("?");  
              
        rest.add(resH);
        rest.add(re);
        rest.add(resA);
          }
      
        
        
  
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
        
        if(ress.getPartie().getEtatMatch().equals("Jouee")){
          grid.getUnselectedStyle().setBackgroundGradientEndColor(0xC4D7ED);
         grid.getUnselectedStyle().setBackgroundGradientStartColor(0xeae4e4);
        }
      
           else{  grid.getUnselectedStyle().setBackgroundGradientEndColor(0xC9001A);
         grid.getUnselectedStyle().setBackgroundGradientStartColor(0xeae4e4);}  
    
           }
           
           
           
           
           setupSideMenu(res);
           
    
    //FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 5);
      
 
        
        
        
    }

   @Override
    protected void showOtherForm(Resources res) {
        new Affichage(res).show();
    }

    
    public Boolean testBet(int idpartie){
    Boolean test=false;
        BetService betService = new BetService();
      ArrayList<Bet> lis = betService.getList2(u.getId());
      
        for (Bet bet : lis) {
        if(bet.getPartie().getIdMatch()==idpartie)
        {
            test=true ; 
        }
        }
    
    return test ; 
    }
}
