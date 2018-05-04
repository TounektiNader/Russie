/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.materialscreens;

import com.codename1.components.ToastBar;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.company.Entites.User;
import com.company.utils.Local;

/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuBaseForm extends Form {
  Local dblocal = new Local();
    User u = dblocal.getUser();
         Image profilePic ;
    
    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res) {
        if(WalkthruForm.capturedImage==null){
           profilePic =  res.getImage("user.png");     }
     
     else{  profilePic = WalkthruForm.capturedImage;}
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(u.getUsername(), profilePic, "SideMenuTitle");
          profilePicLabel.setUIID("txtFieldText");
        profilePicLabel.getAllStyles().setFgColor(0x132959);
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
         profilePicLabel.getStyle().setBgTransparency(100);
         
        sidemenuTop.setUIID("SidemenuTop");
        
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Dashboard", FontImage.MATERIAL_DASHBOARD,  e -> new StatsForm(res).show());        
        getToolbar().addMaterialCommandToSideMenu("  Guide de supporter", FontImage.MATERIAL_HOTEL,  e ->new HomeMenu(res).getF().show());
        getToolbar().addMaterialCommandToSideMenu("  Cadeaux", FontImage.MATERIAL_SHOP,  e -> new CadeauForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Equipes", FontImage.MATERIAL_ATTACH_MONEY,  e -> new AffichageEquipeJoueur(res).getF().show());
        getToolbar().addMaterialCommandToSideMenu("  Matchs", FontImage.MATERIAL_TRENDING_UP,  e ->new Affichage(res).show());    
        getToolbar().addMaterialCommandToSideMenu("  Mes Bets", FontImage.MATERIAL_ATTACH_MONEY,  e -> new MesBets(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Actualités", FontImage.MATERIAL_NEW_RELEASES,  e -> new HomeForm(res).getF().show()); 
        getToolbar().addMaterialCommandToSideMenu("  Profil", FontImage.MATERIAL_PERSON_OUTLINE,  e -> new ProfileForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Déconnexion", FontImage.MATERIAL_EXIT_TO_APP,  e -> new LoginForm(res).show());
    }
    
    protected abstract void showOtherForm(Resources res);
}
