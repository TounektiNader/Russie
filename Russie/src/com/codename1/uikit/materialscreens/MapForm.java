/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.codename1.uikit.materialscreens;

import com.company.Entites.MapContainer;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;


/**
 *
 * @author Mohamed
 */
public class MapForm extends SideMenuBaseForm
{
    Form hi;
    Image profilePic ;

    public MapForm(Resources res) 
    {
        
        hi = new Form("");
        
        Toolbar tb = hi.getToolbar();
        tb.setTitleCentered(false);
     
          if(WalkthruForm.capturedImage==null){
           profilePic =  res.getImage("user.png");     }
     
     else{  profilePic = WalkthruForm.capturedImage;}
        
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Label tit = new Label("Guide de supporter", "Title");
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
        
        hi.setLayout(new BorderLayout());
        final MapContainer cnt = new MapContainer();
        hi.addComponent(BorderLayout.CENTER, cnt);
        
        hi.show();
    }
    
    public MapForm(double lat, double lon, String name) {
        
        hi = new Form(name);
        hi.setLayout(new BorderLayout());
         MapContainer cnt = new MapContainer();
        hi.addComponent(BorderLayout.CENTER, cnt);
        cnt.setCameraPosition(new Coord(lat, lon));
        try {
            EncodedImage ic = EncodedImage.create("/star.png");
            ic.scale(32, 37);
            cnt.setCameraPosition(new Coord(lat, lon));
                    cnt.addMarker(ic, new Coord(lat, lon), "Hi marker", "Optional long description", new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            Dialog.show("Détails", name, "OK", null);
                        }
                    });
                    
        } catch (IOException ex) {
           
        }
        
        
        hi.show();
    }
    
    public Form getF() {
        return hi;
    }

    public void setF(Form f) {
        this.hi = f;
    }

    @Override
    protected void showOtherForm(Resources res) 
    {
        new HomeMenu(res).show();
    }

    
}
