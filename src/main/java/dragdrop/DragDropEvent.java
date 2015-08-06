/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package dragdrop;

import java.io.File;

/**
 *
 * @author Nakim
 */
public class DragDropEvent
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    protected File file;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public DragDropEvent(File file)
    {
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
