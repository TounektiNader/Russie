/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.MultiButton;
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
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.ServiceCafe;
import com.mycompagny.Service.ServiceHotel;
import com.mycompagny.Service.ServiceResto;
import com.mycompagny.Service.ServiceStade;
import com.mycompagny.Service.ServiceVille;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ouss'Hr
 */
public class HomeMenu extends SideMenuBaseForm
{
    Form f;
    Form f2;
    Form f3;
    Form f4;
    Form f5;
    Form f6;
     Image profilePic ;
    public HomeMenu(Resources res) 
    {
        
        f = new Form("Guide de supporter", new BorderLayout());
        
        //        super(BoxLayout.y());
        Toolbar tb = f.getToolbar();
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
        ServiceVille sv = new ServiceVille();
        ArrayList<String> p = sv.count();
        ServiceStade ss = new ServiceStade();
        ArrayList<String> p1 = ss.count();
        ServiceResto sr = new ServiceResto();
        ArrayList<String> p2 = sr.count();
        ServiceHotel sh = new ServiceHotel();
        ArrayList<String> p3 = sh.count();
        ServiceCafe sc = new ServiceCafe();
        ArrayList<String> p4 = sc.count();
        int mm = Display.getInstance().convertToPixels(3);
  EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 4, mm * 4, 0), false);
  Image icon1 = URLImage.createToStorage(placeholder, "adez", "http://localhost/img/coffee.png");
  Image icon2 = URLImage.createToStorage(placeholder, "eder", "http://localhost/img/hotel.png");
  Image icon3 = URLImage.createToStorage(placeholder, "rdedet", "http://localhost/img/resto.png");
  Image icon4 = URLImage.createToStorage(placeholder, "tdey", "http://localhost/img/soccer.png");
  Image icon5 = URLImage.createToStorage(placeholder, "ydeu", "http://localhost/img/congress.png");
  ArrayList<Map<String, Object>> data = new ArrayList<>();
  data.add(createListEntry("Stades", p1.get(0), icon4));
  data.add(createListEntry("Villes", p.get(0), icon5));
  data.add(createListEntry("Cafés", p4.get(0), icon1));
  data.add(createListEntry("Hôtels", p3.get(0), icon2));
  data.add(createListEntry("Restaurants", p2.get(0), icon3));
  
  
  DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
  MultiList ml = new MultiList(model);
 
            ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println( ml.getSelectedIndex());
                if (ml.getSelectedIndex() == 0)
                {
                StadesList a=new StadesList(res);
                a.getF().show();
                f2 = a.getF();
                Toolbar tb = f2.getToolbar();
            
            }
            }    
        });

  
            ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println( ml.getSelectedIndex());
                if (ml.getSelectedIndex() == 1)
                {
                VillesList a=new VillesList(res);
                a.getF().show();
                f3 = a.getF();
                Toolbar tb = f3.getToolbar();
                tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_HOTEL, g->
            {
            
            getF().showBack();
            
            });
            }
            }
        });
      

            ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println( ml.getSelectedIndex());
                if (ml.getSelectedIndex() == 2)
                {
                CafesList a=new CafesList(res);
                a.getF().show();
                f4 = a.getF();
                Toolbar tb = f4.getToolbar();
            tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_HOTEL, g->
            {
            
            getF().showBack();
            
            });
            }
            }
        });

  
            ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println( ml.getSelectedIndex());
                if (ml.getSelectedIndex() == 3)
                {
                HotelsList a=new HotelsList(res);
                a.getF().show();
                f5 = a.getF();
                Toolbar tb = f5.getToolbar();
            tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_HOTEL, g->
            {
            
            getF().showBack();
            
            });
            }
            }
        });

  
            ml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println( ml.getSelectedIndex());
                if (ml.getSelectedIndex() == 4)
                {
                RestosList a=new RestosList(res);
                a.getF().show();
                f6 = a.getF();
                Toolbar tb = f6.getToolbar();
            tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_HOTEL, g->
            {
            
            getF().showBack();
            
            });
            }
            }    
        });


        
  f.add(BorderLayout.CENTER, ml);
  f.show();
}
public Form getF() {
        return f;
    }

    public void setF(Form f) 
    {
        this.f = f;
    }
private Map<String, Object> createListEntry(String name, String date, Image icon) {
  Map<String, Object> entry = new HashMap<>();
  entry.put("Line1", name);
  entry.put("Line2", date);
  entry.put("icon", icon);
  return entry;
    }

    @Override
    protected void showOtherForm(Resources res) 
    {
        new HomeMenu(res).show();
    }
}
