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
public class ATFDLimitSpinner extends ColleagueNumberSpinner
{
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public ATFDLimitSpinner(GodClassMediator mediator, Comparable initialValue)
    {
        super(mediator, initialValue);
        this.setStepSize(1);

        mediator.registerATFDLimitSpinner(ATFDLimitSpinner.this);
    }

    public ATFDLimitSpinner(GodClassMediator mediator)
    {
        this(mediator, 0);
    }
    //</editor-fold>
}
