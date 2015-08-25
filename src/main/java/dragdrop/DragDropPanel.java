/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package dragdrop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.UIManager;
import javax.swing.event.EventListenerList;

/**
 *
 * @author Nakim
 */
public class DragDropPanel extends javax.swing.JPanel
                           implements DropTargetListener
{
    //<editor-fold defaultstate="collapsed" desc="Variables declarations">
    protected final EventListenerList listeners = new EventListenerList();

    protected DropTarget dropTarget;
    protected boolean dragOver = false;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public DragDropPanel()
    {
        this.dropTarget = new DropTarget(this, this);
        this.dragOver = false;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public methods">
    public void addDragDropListener(DragDropListener listener)
    {
        this.listeners.add(DragDropListener.class, listener);
    }

    public void removeDragDropListener(DragDropListener listener)
    {
        this.listeners.remove(DragDropListener.class, listener);
    }

    public DragDropListener[] getDragDropListeners()
    {
        return this.listeners.getListeners(DragDropListener.class);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Protected methods">
    protected void fireFileDropped(File file)
    {
        DragDropEvent event = null;
        for (DragDropListener listener : this.getDragDropListeners())
        {
            if (event == null)
                event = new DragDropEvent(this, file);

            listener.fileDropped(event);
        }
    }

    protected void fireFilesDropped(List<File> files)
    {
        if (files.isEmpty())
            return;

        if (files.size() == 1)
        {
            this.fireFileDropped(files.get(0));
        }
        else
        {
            MultipleDragDropEvent event = null;
            for (DragDropListener listener : this.getDragDropListeners())
            {
                if (event == null)
                    event = new MultipleDragDropEvent(this, files);

                listener.filesDropped(event);
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrided methods">
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (this.dragOver)
            this.setBackground(new Color(209, 245, 189));
        else
            this.setBackground(UIManager.getColor("Panel.background"));
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="DropTargetListener overrided methods">
    @Override
    public void dragEnter(DropTargetDragEvent dtde)
    {
        this.dragOver = true;
        repaint();
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde)
    {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde)
    {
    }

    @Override
    public void dragExit(DropTargetEvent dte)
    {
        this.dragOver = false;
        repaint();
    }

    @Override
    public void drop(DropTargetDropEvent dtde)
    {
        // Accept copy drops
        dtde.acceptDrop(DnDConstants.ACTION_COPY);

        // Update background color
        this.dragOver = false;
        repaint();

        // Get the transfer which can provide the dropped item data
        Transferable transferable = dtde.getTransferable();

        // Get the data formats of the dropped item
        DataFlavor[] flavors = transferable.getTransferDataFlavors();

        // Loop through the flavors
        for (DataFlavor flavor : flavors)
        {
            try
            {
                // If the drop items are files
                if (flavor.isFlavorJavaFileListType())
                {
                    // Get all of the dropped files
                    List transfertData = (List) transferable.getTransferData(flavor);

                    List<File> files = new ArrayList<>();
                    Iterator iter = transfertData.iterator();
                    while (iter.hasNext())
                        files.add((File)iter.next());

                    this.fireFilesDropped(files);
                    /*
                    // Loop them through
                    files.stream().forEach((file) ->
                    {
                        // Print out the file path
                        System.out.println("File path is '" + file.getPath() + "'.");
                    });
                    */
                }

            }
            catch (UnsupportedFlavorException | IOException e)
            {
                e.printStackTrace();
            }
        }

        // Inform that the drop is complete
        dtde.dropComplete(true);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Static">
    private static final long serialVersionUID = 1L;
    //</editor-fold>
}
