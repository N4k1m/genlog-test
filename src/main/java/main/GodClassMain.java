/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package main;

import gui.MainFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Nakim
 */
public class GodClassMain
{
    public static void main(String[] args)
    {
        //<editor-fold defaultstate="collapsed" desc="Change look and feel to Nimbus if available">
        try
        {
            String className = getLookAndFeelClassName("Nimbus");
            UIManager.setLookAndFeel(className);
        }
        catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            System.err.println(ex);
        }
        //</editor-fold>

        MainFrame mainFrame = new MainFrame();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    //<editor-fold defaultstate="collapsed" desc="Get all "look and feel" available on this OS">
    public static String getLookAndFeelClassName(String nameSnippet)
    {
        UIManager.LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
        for(UIManager.LookAndFeelInfo info : plafs)
            if (info.getName().contains(nameSnippet))
                return info.getClassName();

        return null;
    }
    //</editor-fold>
}
