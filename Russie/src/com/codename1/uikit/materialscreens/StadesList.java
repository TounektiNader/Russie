package com.codename1.uikit.materialscreens;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
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
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import com.codename1.util.StringUtil;
import com.company.Entites.Stades;
import com.company.Entites.User;
import com.company.utils.Local;
import com.mycompagny.Service.ServiceStade;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ouss'Hr
 */

public class StadesList extends SideMenuBaseForm
{
    Form f;
    String url2 = "/img";
    String url = "http://localhost/ValidationWeb/russia/web/uploads/images/";
    EncodedImage enc;
    Database db;
    boolean created =false;
    TextField tfd;
    TextField tfNom;
    String prenom;
    public StadesList(Resources res)  
    {
        created = Database.exists("Russia");
        try 
        {
           db = Database.openOrCreate("Russia");
                if(created == true) db.execute("create table commentstade (id INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT, body TEXT, datec TEXT, idstade INTEGER);");
                System.out.println("Table commentstade was created.");
           
 System.out.println("Path of database: "+Database.getDatabasePath("Russia"));
        } 
        catch (IOException ex) 
        {
            
        }
                                try {
                                    enc = EncodedImage.create("/giphy.gif");
                                } catch (IOException ex) 
                                {
                                }
        f = new Form("Stades", BoxLayout.y());
        
        Toolbar tb = f.getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("user-picture.png");
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Label tit = new Label("Stades", "Title");
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
        
        ServiceStade sp = new ServiceStade();
        ArrayList<Stades> p = sp.getList2();
        
        Container ctnlistProduct = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        for (Stades pr : p) {
                                    
                        Button btn3 = new Button("Détails");
                        btn3.setIcon(FontImage.createMaterial(FontImage.MATERIAL_INFO, btn3.getUnselectedStyle()));
                        Button btmap = new Button("Carte");
                        btmap.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MAP, btn3.getUnselectedStyle()));   
                        btmap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                List<String> latlong = StringUtil.tokenize(pr.getPosition(), ",");
                                    double latitude = Double.parseDouble(latlong.get(0));
                                    double longitude = Double.parseDouble(latlong.get(1));
        System.out.println("coordinates: "+latitude+" "+longitude);
                Form f7;
                MapForm a=new MapForm(latitude,longitude,pr.getNom());
                a.getF().show();
                f7 = a.getF();
                Toolbar fg = f7.getToolbar();
            
