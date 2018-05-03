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

import com.codename1.capture.Capture;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;

/**
 * A swipe tutorial for the application
 *
 * @author Shai Almog
 */
public class WalkthruForm extends Form {
    public Media  video;
      public static Image capturedImage;
    public WalkthruForm(Resources res) {
        super(new LayeredLayout());
        Toolbar.setGlobalToolbar(true);
        getTitleArea().removeAll();
        getTitleArea().setUIID("Container");
        
        setTransitionOutAnimator(CommonTransitions.createUncover(CommonTransitions.SLIDE_HORIZONTAL, true, 400));
        
        Tabs walkthruTabs = new Tabs();
        walkthruTabs.setUIID("Container");
        walkthruTabs.getContentPane().setUIID("Container");
        walkthruTabs.getTabsContainer().setUIID("Container");
        walkthruTabs.hideTabs();
        
       Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_PAUSE);
        
        
        
        Image notes = res.getImage("");
        Image duke = res.getImage("duke.png");
        
        Label notesPlaceholder = new Label("","ProfilePic");
        Label notesLabel = new Label(notes, "ProfilePic");
        Component.setSameHeight(notesLabel, notesPlaceholder);
        Component.setSameWidth(notesLabel, notesPlaceholder);
        Label bottomSpace = new Label();
        
        Label la = new Label("Bienvenue ","WalkthruWhite");
        Label laa = new Label("Russia 2018","WalkthruWhite");
         la.getAllStyles().setFgColor(0x183152);
         laa.getAllStyles().setFgColor(0x183152);

        Container tab1 =BorderLayout.centerAbsolute(BoxLayout.encloseY(
                notesPlaceholder,
                
                la,
                laa,
                bottomSpace
        ));
        tab1.setUIID("WalkthruTab1");
          tab1.getStyle().setBgTransparency(50);
        getAllStyles().setBgImage(res.getImage("rrussia2018.jpg"));
        walkthruTabs.addTab("", tab1);
      //////////////////////////////////////////////////////////
          Label picture = new Label("", "Container");
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
Image camera = FontImage.createMaterial(FontImage.MATERIAL_CAMERA,s);
    
    getToolbar().addCommandToRightBar("", camera, (ev) -> {
    try {
        int width = Display.getInstance().getDisplayWidth();
        
          capturedImage = Image.createImage(Capture.capturePhoto(width, -1));
                             // String filePath = Capture.capturePhoto();
                            //System.out.println(filePath);
//if(filePath != null) {
//    Util.copy(FileSystemStorage.getInstance().openInputStream(filePath), Storage.getInstance().createOutputStream("nader"));
//  
//        
//}
      int widthd = Display.getInstance().getDisplayWidth();
   
       
    Image roundMask = Image.createImage(width, capturedImage.getHeight(), 0xff000000);
      
        
        Graphics gr = roundMask.getGraphics();
        gr.setColor(0xffffff);
        gr.fillArc(0, 0, width, width, 0, 360);
        Object mask = roundMask.createMask();
        capturedImage = capturedImage.applyMask(mask);
        picture.setIcon(capturedImage);
        
        
    
        
    } catch(IOException err) {
        Log.e(err);
    }
});
        ///////////////////////////////////////////////////////

String file = "res/russia.mp4";
                
                    
        


       
                try {
                  video = MediaManager.createMedia(file,true);
                   
                    video.setVolume(150);
                    video.play();
                } catch(IOException err) {
                    
                }
                
                settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(video.isPlaying())
                {
                    video.pause();
                    FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_PLAY_ARROW);}
                else
                {
                  video.play(); 
                     FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_PAUSE);
                 
                }
            }
        });
        
        ///////////////////////////////////////////////////////
        Label bottomSpaceTab2 = new Label();
        
        Container tab2 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
              //  new Label(duke, "ProfilePic"),
                new Label("Photo Profil", "WalkthruWhite"),
               // new SpanLabel("Write once run anywhere native mobile development " +
                        //                    "Get Java working on all devices as it was always meant " +
                          //                  "to be!",  "WalkthruBody"),
                picture,
                bottomSpaceTab2
        ));
        
        tab2.setUIID("WalkthruTab2");
          tab2.getStyle().setBgTransparency(50);

        walkthruTabs.addTab("", tab2);
       
        add(walkthruTabs);
        
        ButtonGroup bg = new ButtonGroup();
        Image unselectedWalkthru = res.getImage("unselected-walkthru.png");
        Image selectedWalkthru = res.getImage("selected-walkthru.png");
        RadioButton[] rbs = new RadioButton[walkthruTabs.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(CENTER);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        walkthruTabs.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Button skip = new Button("Accèder");
        skip.setUIID("LoginButton");        
        skip.addActionListener(e -> {new ProfileForm(res).show();video.pause();});
        
        Container southLayout = BoxLayout.encloseY(
                        radioContainer,
                        skip
                );
        add(BorderLayout.south(
                southLayout
        ));
        
        Component.setSameWidth(bottomSpace, bottomSpaceTab2, southLayout);
        Component.setSameHeight(bottomSpace, bottomSpaceTab2, southLayout);
        
        // visual effects in the first show
        addShowListener(e -> {
            notesPlaceholder.getParent().replace(notesPlaceholder, notesLabel, CommonTransitions.createFade(1500));
        });
    }    
}
