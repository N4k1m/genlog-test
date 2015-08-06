/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package dragdrop;

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
        super();

        // Populate panel
        this.labelTitle = new JLabel(title, SwingConstants.CENTER);
        this.add(this.labelTitle);
    }
    //</editor-fold>
}
