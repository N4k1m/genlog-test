/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package dragdrop;

import java.io.File;
import java.util.EventObject;

/**
 *
 * @author Nakim
 */
public class DragDropEvent extends EventObject
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    protected File file;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public DragDropEvent(Object source, File file)
    {
        super(source);
        
        this.file = file;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public methods">
    public File getFile()
    {
        return this.file;
    }
    //</editor-fold>
}
