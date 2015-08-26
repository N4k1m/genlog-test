/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package godclass;

import gui.mediator.ColleagueNumberSpinner;

/**
 *
 * @author Nakim
 */
public class MCCLimitSpinner extends ColleagueNumberSpinner
{
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public MCCLimitSpinner(GodClassMediator mediator, Comparable initialValue)
    {
        super(mediator, initialValue);
        this.setStepSize(1);

        mediator.registerMCCLimitSpinner(MCCLimitSpinner.this);
    }

    public MCCLimitSpinner(GodClassMediator mediator)
    {
        this(mediator, 0);
    }
    //</editor-fold>
}
