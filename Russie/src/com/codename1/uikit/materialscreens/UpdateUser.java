/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.company.Entites.User;
import com.company.utils.Local;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Nader
 */
public class UpdateUser extends SideMenuBaseForm {

    Local dblocal = new Local();
    User u = dblocal.getUser();
    private EncodedImage jet;
    String urlphp = "http://localhost/mobile/russia/updateUser.php";
    public static String reponse = "";
   Image profilePic ;
    public UpdateUser(Resources res) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
   
        
          if(WalkthruForm.capturedImage==null){
           profilePic =  res.getImage("user.png");     }
     
     else{  profilePic = WalkthruForm.capturedImage;}
         
        
        
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Image jeton = res.getImage("jeton.png");

        Container remainingTasks = BoxLayout.encloseY(
                new Label("" + u.getJeton(), jeton, "CenterTitle"),
                new Label("Jetons", "CenterSubTitle")
        );
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
                new Label("32", "CenterTitle"),
                new Label("Recomponses", "CenterSubTitle")
        );
        completedTasks.setUIID("CompletedTasks");

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label(u.getUsername(), "Title"),
                                new Label(u.getNom(), "SubTitle")
                        )
                ).add(BorderLayout.WEST, profilePicLabel),
                GridLayout.encloseIn(2, remainingTasks, completedTasks)
        );

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));

        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);

        if (!reponse.equals("OK")) {
            addButtonBottom(profilePic, u, 0xd997f1, true,res);
        } else {
            addButtonBottom(profilePic, u, 0xd997f1, true,res);
            new ProfileForm(res).show();
        }

        setupSideMenu(res);
    }

    private void addButtonBottom(Image arrowDown, User user, int color, boolean first,Resources res) {
        Container c = new Container(BoxLayout.y());
        TextField login = new TextField(u.getUsername(), "Username", 15, TextField.EMAILADDR);
           login.setUIID("txtFieldText");
        login.getAllStyles().setFgColor(0x132959);
        //  TextField password = new TextField("", "Password", 15, TextField.PASSWORD) ;
        TextField nom = new TextField(u.getNom(), "Nom", 15, TextField.EMAILADDR);
         nom.setUIID("txtFieldText");
        nom.getAllStyles().setFgColor(0x132959);
        TextField prenom = new TextField(u.getPrenom(), "Prenom", 15, TextField.EMAILADDR);
       prenom.setUIID("txtFieldText");
        prenom.getAllStyles().setFgColor(0x132959);
        TextField mail = new TextField(u.getEmail(), "Mail", 15, TextField.EMAILADDR);
             mail.setUIID("txtFieldText");
        mail.getAllStyles().setFgColor(0x132959);
        TextField num = new TextField(u.getNum(), "+216XXXXXXXX", 15, TextField.PHONENUMBER);
         num.setUIID("txtFieldText");
        num.getAllStyles().setFgColor(0x132959);
        ComboBox natio = new ComboBox("Tunisien", "Belge", "Français", "Marocain", "Islandais");

        natio.getAllStyles().setBgColor(0xFFFFFF);
        natio.setHeight(10);
        natio.setWidth(10);
        login.getAllStyles().setMargin(LEFT, 0);
        //   password.getAllStyles().setMargin(LEFT, 0);
        nom.getAllStyles().setMargin(LEFT, 0);
        prenom.getAllStyles().setMargin(LEFT, 0);
        mail.getAllStyles().setMargin(LEFT, 0);
        num.getAllStyles().setMargin(LEFT, 0);
        natio.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label nomIcon = new Label("", "TextField");
        Label prenomIcon = new Label("", "TextField");
        Label mailIcon = new Label("", "TextField");
        Label numIcon = new Label("", "TextField");
        Label natioanliteIcon = new Label("", "TextField");

        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        nomIcon.getAllStyles().setMargin(RIGHT, 0);
        prenomIcon.getAllStyles().setMargin(RIGHT, 0);
        mailIcon.getAllStyles().setMargin(RIGHT, 0);
        numIcon.getAllStyles().setMargin(RIGHT, 0);
        natioanliteIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 5);
        FontImage.setMaterialIcon(nomIcon, FontImage.MATERIAL_PERSON_OUTLINE, 5);
        FontImage.setMaterialIcon(prenomIcon, FontImage.MATERIAL_PERSON_OUTLINE, 5);
        FontImage.setMaterialIcon(mailIcon, FontImage.MATERIAL_MAIL, 5);
        FontImage.setMaterialIcon(numIcon, FontImage.MATERIAL_PHONE, 5);
        FontImage.setMaterialIcon(natioanliteIcon, FontImage.MATERIAL_NATURE, 5);

        Button loginButton = new Button("Modifier");

          loginButton.getAllStyles().setFgColor(0xFFFFFF);
     
        loginButton.getUnselectedStyle().setBorder(
        RoundBorder.create().rectangle(true)
               .color(0x183152)
);

        loginButton.addActionListener(e -> {
            /**
             * ************** page detail ************
             */
             if((mail.getText().trim().length() > 0)&&(login.getText().trim().length() > 0)&&(nom.getText().trim().length() > 0)
                    &&(prenom.getText().trim().length() > 0)&&(num.getText().trim().length() > 0)){
            u.setEmail(mail.getText());
            u.setEmail_canonical(mail.getText());
            u.setUsername(login.getText());
            u.setUsername_canonical(login.getText());
            u.setNom(nom.getText());
            u.setPrenom(prenom.getText());
            u.setNum(num.getText());
            u.setNationalite(natio.getSelectedItem().toString());

            try {
                dblocal.update(u);
                ConnectionRequest request = new ConnectionRequest();
            
request.setUrl("http://localhost/mobile/russia/updateUser.php");
request.setPost(false);
request.setContentType("TYPE MIME DU CONTENU");
request.addArgument("id", "" +u.getId());
request.addArgument("username",u.getUsername());
request.addArgument("username_cano",u.getUsername_canonical());
request.addArgument("mail",u.getEmail());
request.addArgument("mail_canoc", u.getEmail_canonical());
request.addArgument("nom", u.getNom());
request.addArgument("prenom", u.getPrenom());
request.addArgument("num", u.getNum());
request.addArgument("nationalite", u.getNationalite());

                
                System.out.println(request.getUrl());
                request.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {

                        try {
                            reponse = new String(request.getResponseData(), "UTF-8");
                            System.out.println(reponse);

                        } catch (UnsupportedEncodingException ex) {

                        }
                    }
                });

                  NetworkManager.getInstance().addToQueue(request);
                  new ProfileForm(res).show();
                /**
                 * update Base De doonée MySQL
                 */
            } catch (IOException ex) {

            }
             }
             else{ ToastBar.Status s = ToastBar.getInstance().createStatus();
                            s.setMessage("Vous devez Remplir tous les champs");
                          
                            Image i = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"));
                            s.setIcon(i);
                            s.setExpires(5000);
                            s.show();}
        });
        Container by = BoxLayout.encloseY(
                BorderLayout.center(login).add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(nom).add(BorderLayout.WEST, nomIcon),
                BorderLayout.center(prenom).add(BorderLayout.WEST, prenomIcon),
                BorderLayout.center(mail).add(BorderLayout.WEST, mailIcon),
                BorderLayout.center(num).add(BorderLayout.WEST, numIcon),
                BorderLayout.center(natio).add(BorderLayout.WEST, natioanliteIcon),
                loginButton
        );
        by.setWidth(500);
        by.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
        by.getUnselectedStyle().setBackgroundGradientEndColor(0xE1E6FA);
        by.getUnselectedStyle().setBackgroundGradientStartColor(0xeae4e4);
        by.getStyle().setBgTransparency(50);
       
        add(BorderLayout.CENTER, by);

        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);

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
