/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

/**
 *
 * @author Nader
 */
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import static com.codename1.uikit.materialscreens.Affichage.resultat;
import com.company.Entites.Equipe;
import com.company.Entites.Partie;
import com.company.Entites.Resultat;
import com.company.Entites.User;
import com.company.utils.Local;
import com.mycompagny.Service.Authentification;
import com.mycompagny.Service.BetService;
import java.io.IOException;
import com.company.Entites.Bet;

public class AjoutBet extends SideMenuBaseForm {
    BetService betService = new BetService();
    Local dblocal = new Local();
    User u = dblocal.getUser();
    private EncodedImage jet;
    private Image imgAway;
    private EncodedImage enc ;
     Image profilePic ;
    public AjoutBet(Resources res) {
        super(BoxLayout.y());

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        if(WalkthruForm.capturedImage==null){
           profilePic =  res.getImage("user.png");     }
     
     else{  profilePic = WalkthruForm.capturedImage;}

          tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, g->
            {
            
            new Affichage(res).show();
            
            });
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Label tit = new Label("PARIEZ", "Title");
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
       
        addButtonBottom(profilePic, Affichage.resultat.getPartie().getHome(), 0xd997f1, true,Affichage.resultat.getPartie().getHome().getIDEquipe(),Affichage.resultat.getPartie());
        addButtonBottom(profilePic, Affichage.resultat.getPartie().getAway(), 0xd997f1, true,Affichage.resultat.getPartie().getAway().getIDEquipe(),Affichage.resultat.getPartie());

          Container cbet = new Container(BoxLayout.y());
        Container equipesAparier = new Container(BoxLayout.y());
        
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(64,64),true);
          try {
            enc = EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
            System.out.println("error encoder");
        }

        Image imHome = URLImage.createToStorage(placeholder,"imageEquipe"+Affichage.resultat.getPartie().getHome().getIDEquipe(),Affichage.resultat.getPartie().getHome().getDrapeau(),null);
        Image imAway = URLImage.createToStorage(placeholder,"imageEquipe"+Affichage.resultat.getPartie().getAway().getIDEquipe(),Affichage.resultat.getPartie().getAway().getDrapeau(),null);
