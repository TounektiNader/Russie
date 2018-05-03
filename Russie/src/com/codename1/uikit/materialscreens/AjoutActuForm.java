/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.company.Entites.Actualite;
import com.mycompagny.Service.ServiceActualite;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.company.Entites.User;
import com.company.utils.Local;
import java.util.ArrayList;

/**
 *
 * @author gaelfameni
 */
 public class AjoutActuForm extends SideMenuBaseForm{
   
    Form f;
    TextField titre;
    
    TextField texte;
    
     ServiceActualite se = new ServiceActualite();
     ArrayList<Actualite> lsevent= se.getList2();
     
    
    Button btnajout,btnaff;
  Actualite actu  ;
    private final Button choose;
    private final ImageViewer image;

    public AjoutActuForm(Resources res) {
        
        f = new Form("Actu");
        
        Toolbar tb = f.getToolbar();
        tb.setTitleCentered(false);
        if(WalkthruForm.capturedImage==null){
           profilePic =  res.getImage("user.png");     }
     
     else{  profilePic = WalkthruForm.capturedImage;}
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Label tit = new Label("Actualités", "Title");
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
        
        
        titre = new TextField();
        texte = new TextField();
        ServiceActualite se = new ServiceActualite();
                ArrayList<Actualite> lsevent= se.getList2();
                
                choose=new Button("choose image");

                 
        image =new ImageViewer();
        
                  

        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceActualite s=new ServiceActualite();
           s.browseImage(image);
          
            }

        });
                
        btnajout = new Button("ajouter");
        f.add(titre);
        titre.setHint("titre");
     //  int aaaa = combo.getSelectedIndex();
        texte.setHint("texte");
        Local db = new Local();
        User u = new Local().getUser();
        f.add(texte);
        f.add(choose);
       f.add(image);
        f.add(btnajout);
//        f.add(btnaff);
        btnajout.addActionListener((e) -> {
            ServiceActualite ser = new ServiceActualite();
           
            Actualite t = new Actualite(titre.getText(),texte.getText());
                       System.out.println(actu);

            ser.ajouteactu(t);
            

        });
//        btnaff.addActionListener((e)->{
        //Affichage a=new Affichage();
        //a.getF().show();
        //});
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

   /* public TextField getTnom() {
        return tnom;
    }

    public void setTnom(TextField tnom) {
        this.tnom = tnom;
    }
*/

    @Override
    protected void showOtherForm(Resources res) {
       
    }
    }