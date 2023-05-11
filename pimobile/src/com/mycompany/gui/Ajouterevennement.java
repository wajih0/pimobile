/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Evennement;
import com.mycompany.services.ServiceEvennement;

/**
 *
 * @author wajihbenhmida
 */
public class Ajouterevennement extends BaseForm {
        Form current ;
      public Ajouterevennement(Resources res) {
         super("Newsfeed", new FlowLayout(Component.CENTER));
         Toolbar tb = new Toolbar(true);
         current = this;
          setToolbar(tb);
          getTitleArea().setUIID("ggggggg");
          setTitle("Ajouter Formation");
          getContentPane().setScrollVisible(false);
          
            
        
        tb.addSearchCommand(e ->  {
            
        });
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("OIP.jpg"),"","",res);
        
        ////
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Ajouter Event", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Consulter List", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        mesListes.addActionListener((e)->{
                  new Ajouterevennement(res).show();
        });
        bindButtonSelection(liste, arrow);
        partage.addActionListener((e)->{
                  new Listevennement(res).show();
        });
       
        
       
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
        ////
          
          
         
          
          
          
          TextField nom= new TextField("","Entrer Titre");
          nom.setUIID("TextFieldBlack");
          addStringValue("nom",nom);
          
          TextField description= new TextField("","Entrer description");
          description.setUIID("TextFieldBlack");
          addStringValue("description",description);
          
          TextField lieu= new TextField("","Entrer type");
          lieu.setUIID("TextFieldBlack");
          addStringValue("lieu",lieu);
          
            TextField prix= new TextField("","Entrer debut");
          prix.setUIID("TextFieldBlack");
          addStringValue("prix",prix);
          
          TextField date= new TextField("","Entrer pays");
          date.setUIID("TextFieldBlack");
          addStringValue("date",date);
         
        
          
          TextField nb_participants= new TextField("","Entrer fin");
          nb_participants.setUIID("TextFieldBlack");
          addStringValue("nb_participants",nb_participants);
          
          TextField type_evenement= new TextField("","Entrer fin");
          type_evenement.setUIID("TextFieldBlack");
          addStringValue("type_evenement",type_evenement);
          
          Button btnAdd = new Button("Ajouter");
          addStringValue("",btnAdd);
          
          btnAdd.addActionListener((ActionEvent e)->{
              try{
                  if(nom.getText() == "" ||description.getText() == ""||lieu.getText() == ""||prix.getText() == ""||date.getText() ==""||nb_participants.getText() ==""||type_evenement.getText() ==""){
                      Dialog.show("Verfier vous donnee","","Annuler","OK");
                      
                  }else{
                      InfiniteProgress ip = new InfiniteProgress();
                       final Dialog iDialog = ip.showInfiniteBlocking();
                        Evennement f = new Evennement(
                         String.valueOf(nom.getText()).toString(),
                                  String.valueOf(description.getText()).toString(),
                            String.valueOf(lieu.getText()).toString(),
                                                            String.valueOf(prix.getText()).toString(),

                                                        String.valueOf(date.getText()).toString(),

                            String.valueOf(nb_participants.getText()).toString(),
                                                        String.valueOf(type_evenement.getText()).toString()
                        );
                      System.out.println("data foramtion = "+f);
                      
                      
                      ServiceEvennement.getInstance().ajoutEvent(f);
                      iDialog.dispose(); 
         
                    new Listevennement(res).show();
                      
                      refreshTheme();     
                  }
              }catch(Exception ex){
                  ex.printStackTrace();
                  
              }
              
              
              });

          
      
      
      }

   private void addStringValue(String s, Component v) {
    Container cnt = new Container(new BoxLayout(BoxLayout.X_AXIS));
    cnt.add(new Label(s, "PaddedLabel"));
    cnt.add(v);
    add(cnt);
    add(createLineSeparator(0xeeeeee));
}

  private void addTab(Tabs swipe, Label spacer , Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        if(image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
          if(image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        
        
        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2 ) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overLay = new Label("","ImageOverlay");
        
        
        Container page1 = 
                LayeredLayout.encloseIn(
                imageScale,
                        overLay,
                        BorderLayout.south(
                        BoxLayout.encloseY(
                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                        )
                    )
                );
        
        swipe.addTab("",res.getImage("OIP.jpg"), page1);
        
        
        
        
    }
  
        
    public void bindButtonSelection(Button btn , Label l ) {
        
        btn.addActionListener(e-> {
        if(btn.isSelected()) {
            updateArrowPosition(btn,l);
        }
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
        
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth()  / 2  - l.getWidth() / 2 );
        l.getParent().repaint();
    }

   
   

   
    
}
