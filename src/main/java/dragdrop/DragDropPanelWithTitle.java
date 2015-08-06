/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package dragdrop;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Nakim
 */
public class DragDropPanelWithTitle extends DragDropPanel
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    private JLabel labelTitle;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public DragDropPanelWithTitle()
    {
        this("");
    }

    public DragDropPanelWithTitle(String title)
    {
        // GUI Configuration
        super();
        this.setLayout(new java.awt.BorderLayout());

        // Populate panel
        this.labelTitle = new JLabel(title);
        this.labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(this.labelTitle, BorderLayout.CENTER);
    }
    //</editor-fold>
}
