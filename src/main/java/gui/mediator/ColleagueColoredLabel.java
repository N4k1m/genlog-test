/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package gui.mediator;

import java.awt.Color;

/**
 *
 * @author Nakim
 */
public class ColleagueColoredLabel extends ColleagueLabel
{
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public ColleagueColoredLabel(Mediator mediator)
    {
        this(mediator, "");
    }

    public ColleagueColoredLabel(Mediator mediator, String text)
    {
        this(mediator, Color.BLACK, text);
    }

    public ColleagueColoredLabel(Mediator mediator, Color color, String text)
    {
        super(mediator, text);
        this.setForeground(color);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public methods">
    public void setColoredText(Color color, String text)
    {
        this.setForeground(color);
        this.setText(text);
    }

    public void setBlackText(String text)
    {
        this.setColoredText(Color.BLACK, text);
    }

    public void setRedText(String text)
    {
        this.setColoredText(Color.RED, text);
    }

    public void setGreenText(String text)
    {
        this.setColoredText(Color.GREEN, text);
    }
    //</editor-fold>
}
