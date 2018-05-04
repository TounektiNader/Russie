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

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.models.XYSeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.CubicLineChart;
import com.codename1.charts.views.PieChart;
import com.codename1.charts.views.PointStyle;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.Resources;
import com.company.Entites.Bet;
import com.company.Entites.User;
import com.company.utils.Local;
import com.mycompagny.Service.BetService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shai Almog
 */
public class StatsForm extends SideMenuBaseForm {
    BetService bt = new BetService();
       Local dblocal = new Local();
    User u = dblocal.getUser();
    List<Bet> listBet = new ArrayList<Bet>();

//    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
  //  private static final String[] LABELS = {"Gain", "Perte", "En cours"};

    public StatsForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("russia2018.jpg");        
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());
        
        tb.getUnselectedStyle().setBgImage(tintedImage);
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        
        
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent = 
                BorderLayout.north(
                    BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                add(BorderLayout.CENTER, space).
                add(BorderLayout.SOUTH, 
                        FlowLayout.encloseIn(
                                new Label("Statistiques ", "WelcomeBlue"),
                                new Label(" Mes Bets", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(BorderLayout.NORTH, separator);
        

       Bet betGain = new Bet(bt.getbetgain(u.getId()),"Gain");
       Bet betPerte = new Bet(bt.getbetPerte(u.getId()),"Perte");
       Bet betCours = new Bet(bt.getbetCours(u.getId()),"Traite");
         listBet.add(betGain);
         listBet.add(betPerte);
         listBet.add(betCours);
          
         createPieChartForm();
        
        setupSideMenu(res);
    }

  
      private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLabelsColor(ColorUtil.BLACK);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 20});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title,ArrayList<Bet>total) {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
        for (Bet value : listBet) {
            try {
            double v = value.getValeurr();System.out.println("v value : "+v);
            int s = total.size();System.out.println("s value : "+s);
            double quant = (v/s)*100;
 
                System.out.println(quant);
            series.add("" + value.getEtat(),(int) quant);
            } catch (ArithmeticException e) {
            }
            
        }

        return series;
    }

    public void createPieChartForm() {

        // Generate the values
       
       ArrayList<Bet> total = new ArrayList<Bet>();
    
        total=bt.getList2(u.getId());
        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.LTGRAY};
        DefaultRenderer renderersrer = buildCategoryRenderer(colors);
        renderersrer.setZoomButtonsVisible(true);
        renderersrer.setZoomEnabled(true);
        renderersrer.setChartTitleTextSize(20);
        renderersrer.setDisplayValues(true);
        renderersrer.setShowLabels(true);
        
        SimpleSeriesRenderer r = renderersrer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Project budget",total), renderersrer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
    
        addComponent(BorderLayout.CENTER, c);
        
    }
     
    
    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }

  
}
