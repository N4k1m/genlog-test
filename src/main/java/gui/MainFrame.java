/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package gui;

import dragdrop.DragDropPanelWithTitle;
import java.awt.Dimension;
import utils.TextAreaOutputStream;

/**
 *
 * @author Nakim
 */
public class MainFrame extends javax.swing.JFrame
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    // Header
    private DragDropPanelWithTitle dragDropPanel;
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
        TextAreaOutputStream toas = TextAreaOutputStream.getInstance(
            this.textAreaOutput);

        // Create drop panel
        this.dragDropPanel = new DragDropPanelWithTitle("Déposer des fichiers ici ...");
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
        spinnerATFDLimit = new javax.swing.JSpinner();
        spinnerWMCMethodCountLimit = new javax.swing.JSpinner();
        spinnerWMCLimit = new javax.swing.JSpinner();
        spinnerTCCLimit = new javax.swing.JSpinner();
        labelATFDValue = new javax.swing.JLabel();
        labelATFDTF = new javax.swing.JLabel();
        labelWMCMethodCountValue = new javax.swing.JLabel();
        labelWMCValue = new javax.swing.JLabel();
        labelWMCTF = new javax.swing.JLabel();
        labelTCCValue = new javax.swing.JLabel();
        labelTCCTF = new javax.swing.JLabel();
        labelGodClassTF = new javax.swing.JLabel();
        labelBackground = new javax.swing.JLabel();
        panelStatistics = new javax.swing.JPanel();
        panelConsole = new javax.swing.JPanel();
        buttonClearConsole = new javax.swing.JButton();
        scrollPaneTextAreaOutput = new javax.swing.JScrollPane();
        textAreaOutput = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        panelGodClass.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spinnerATFDLimit.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        panelGodClass.add(spinnerATFDLimit, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 70, -1));

        spinnerWMCMethodCountLimit.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        panelGodClass.add(spinnerWMCMethodCountLimit, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 70, -1));

        spinnerWMCLimit.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        panelGodClass.add(spinnerWMCLimit, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, 70, -1));

        spinnerTCCLimit.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), Double.valueOf(0.0d), null, Double.valueOf(0.1d)));
        panelGodClass.add(spinnerTCCLimit, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, 70, -1));

        labelATFDValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelATFDValue.setText("ATFD ?");
        panelGodClass.add(labelATFDValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 70, 30));

        labelATFDTF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelATFDTF.setText("TRUE or FALSE ?");
        panelGodClass.add(labelATFDTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 70, 160, 30));

        labelWMCMethodCountValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelWMCMethodCountValue.setText("count ?");
        panelGodClass.add(labelWMCMethodCountValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 70, 30));

        labelWMCValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelWMCValue.setText("WMC ?");
        panelGodClass.add(labelWMCValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 70, 40));

        labelWMCTF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelWMCTF.setText("TRUE or FALSE ?");
        panelGodClass.add(labelWMCTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 170, 40));

        labelTCCValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTCCValue.setText("TCC ?");
        panelGodClass.add(labelTCCValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 70, 30));

        labelTCCTF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTCCTF.setText("TRUE or FALSE ?");
        panelGodClass.add(labelTCCTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 440, 160, 30));

        labelGodClassTF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelGodClassTF.setText("TRUE or FALSE ?");
        panelGodClass.add(labelGodClassTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 210, 170, 40));

        labelBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background.png"))); // NOI18N
        panelGodClass.add(labelBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        tabbedPane.addTab("Analyseur de classe God", panelGodClass);
        tabbedPane.addTab("Statistiques", panelStatistics);

        panelConsole.setLayout(new java.awt.BorderLayout());

        buttonClearConsole.setText("Effacer");
        panelConsole.add(buttonClearConsole, java.awt.BorderLayout.PAGE_END);

        textAreaOutput.setEditable(false);
        scrollPaneTextAreaOutput.setViewportView(textAreaOutput);

        panelConsole.add(scrollPaneTextAreaOutput, java.awt.BorderLayout.CENTER);

        tabbedPane.addTab("Console", panelConsole);

        getContentPane().add(tabbedPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //<editor-fold defaultstate="collapsed" desc="Generated Widgets">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClearConsole;
    private javax.swing.JLabel labelATFDTF;
    private javax.swing.JLabel labelATFDValue;
    private javax.swing.JLabel labelBackground;
    private javax.swing.JLabel labelGodClassTF;
    private javax.swing.JLabel labelTCCTF;
    private javax.swing.JLabel labelTCCValue;
    private javax.swing.JLabel labelWMCMethodCountValue;
    private javax.swing.JLabel labelWMCTF;
    private javax.swing.JLabel labelWMCValue;
    private javax.swing.JPanel panelConsole;
    private javax.swing.JPanel panelGodClass;
    private javax.swing.JPanel panelStatistics;
    private javax.swing.JScrollPane scrollPaneTextAreaOutput;
    private javax.swing.JSpinner spinnerATFDLimit;
    private javax.swing.JSpinner spinnerTCCLimit;
    private javax.swing.JSpinner spinnerWMCLimit;
    private javax.swing.JSpinner spinnerWMCMethodCountLimit;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTextArea textAreaOutput;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
