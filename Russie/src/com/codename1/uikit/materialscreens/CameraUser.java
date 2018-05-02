/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.charts.ChartComponent;


import com.codename1.io.CharArrayReader;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import com.company.Entites.Bet;
import com.company.Entites.User;
import com.company.utils.Local;
import com.mycompagny.Service.BetService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nader
 */
public class CameraUser extends Form {
    BetService bt = new BetService();
       Local dblocal = new Local();
    User u = dblocal.getUser();
    List<Bet> listBet = new ArrayList<Bet>();
     public CameraUser(Resources res) {
     super(new LayeredLayout());
       Toolbar.setGlobalToolbar(true);
       
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Label tit = new Label("Resultat Matchs ", "Title");
        // tit.getAllStyles().setFgColor(0xE12336);
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
       
        
       // add(createPieChartForm());
       Bet betGain = new Bet(bt.getbetgain(u.getId()),"Gain");
       Bet betPerte = new Bet(bt.getbetPerte(u.getId()),"Perte");
       Bet betCours = new Bet(bt.getbetCours(u.getId()),"Traite");
         listBet.add(betGain);
         listBet.add(betPerte);
         listBet.add(betCours);
          
         add(createPieChartForm());
       }
     
      private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLabelsColor(ColorUtil.BLACK);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
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

    public Form createPieChartForm() {

        // Generate the values
       
       ArrayList<Bet> total = new ArrayList<Bet>();
    
        total=bt.getList2(u.getId());
        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
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
        Form f = new Form("Budget");
        f.setLayout(new BorderLayout());
        f.addComponent(BorderLayout.CENTER, c);
        return f;
    }
     
     
}


