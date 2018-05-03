package com.codename1.uikit.materialscreens;

import com.company.Entites.Actualite;
import com.mycompagny.Service.ServiceActualite;
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
import com.codename1.ui.spinner.DateSpinner;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
/**
 *
 * @author sana
 */
public class HomeForm extends SideMenuBaseForm{

    Form f;
    Form f2;
    Form f3;

    Button btnaff;
    Button btnajout;

    public HomeForm(Resources res) {
        f = new Form("Menu Actualité");
        
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
       

//add(gov);
//gov.getAllStyles().setMargin(LEFT, 0);
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);

        System.out.println("test");
        getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
        getUnselectedStyle().setBackgroundGradientEndColor(0xFFFFFF);
        getUnselectedStyle().setBackgroundGradientStartColor(0xFFFFFF);
        setupSideMenu(res);
        
        
        f3= new Form("Nouvelle actualté");
        btnaff=new Button("Actualite");
        btnaff.setIcon(FontImage.createMaterial(FontImage.MATERIAL_BOOK, btnaff.getUnselectedStyle()));
         btnajout= new Button("Nouvelle actualité");
 btnajout.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ADD, btnajout.getUnselectedStyle()));
        f.add(btnaff);
        f.add(btnajout);
      
        btnaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ActualiteList a=new ActualiteList(res);
                a.getF().show();
                f2 = a.getF();
                Toolbar tb = f2.getToolbar();
            
            tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_HOME, g->
            {
            
            getF().showBack();
            
            });
            }
        });
        f.show();
        btnajout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AjoutActuForm a=new AjoutActuForm(res);
                a.getF().show();
                f2 = a.getF();
                Toolbar tb = f2.getToolbar();
            
            tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_HOME, g->
            {
            
            getF().showBack();
            
            });
            }
        });
    }
    
    

    public Form getF() {
        return f;
    }

    public void setF(Form f) 
    {
        this.f = f;
    }

    @Override
    protected void showOtherForm(Resources res) {
    }
    
   

}
