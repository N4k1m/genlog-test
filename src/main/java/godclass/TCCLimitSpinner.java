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
public class TCCLimitSpinner extends ColleagueNumberSpinner
{
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public TCCLimitSpinner(GodClassMediator mediator, Comparable initialValue)
    {
        super(mediator, initialValue);
        this.setStepSize(0.1);

        mediator.registerTCCLimitSpinner(TCCLimitSpinner.this);
    }

    public TCCLimitSpinner(GodClassMediator mediator)
    {
        this(mediator, 0);
    }
    //</editor-fold>
}
