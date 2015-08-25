/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package gui.spinner;

import java.util.EventObject;

/**
 *
 * @author Nakim
 */
public class ValueChangedEvent extends EventObject
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    protected double value;
    protected double oldValue;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public ValueChangedEvent(Object source, double oldValue, double value)
    {
        super(source);

        this.value = value;
        this.oldValue = oldValue;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public methods">
    public double getDoubleValue()
    {
        return this.value;
    }

    public int getIntegerValue()
    {
        return (int)this.getDoubleValue();
    }

    public double getOldDoubleValue()
    {
        return this.oldValue;
    }

    public int getOldIntegerValue()
    {
        return (int)this.getOldDoubleValue();
    }
    //</editor-fold>
}
