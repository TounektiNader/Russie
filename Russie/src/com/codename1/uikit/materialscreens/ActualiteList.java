package com.codename1.uikit.materialscreens;
import com.company.Entites.Actualite;
import com.mycompagny.Service.ServiceActualite;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Ouss'Hr
 */

public class ActualiteList extends SideMenuBaseForm
{
    Form f;
    String url2 = "/img";
    String url = "http://localhost/ValidationWeb/russia/web/images/";
    EncodedImage enc;

    public ActualiteList(Resources res)  
    {
        
                                try {
                                    enc = EncodedImage.create("/giphy.gif");
                                } catch (IOException ex) 
                                {
                                }
        f = new Form("Actualite", BoxLayout.y());
        
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
        
        
        Image imu = URLImage.createToStorage(enc,"/img/frfr.jpg", "http://localhost/img/uuuuu.jpg",URLImage.RESIZE_SCALE);
f.setLayout(new LayeredLayout());
Label l = new Label("");
l.getUnselectedStyle().setBgImage(imu);
l.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
l.getUnselectedStyle().setBgTransparency(255);
l.setSelectedStyle(l.getUnselectedStyle());
f.addComponent(l);
        ServiceActualite sp = new ServiceActualite();
        ArrayList<Actualite> p = sp.getList2();
        Container ctnlistProduct = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        for (Actualite pr : p) {
            Button del = new Button("delete");
            del.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, del.getUnselectedStyle()));
            del.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent k) {
     
                    Dialog d = new Dialog();

                    if (Dialog.show("Confirmation", "delete this news??", "Ok", "Annuler")) {
                        ConnectionRequest req = new ConnectionRequest();

                        req.setUrl("http://127.0.0.1:8000/suppriactuj/"
                                + pr.getIdactualite());
                        System.out.println(pr.getIdactualite());
                        NetworkManager.getInstance().addToQueue(req);
                        ActualiteList pc = new ActualiteList(res);
                        pc.getF().show();

                    }
                }
            });
            
            Button partage = new Button("partager");
            partage.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    String accessToken = "EAACEdEose0cBADCGsgwaniDS7aqoZCfVbuAtf7LN5tc9iB1CMvCoTDxJGX1wdavTJZACoVTiu0x5F1YtoeRzbJr2yQ5fIeZCk6ra6ZBYQShw3Jf3NQbP0fE85x2UfZBdQk7OsfVi8wPJ643qGMsZCX4uHiH95CsMYAm7N7GfWMtMgKAZAPCrZBBwZBdjRXNZA6D1G7wm7NxevlNQZDZD";

                    FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                    FacebookType response = fbClient.publish("me/feed", FacebookType.class,
                            Parameter.with("message", pr.getTitre() + "\n" + pr.getTexte()));
                    Dialog.show("Confirmation", "votre actualité a été partagé sur facebook", "Ok", null);

                }

            });
                        Button btn3 = new Button("Détails");
                        btn3.setIcon(FontImage.createMaterial(FontImage.MATERIAL_INFO, btn3.getUnselectedStyle()));
                        btn3.addActionListener(new ActionListener() 
                        {
                            @Override
            public void actionPerformed(ActionEvent evt) {
            Form f2;
            f2 = new Form(pr.getTitre(),BoxLayout.y());
            
            Toolbar tb = f2.getToolbar();
            
            tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e->{
            
            f.showBack();
            
            });
            URLImage imgsv = URLImage.createToStorage(enc, url2+pr.getImage(), url+pr.getImage());
                ImageViewer imgvb = new ImageViewer(imgsv);
                Label name = new Label(pr.getTitre());
                                SpanLabel details = new SpanLabel("Détails: "+pr.getTexte());
              
                //Label ville = new Label(t.getCafeVille().getNom());
                
                f2.add(name);
                f2.add(details);
               
                f2.add(imgvb);
                f2.show();
}
        });
                     
        f.show();

                        Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
                        Label label = new Label();
                                                label.getStyle().setBorder(Border.createLineBorder(1, 1));

                        System.out.println(pr.getImage());
                        int deviceWidth = Display.getInstance().getDisplayWidth() / 4;
                                Image placeholder = Image.createImage(deviceWidth, deviceWidth); //square image set to 10% of screen width
                                EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                               if(pr.getImage()==null){
                               
                                label.setIcon(URLImage.createToStorage(encImage,
                                        "Large_" + url2 +"icon.png"+
                                                "", url +"icon.png"+
                                                        "", URLImage.RESIZE_SCALE_TO_FILL));
                               }
                               else{
                                label.setIcon(URLImage.createToStorage(encImage,
                                        "Large_" + url2 +pr.getImage()+
                                                "", url +pr.getImage()+
                                                        "", URLImage.RESIZE_SCALE_TO_FILL));}
                        c.add(label);
                        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        cnt.add(pr.getTitre());
                        c.add(cnt);
                        Container cnt1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                        cnt1.add(btn3);
                        cnt1.add( del);
                        cnt1.add(partage);
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

    @Override
    protected void showOtherForm(Resources res) {
    }

}