imHome.fill( 30, 30);
          
        Label create = new Button("Votre Choix");
        create.setUIID("CreateNewAccountButton");
        create.getAllStyles().setFgColor(0xf1b00a);

        
        RadioButton rb1 = new RadioButton(Affichage.resultat.getPartie().getHome().getNomEquipe(),imHome.fill( 30, 30));
        RadioButton rb2 = new RadioButton(Affichage.resultat.getPartie().getAway().getNomEquipe(),imAway.fill(30,30));
        RadioButton rb3 = new RadioButton("Null");
        new ButtonGroup(rb1, rb2, rb3);
     
        equipesAparier.add(rb1);
        equipesAparier.add(rb2);
        equipesAparier.add(rb3);
        
        Button bt = new Button ("Pariez");
          bt.getAllStyles().setFgColor(0x080004);
     
        bt.getUnselectedStyle().setBorder(
        RoundBorder.create().rectangle(true)
               .color(0xf1b00a)
);
       bt.addActionListener(e -> {
            /**
             * ************** bech nkamleou lenna  ************
             */
            
       if(rb1.isSelected()){
           Bet bet = new Bet();
           
           bet.setUser(u);
           bet.setPartie(Affichage.resultat.getPartie());
           bet.setValeurr(Affichage.resultat.getPartie().getHome().getIDEquipe());
           int sold= u.getJeton()-1;
           dblocal.updatejeton(sold,u.getId());
       betService.ajoutBet(bet);
           Dialog.show("Confirmation","Votre bet a été ajouté avec succès","OK",null);
       
           new MesBets(res).show();
           
       }
       else if(rb2.isSelected()){
                 Bet bet = new Bet();
           bet.setUser(u);
           bet.setPartie(Affichage.resultat.getPartie());
           bet.setValeurr(Affichage.resultat.getPartie().getAway().getIDEquipe());
            int sold= u.getJeton()-1;
           dblocal.updatejeton(sold,u.getId());
       betService.ajoutBet(bet);
          Dialog.show("Confirmation","Votre bet a été ajouté avec succès","OK",null);
           new MesBets(res).show();
       }
       else if(rb3.isSelected()){
           Bet bet = new Bet();
           bet.setUser(u);
           bet.setPartie(Affichage.resultat.getPartie());
           bet.setValeurr(0);
           
           int sold= u.getJeton()-1;
           dblocal.updatejeton(sold,u.getId());
           
           betService.ajoutBet(bet);
              Dialog.show("Confirmation","Votre bet a été ajouté avec succès","OK",null);
               new MesBets(res).show();
       }
       else{
     
              ToastBar.Status s = ToastBar.getInstance().createStatus();
                            s.setMessage("Vous devez Choisir une resultat ");
                          
                            Image i = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"));
                            s.setIcon(i);
                            s.setExpires(5000);
                            s.show();
       }
           
        });
       cbet.add(create);
        cbet.add(equipesAparier);
        cbet.add(bt);
        
        
           cbet.setWidth(200);
  
        cbet.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
        cbet.getUnselectedStyle().setBackgroundGradientEndColor(0xC4D7ED);
        cbet.getUnselectedStyle().setBackgroundGradientStartColor(0xeae4e4);
        add(cbet);
        
        setupSideMenu(res);
    }

    private void addButtonBottom(Image arrowDown, Equipe equipe, int color, boolean first, int j,Partie partie) {

        Container finishLandingPage = new Container(BoxLayout.x());
        Container c = new Container(BoxLayout.y());
        
      
        
        
        
        Container entreneur = new Container(BoxLayout.x());
        Container butmarque = new Container(BoxLayout.x());
        Container butEnCaisse = new Container(BoxLayout.x());
        Container matchGagne = new Container(BoxLayout.x());
        Container matchperdu = new Container(BoxLayout.x());
        Container matchMatchNull = new Container(BoxLayout.x());
        Container contient = new Container(BoxLayout.x());

        Container containervide = new Container(BoxLayout.x());
        Label spaceLabel0 = new Label(" ");
        Label spaceLabel2 = new Label(" ");
        containervide.add(spaceLabel0);
        containervide.add(spaceLabel2);

       
        
         
           
            
               
               try {
            enc = EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
            System.out.println("error encoder");
        }
               
                    
   EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(64, 64), true);
        Image imgAway = URLImage.createToStorage(placeholder, "imageEquipe" + equipe.getIDEquipe(), equipe.getDrapeau(), null);
       
   



     //   finishLandingPage.add( imgAway.scaledWidth(100));
        
        Label nomequipe = new Label(equipe.getNomEquipe());
        nomequipe.getAllStyles().setFgColor(0xF69602);
       
        
       nomequipe.setIcon(imgAway.fill( 30, 30)); 
        
        SpanLabel entr = new SpanLabel("Entraineur:");
        Label nomEntre = new Label(equipe.getEntraineur());
        nomEntre.getAllStyles().setFgColor(0xE12336);
        entr.getAllStyles().setFgColor(0x000000);
        entreneur.add(entr);
        entreneur.add(nomEntre);

        SpanLabel btm = new SpanLabel("Buts marques:");
        btm.getAllStyles().setFgColor(0x000000);

        Label nbm = new Label("" + equipe.getButMarque());
        nbm.getAllStyles().setFgColor(0xE12336);
        butmarque.add(btm);
        butmarque.add(nbm);
        
        
        SpanLabel bte = new SpanLabel("Buts Encaissés:");
        bte.getAllStyles().setFgColor(0x000000);

        Label nbe = new Label("" + equipe.getButEncaisse());
        nbe.getAllStyles().setFgColor(0xE12336);
        butEnCaisse.add(bte);
        butEnCaisse.add(nbe);

        
        
        SpanLabel matchgagne = new SpanLabel("Match Gagnées:");
        matchgagne.getAllStyles().setFgColor(0x000000);

        Label nmatchgagne = new Label("" + equipe.getMatchGagne());
        nmatchgagne.getAllStyles().setFgColor(0xE12336);
        matchGagne.add(matchgagne);
        matchGagne.add(nmatchgagne);

        
        
        SpanLabel matchperdue = new SpanLabel("Match Perdus:");
        matchperdue.getAllStyles().setFgColor(0x000000);

        Label nmatchperdue = new Label("" + equipe.getMatchperdu());
        nmatchperdue.getAllStyles().setFgColor(0xE12336);
        matchperdu.add(matchperdue);
        matchperdu.add(nmatchperdue);
        
        
        
        SpanLabel matchnull = new SpanLabel("Match Nulls:");
        matchnull.getAllStyles().setFgColor(0x000000);

        Label nmatchnull = new Label("" + equipe.getMatchNull());
        nmatchnull.getAllStyles().setFgColor(0xE12336);
        matchMatchNull.add(matchnull);
        matchMatchNull.add(nmatchnull);

        
        
   
        
        
        
        c.add(nomequipe);
        c.add(entreneur);
        c.add(butmarque);
        c.add(butEnCaisse);
        c.add(matchGagne);
        c.add(matchperdu);
        c.add(matchMatchNull);
 

        // c.add(l3);
        c.setWidth(500);
  
        c.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
        c.getUnselectedStyle().setBackgroundGradientEndColor(0xC4D7ED);
        c.getUnselectedStyle().setBackgroundGradientStartColor(0xeae4e4);

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

    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if (first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }
}
