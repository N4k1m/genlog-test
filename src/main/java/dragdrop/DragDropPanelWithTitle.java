/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package dragdrop;

import java.awt.BorderLayout;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Nakim
 */
public class DragDropPanelWithTitle extends DragDropPanel
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    protected JLabel labelMessage;

    protected String title;
    protected String dropMessage;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public DragDropPanelWithTitle()
    {
        this("");
    }

    public DragDropPanelWithTitle(String title)
    {
        this(title, "");
    }

    public DragDropPanelWithTitle(String title, String dropMessage)
    {
        // GUI Configuration
        super();
        this.setLayout(new java.awt.BorderLayout());

        // Populate panel
        this.labelMessage = new JLabel(title);
        this.labelMessage.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(this.labelMessage, BorderLayout.CENTER);

        this.title = title;
        this.dropMessage = dropMessage.isEmpty() ? title : dropMessage;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrided methods">
    @Override
    public void dragEnter(DropTargetDragEvent dtde)
    {
        this.labelMessage.setText(this.dropMessage);
        super.dragEnter(dtde);
    }

    @Override
    public void dragExit(DropTargetEvent dte)
    {
        this.labelMessage.setText(this.title);
        super.dragExit(dte);
    }

    @Override
    public void drop(DropTargetDropEvent dtde)
    {
        this.labelMessage.setText(this.title);
        super.drop(dtde);
    }
    //</editor-fold>
}