            fg.addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, g->
            {
            
            getF().showBack();
            
            });
            }
        });
                        btn3.addActionListener(new ActionListener() 
                        {
                            @Override
            public void actionPerformed(ActionEvent evt) {
            Form f2;
            f2 = new Form(pr.getNom(),BoxLayout.y());
            
            Toolbar tb = f2.getToolbar();
            
            tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e->{
            
            f.showBack();
            
            });
            URLImage imgsv = URLImage.createToStorage(enc, url2+pr.getPhoto(), url+pr.getPhoto());
                ImageViewer imgvb = new ImageViewer(imgsv);
                Label capa = new Label("Capacité: "+pr.getCapacite()+" places");
                Label inau = new Label("Inauguration: "+pr.getFondation());
                Label equipe = new Label("Equipe locale: "+pr.getEquipelocale());
                //Label ville = new Label(t.getCafeVille().getNom());
                 Button addcmt = new Button("Commenter");
                 addcmt.setIcon(FontImage.createMaterial(FontImage.MATERIAL_COMMENT, addcmt.getUnselectedStyle()));
                 addcmt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println("Button commenter clicked!");
                System.out.println("Path of database: "+Database.getDatabasePath("Russia"));
                tfNom = new TextField("", "Votre commentaire");
                Button btnOk = new Button("Ajouter");
                btnOk.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ADD_CIRCLE, btnOk.getUnselectedStyle()));
                Label comm = new Label("Votre commentaire");
                f2.add(comm);
                f2.add(tfNom);
                f2.add(btnOk);
                System.out.println("Insertion open");
                btnOk.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        try {
                            if (isInputValid())
                            {
                                String nom = tfNom.getText();
                                Local dblocal = new Local();
                                User u = dblocal.getUser();
                                Date dt = new Date();
                                String str = new SimpleDateFormat("dd/MM/yyyy : HH:mm").format(dt);
                                db.execute("insert into commentstade (user, body, datec, idstade) values ('"+u.getUsername()+"', '"+nom+"', '"+str+"','"+pr.getId()+"' );");
                                System.out.println(pr.getId());
                                System.out.println(pr.getNom());
                                System.out.println(u.getId());
                                System.out.println(u.getUsername());
                                System.out.println("OK");
                                Dialog.show("Commenter", "Merci pour votre avis.", "OK", null);
                                f2.revalidate();
                                StadesList cl = new StadesList(res);
                                cl.getF().show();
                            }
                        } catch (IOException ex)
                        {
                            Dialog.show("Commenter", "Echec de l'ajout de votre commentaire.", "OK", null);
                        }
                    }
                });
            }
        });
                f2.add(inau);
                f2.add(capa);
                f2.add(equipe);
                f2.add(imgvb);
                f2.add(addcmt);                
                
                try {
                    Image placeholder = Image.createImage("/cmnt.png");
                        EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
                        enc.scale(50, 50);
                        Label title = new Label("Commentaires", enc);
                        f2.add(title);
                    Cursor c= db.executeQuery("select * from commentstade where idstade ="+pr.getId());
                   while (c.next()) 
                   {
                        Row r = c.getRow();
                        
                        String nom = r.getString(1)+" a commenté:\n";
                        prenom = r.getString(2)+"\n";
                        Date dt = new Date();
                        String str = new SimpleDateFormat("dd/MM/yyyy : HH:mm").format(dt);
                        SimpleDateFormat format  = new SimpleDateFormat("dd/MM/yyyy : HH:mm"); 
                        Date d1 = null;
                        Date d2 = null;
                        Container contr = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        contr.getStyle().setBorder(Border.createLineBorder(1, 1));
                        try {
			d1 = format.parse(r.getString(3));
			d2 = format.parse(str);

			//in milliseconds
			long diff = d2.getTime() - d1.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			System.out.print(diffDays + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");
                        String dateString = "Il y'a: "+diffDays+" jour(s) "+diffHours+" heure(s) "+diffMinutes+" minute(s)"; 
                        
                        if (diffDays == 0 && diffMinutes > 0) 
                            {
                                dateString = "Il y'a: "+diffHours+" heure(s)"; 
                            }
                            
                            if (diffDays == 0 && diffHours == 0 && diffMinutes == 0) 
                            {
                                dateString = "A l'instant"; 
                            }
 
                            if (diffDays == 0 && diffHours == 0 && diffMinutes > 0) 
                            {
                                dateString = "Il y'a: "+diffMinutes+" minute(s)"; 
                            }
                            
                            if (diffDays > 0) 
                            {
                                dateString = "Il y'a: "+diffDays+" jour(s)"; 
                            }
                            
                            if (diffDays == 1) 
                            {
                                dateString = "Hier"; 
                            }
                            
                            if (diffDays == 7) 
                            {
                                dateString = "Il y'a une semaine"; 
                            }
                            
                            if (diffDays == 30) 
                            {
                                dateString = "Il y'a un mois"; 
                            }
                            
                            if (diffDays == 365) 
                            {
                                dateString = "Il y'a un an"; 
                            }
                        
                        Label l = new Label(nom);
                        Label l2 = new Label(prenom);
                        SpanLabel l3 = new SpanLabel(dateString);
                        
                        contr.add(l);
                        contr.add(l2);
                        contr.add(l3);
		} catch (IOException | ParseException e) {
		}
                        if (r.getString(1).equals(u.getUsername())) 
                        {
                           addcmt.setEnabled(false);
                           addcmt.setIcon(FontImage.createMaterial(FontImage.MATERIAL_BLOCK, addcmt.getUnselectedStyle()));
                           Button supp = new Button("Supprimer");
                           supp.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, btn3.getUnselectedStyle()));
                           Button modif = new Button("Modifier");
                           modif.setIcon(FontImage.createMaterial(FontImage.MATERIAL_EDIT, btn3.getUnselectedStyle()));
                           
                           modif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                System.out.println("Button modifier clicked!");
                System.out.println("Path of database: "+Database.getDatabasePath("Russia"));
                tfNom = new TextField("", prenom);
                Button btnOk = new Button("Modifier");
                btnOk.setIcon(FontImage.createMaterial(FontImage.MATERIAL_EDIT, btnOk.getUnselectedStyle()));
                Label comm = new Label("Votre nouveau commentaire");
                f2.add(comm);
                f2.add(tfNom);
                f2.add(btnOk);
                System.out.println("Update open");
                btnOk.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        try {
                            if (isInputValid())
                            {
                                String nom = tfNom.getText();
                                Date dt = new Date();
                                String str = new SimpleDateFormat("dd/MM/yyyy : HH:mm").format(dt);
                                db.execute("UPDATE commentstade SET body ='"+nom+"',datec ='"+str+"' WHERE idstade='" + pr.getId() + "' AND user='" + u.getUsername() + "' ");
                                System.out.println(pr.getId());
                                System.out.println(pr.getNom());
                                System.out.println(u.getId());
                                System.out.println(u.getUsername());
                                System.out.println("OK");
                                Dialog.show("Commenter", "Votre commentaire a été modifié.", "OK", null);
                                f2.revalidate();
                                StadesList cl = new StadesList(res);
                                cl.getF().show();
                            }
                        } catch (IOException ex)
                        {
                            Dialog.show("Commenter", "Echec de la modification de votre commentaire.", "OK", null);
                        }
                    }
                });
            }
        });
                           supp.addActionListener(new ActionListener() {
                        
                        @Override
                        public void actionPerformed(ActionEvent evt) 
                        {
                            try 
                            {
                                System.out.println("Delete id: "+pr.getId());
                                System.out.println("Delete user:"+u.getUsername());
                                
                                db.execute("DELETE from commentstade WHERE idstade='" + pr.getId() + "' AND user='" + u.getUsername() + "' ");
                                Dialog.show("Suppression", "Votre commentaire a été supprimé.", "OK", null);
                                StadesList cl = new StadesList(res);
                                cl.getF().show();
                                System.out.println("delete ok");
                            }
                            catch (IOException ex) 
                            {
                                System.out.println("error deleting");
                            }
                        }
                    });
                           contr.add(modif);
                           contr.add(supp);
                        }
                        f2.add(contr);
                   }
                
                } catch (IOException ex) {
                }
                f2.show();
}
        });
                     
        f.show();

                        Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
                        Label label = new Label();
                        label.getStyle().setBorder(Border.createLineBorder(1, 1));
                        Label no = new Label(pr.getNom());
                        System.out.println(pr.getPhoto());
                        int deviceWidth = Display.getInstance().getDisplayWidth() / 4;
                                Image placeholder = Image.createImage(deviceWidth, deviceWidth); //square image set to 10% of screen width
                                EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                                label.setIcon(URLImage.createToStorage(encImage,
                                        "Large_" + url2 +pr.getPhoto()+
                                                "", url +pr.getPhoto()+
                                                        "", URLImage.RESIZE_SCALE_TO_FILL));
                        c.add(label);
                        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        c.add(cnt);
                        Container cnt1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                        cnt.add(no);
                        cnt1.add(btn3);
                        cnt1.add(btmap);
                        Container cc = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        cc.getStyle().setBorder(Border.createLineBorder(1, 1));
                        cc.add(c);
                        cc.add(cnt1);
                        ctnlistProduct.add(cc);

        }
        f.add(ctnlistProduct);
    }
    public static Image getImageFromTheme(String name) {
        try {
            Resources resFile = Resources.openLayered("/theme");
            Image image = resFile.getImage(name);
            return image;
        } catch (IOException ioe) {
            System.out.println("Image " + name + " not found: " + ioe);
        }
        return null;
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    private boolean isInputValid() 
    {
        String errorMessage = "";

        if (tfNom.getText() == null || tfNom.getText().length() == 0) 
        {
            errorMessage += "Champ commentaire invalide !\n"; 
        }
        if (errorMessage.length() == 0) 
        {
            return true;
        }
         else 
        {
            Dialog.show("Champs invalides", errorMessage, "OK", null);
            return false;
        }
    }    

    @Override
    protected void showOtherForm(Resources res) 
    {
        new HomeMenu(res).show();
    }

}
