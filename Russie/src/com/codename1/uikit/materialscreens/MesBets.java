/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.company.Entites.Resultat;
import com.company.Entites.User;
import com.company.utils.Local;
import com.mycompagny.Service.BetService;
import com.mycompagny.Service.ServiceResultat;
import java.io.IOException;
import java.util.ArrayList;
import com.company.Entites.Bet;
import com.company.Entites.Equipe;
import com.company.Entites.Partie;

/**
 *
 * @author Nader
 */
public class MesBets extends SideMenuBaseForm {

    Form hi;
    BetService betService = new BetService();
    ServiceResultat sr = new ServiceResultat();
    Local dblocal = new Local();
    User u = dblocal.getUser();
    private Image imgHome;
    private Image imgAway;
    private EncodedImage enc;
    public static Resultat resultat = new Resultat();
 Image profilePic ;
    public MesBets(Resources res) {
        super(BoxLayout.y());

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        
         if(WalkthruForm.capturedImage==null){
           profilePic =  res.getImage("user.png");     }
     
     else{  profilePic = WalkthruForm.capturedImage;}
        

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Label tit = new Label("Mes Bets ", "Title");
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

    

        Container containervid = new Container(BoxLayout.x());
        Label spaceLabel00 = new Label(" ");
        Label spaceLabel22 = new Label(" ");
        containervid.add(spaceLabel00);
        containervid.add(spaceLabel22);

        containervid.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
        containervid.getUnselectedStyle().setBackgroundGradientEndColor(0xFFFFFF);
        containervid.getUnselectedStyle().setBackgroundGradientStartColor(0xFFFFFF);

        add(containervid);
      

        ArrayList<Bet> lis = betService.getList2(u.getId());
      
        if(lis.isEmpty()){
        
            
             ToastBar.Status s = ToastBar.getInstance().createStatus();
                            s.setMessage("Vous n'avez pas encore des bets ");
                          
                            Image i = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"));
                            s.setIcon(i);
                            s.setExpires(5000);
                            s.show();
                            
                          
        
        }
        
        else{
        for (Bet bet : lis) {

   addButtonBottom(profilePic, bet.getPartie().getHome(),bet.getPartie().getAway(), 0xd997f1, true,res,bet);


        }
        }
        setupSideMenu(res);

        //FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 5);
    }

    private void addButtonBottom(Image arrowDown, Equipe home, Equipe away, int color, boolean first,Resources res,Bet bet) {

        Container finishLandingPage = new Container(BoxLayout.x());
        Container c = new Container(BoxLayout.y());

        Container conHome = new Container(BoxLayout.x());
        Container contAway = new Container(BoxLayout.x());
        Container contChoix = new Container(BoxLayout.x());
        Container contResultat = new Container(BoxLayout.x());

        Container containervide = new Container(BoxLayout.x());
        Label spaceLabel0 = new Label(" ");
        Label spaceLabel2 = new Label(" ");
        containervide.add(spaceLabel0);
        containervide.add(spaceLabel2);

        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(64, 64), true);
        Image imHome = URLImage.createToStorage(placeholder, "imageEquipe" + home.getIDEquipe(), home.getDrapeau(), null);
        Image imAway = URLImage.createToStorage(placeholder, "imageEquipe" + away.getIDEquipe(), away.getDrapeau(), null);

        Label homePlace = new Label("Home");
        Label homeLabel = new Label(home.getNomEquipe());
        homeLabel.getAllStyles().setFgColor(0xF69602);
        homeLabel.setIcon(imHome.fill( 30, 30));
conHome.add(homePlace);
conHome.add(homeLabel);
    
        Label awayPlace = new Label("Away");
        Label awayLabel = new Label(away.getNomEquipe());
        awayLabel.getAllStyles().setFgColor(0xF69602);
        awayLabel.setIcon(imAway.fill( 30, 30));
contAway.add(awayPlace);
contAway.add(awayLabel);
        
        Label resPlace = new Label("Resultat");
       
          Label labeRes1 = new Label("");
         labeRes1.getAllStyles().setFgColor(0xF69602);
       
         Image image1 = res.getImage("1.PNG");
         Image image2 = res.getImage("2.png");
         Image imagen = res.getImage("n.PNG");
         Image imageattent = res.getImage("attente.png");
         
   int idEquipeGagne=sr.getEquipe(bet.getPartie().getIdMatch());
         
            if(bet.getEtat().equals("Traite")){
         labeRes1.setIcon(imageattent.fill( 55,20));
             
         }
         else{
          if(idEquipeGagne==home.getIDEquipe()){ 
        
  
        labeRes1.setIcon(image1.fill( 55,20));
          
    }
else
    if(idEquipeGagne==away.getIDEquipe()) {
       
        
       labeRes1.setIcon(image2.fill( 55,20));
      
    }
else
    if(idEquipeGagne==0) {
      
        labeRes1.setIcon(imagen.fill( 55,20));
        
    }
            }
         
        contResultat.add(resPlace);
         contResultat.add(labeRes1);
         
         
         
        Label choixPlace = new Label("Choix");       
           

          Label labeChoix1 = new Label("");
         labeChoix1.getAllStyles().setFgColor(0xF69602);
      
    if(bet.getValeurr()==home.getIDEquipe()){ 
        
  
        labeChoix1.setIcon(image1.fill( 55,20));
          
    }
else
    if(bet.getValeurr()==away.getIDEquipe()) {
       
        
        labeChoix1.setIcon(image2.fill( 55,20));
      
    }
else
    if(bet.getValeurr()==0) {
      
        labeChoix1.setIcon(imagen.fill( 55,20));
        
    }
         
contChoix.add(choixPlace);
contChoix.add(labeChoix1);
        

c.add(conHome);
        c.add(contAway);
        c.add(contChoix);
        c.add(contResultat);

        // c.add(l3);
        c.setWidth(500);
if(bet.getEtat().equals("Gain")){
        c.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
        c.getUnselectedStyle().setBackgroundGradientEndColor(0xB0CC99);
        c.getUnselectedStyle().setBackgroundGradientStartColor(0xeae4e4);}
else if(bet.getEtat().equals("Perte")){

       c.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
        c.getUnselectedStyle().setBackgroundGradientEndColor(0xC44C51);
        c.getUnselectedStyle().setBackgroundGradientStartColor(0xeae4e4);}
else{
 c.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
        c.getUnselectedStyle().setBackgroundGradientEndColor(0xC4D7ED);
        c.getUnselectedStyle().setBackgroundGradientStartColor(0xeae4e4);}


        containervide.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
        containervide.getUnselectedStyle().setBackgroundGradientEndColor(0xFFFFFF);
        containervide.getUnselectedStyle().setBackgroundGradientStartColor(0xFFFFFF);
        finishLandingPage.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
        finishLandingPage.getUnselectedStyle().setBackgroundGradientEndColor(0xFFFFFF);
        finishLandingPage.getUnselectedStyle().setBackgroundGradientStartColor(0xFFFFFF);
        // c.add(containervide);
        finishLandingPage.add(c);
        // finishLandingPage.add(containervide);
        c.setPreferredW(400);
        add(FlowLayout.encloseIn(finishLandingPage));
        add(containervide);
    }

    @Override
    protected void showOtherForm(Resources res) {
        new Affichage(res).show();
    }
}
