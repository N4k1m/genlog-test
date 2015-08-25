/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package gui;

import dragdrop.DragDropEvent;
import dragdrop.DragDropListener;
import dragdrop.MultipleDragDropEvent;
import java.io.File;

/**
 *
 * @author Nakim
 */
public class MainFrameMediator implements DragDropListener
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    // TODO : Calculators here
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public MainFrameMediator()
    {
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Drag & drop event handlers">
    @Override
    public void fileDropped(DragDropEvent event)
    {
        System.out.println("File dropped : " + event.getFile().getName());
    }

    @Override
    public void filesDropped(MultipleDragDropEvent event)
    {
        for (File file : event.getFiles())
            System.out.println("File dropped : " + file.getName());
    }
    //</editor-fold>
}
