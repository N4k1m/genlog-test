/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package gui;

import godclass.GodClassMediator;
import dragdrop.DragDropPanelWithTitle;
import godclass.ATFDLimitSpinner;
import godclass.HorizontalAlignedColoredLabel;
import godclass.MCCLimitSpinner;
import godclass.TCCLimitSpinner;
import godclass.WMCLimitSpinner;
import java.awt.Dimension;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 *
 * @author Nakim
 */
public class MainFrame extends javax.swing.JFrame
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    // Header
    private DragDropPanelWithTitle dragDropPanel;

    // Concret Mediator
    private GodClassMediator mediator;

    // Concret Colleagues
    private ATFDLimitSpinner atfdLimitSpinner;
    private HorizontalAlignedColoredLabel atfdTFLabel;
    private HorizontalAlignedColoredLabel atfdValueLabel;

    private WMCLimitSpinner wmcLimitSpinner;
    private HorizontalAlignedColoredLabel wmcTFLabel;
    private HorizontalAlignedColoredLabel wmcValueLabel;

    private MCCLimitSpinner mccLimitSpinner;
    private HorizontalAlignedColoredLabel mccTFLabel;
    private HorizontalAlignedColoredLabel mccValueLabel;

    private TCCLimitSpinner tccLimitSpinner;
    private HorizontalAlignedColoredLabel tccTFLabel;
    private HorizontalAlignedColoredLabel tccValueLabel;

    private HorizontalAlignedColoredLabel godClassTFLabel;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public MainFrame()
    {
        this("Génie Logiciel : Détecteur de classes God");
    }

    public MainFrame(String title)
    {
        // GUI Configuration
        this.initComponents();
        this.setTitle(title);

        // Redirect the system output to a TextArea
        /*TextAreaOutputStream toas = TextAreaOutputStream.getInstance(
            this.textAreaOutput);
            */

        // Mediator
        this.mediator = new GodClassMediator();

        // Colleagues
        this.atfdLimitSpinner = new ATFDLimitSpinner(this.mediator);
        this.atfdValueLabel   = new HorizontalAlignedColoredLabel(this.mediator, "ATFD ?");
        this.atfdTFLabel      = new HorizontalAlignedColoredLabel(this.mediator, "TRUE or FALSE ?");

        this.wmcLimitSpinner = new WMCLimitSpinner(this.mediator);
        this.wmcValueLabel   = new HorizontalAlignedColoredLabel(this.mediator, "WMC ?");
        this.wmcTFLabel      = new HorizontalAlignedColoredLabel(this.mediator, "TRUE or FALSE ?");

        this.mccLimitSpinner = new MCCLimitSpinner(this.mediator);
        this.mccValueLabel   = new HorizontalAlignedColoredLabel(this.mediator, "MCC ?");
        this.mccTFLabel      = new HorizontalAlignedColoredLabel(this.mediator, "TRUE or FALSE ?");

        this.tccLimitSpinner = new TCCLimitSpinner(this.mediator);
        this.tccValueLabel   = new HorizontalAlignedColoredLabel(this.mediator, "TCC ?");
        this.tccTFLabel      = new HorizontalAlignedColoredLabel(this.mediator, "TRUE or FALSE ?");

        this.godClassTFLabel = new HorizontalAlignedColoredLabel(this.mediator, "TRUE or FALSE ?");

        // Register labels
        this.mediator.registerATFDValueLabel(this.atfdValueLabel);
        this.mediator.registerATFDTFLabel(this.atfdTFLabel);
        this.mediator.registerWMCValueLabel(this.wmcValueLabel);
        this.mediator.registerWMCTFLabel(this.wmcTFLabel);
        this.mediator.registerMCCValueLabel(this.mccValueLabel);
        this.mediator.registerMCCTFLabel(this.mccTFLabel);
        this.mediator.registerTCCValueLabel(this.tccValueLabel);
        this.mediator.registerTCCTFLabel(this.tccTFLabel);
        this.mediator.registerGodClassTFLabel(this.godClassTFLabel);

        // Populate "GodClass" panel
        this.panelGodClass.add(this.atfdLimitSpinner, new AbsoluteConstraints(110, 70, 70, -1), 0);
        this.panelGodClass.add(this.atfdValueLabel, new AbsoluteConstraints(20, 70, 70, 30), 0);
        this.panelGodClass.add(this.atfdTFLabel, new AbsoluteConstraints(205, 70, 160, 30), 0);

        this.panelGodClass.add(this.wmcLimitSpinner, new AbsoluteConstraints(110, 210, 70, -1), 0);
        this.panelGodClass.add(this.wmcValueLabel, new AbsoluteConstraints(20, 210, 70, 30), 0);
        this.panelGodClass.add(this.wmcTFLabel, new AbsoluteConstraints(200, 210, 170, 30), 0);

        this.panelGodClass.add(this.mccLimitSpinner, new AbsoluteConstraints(110, 280, 70, -1), 0);
        this.panelGodClass.add(this.mccValueLabel, new AbsoluteConstraints(20, 280, 70, 30), 0);
        this.panelGodClass.add(this.mccTFLabel, new AbsoluteConstraints(200, 280, 170, 30), 0);

        this.panelGodClass.add(this.tccLimitSpinner, new AbsoluteConstraints(110, 440, 70, -1), 0);
        this.panelGodClass.add(this.tccValueLabel, new AbsoluteConstraints(20, 440, 70, 30), 0);
        this.panelGodClass.add(this.tccTFLabel, new AbsoluteConstraints(200, 440, 160, 30), 0);

        this.panelGodClass.add(this.godClassTFLabel, new AbsoluteConstraints(640, 210, 170, 40), 0);

        // Create drop panel
        this.dragDropPanel = new DragDropPanelWithTitle(
            "Déposez un fichier ici", "Lachez maintenant !");
        this.dragDropPanel.addDragDropListener(this.mediator);
        this.dragDropPanel.setPreferredSize(new Dimension(0, 75));
        this.add(this.dragDropPanel, java.awt.BorderLayout.PAGE_START);

        // Adapte frame size
        this.pack();
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        tabbedPane = new javax.swing.JTabbedPane();
        panelGodClass = new javax.swing.JPanel();
        labelBackground = new javax.swing.JLabel();
        panelStatistics = new javax.swing.JPanel();
        panelConsole = new javax.swing.JPanel();
        buttonClearConsole = new javax.swing.JButton();
        scrollPaneTextAreaOutput = new javax.swing.JScrollPane();
        textAreaOutput = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        panelGodClass.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        panelGodClass.add(labelBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        tabbedPane.addTab("Analyseur de classe God", panelGodClass);
        tabbedPane.addTab("Statistiques", panelStatistics);

        panelConsole.setLayout(new java.awt.BorderLayout());

        buttonClearConsole.setText("Effacer");
        buttonClearConsole.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                buttonClearConsoleActionPerformed(evt);
            }
        });
        panelConsole.add(buttonClearConsole, java.awt.BorderLayout.PAGE_END);

        textAreaOutput.setEditable(false);
        scrollPaneTextAreaOutput.setViewportView(textAreaOutput);

        panelConsole.add(scrollPaneTextAreaOutput, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("Console", panelConsole);

        getContentPane().add(tabbedPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //<editor-fold defaultstate="collapsed" desc="Events handlers">
    private void buttonClearConsoleActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonClearConsoleActionPerformed
    {//GEN-HEADEREND:event_buttonClearConsoleActionPerformed
        this.textAreaOutput.setText(null);
    }//GEN-LAST:event_buttonClearConsoleActionPerformed
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Generated Widgets">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClearConsole;
    private javax.swing.JLabel labelBackground;
    private javax.swing.JPanel panelConsole;
    private javax.swing.JPanel panelGodClass;
    private javax.swing.JPanel panelStatistics;
    private javax.swing.JScrollPane scrollPaneTextAreaOutput;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTextArea textAreaOutput;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
