/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package gui.mediator;

import gui.spinner.NumberSpinner;

/**
 *
 * @author Nakim
 */
public abstract class ColleagueNumberSpinner extends NumberSpinner
                                             implements Colleague
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    protected Mediator mediator;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    protected ColleagueNumberSpinner(Mediator mediator)
    {
        this(mediator, 0.0);
    }
    
    protected ColleagueNumberSpinner(Mediator mediator, Comparable initialValue)
    {
        super(initialValue);
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
