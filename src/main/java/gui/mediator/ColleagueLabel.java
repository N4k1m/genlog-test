/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package gui.mediator;

import javax.swing.JLabel;

/**
 *
 * @author Nakim
 */
public class ColleagueLabel extends JLabel
                            implements Colleague
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    protected Mediator mediator;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public ColleagueLabel(Mediator mediator)
    {
        this(mediator, "");
    }

    public ColleagueLabel(Mediator mediator, String text)
    {
        super(text);
        this.mediator = mediator;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public methods">
    @Override
    public Mediator getMediator()
    {
        return this.mediator;
    }
    //</editor-fold>
}
