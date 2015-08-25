/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package gui.spinner;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Nakim
 */
public class NumberSpinner extends JSpinner implements ChangeListener
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    protected SpinnerNumberModel model;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public NumberSpinner(Comparable initialValue)
    {
        super();

        this.model = (SpinnerNumberModel)this.getModel();
        this.model.setValue(initialValue);

        // Allow only number and comma in spinner textfield
        JFormattedTextField txt = ((JSpinner.NumberEditor) this.getEditor()).getTextField();
        NumberFormatter numberFormatter = (NumberFormatter) txt.getFormatter();
        numberFormatter.setValueClass(Double.class);
        numberFormatter.setAllowsInvalid(false);
        numberFormatter.setCommitsOnValidEdit(true);

        this.addChangeListener(NumberSpinner.this);
    }

    public NumberSpinner()
    {
        this(0);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public methods">
    public int getIntegerValue()
    {
        return ((Number)this.getValue()).intValue();
    }

    public double getDoubleValue()
    {
        return ((Number)this.getValue()).doubleValue();
    }

    public void setValue(Comparable value)
    {
        this.model.setValue(value);
    }

    public void setMaximumValue(Comparable maximum)
    {
        this.model.setMaximum(maximum);
    }

    public void setMinimumValue(Comparable minimum)
    {
        this.model.setMinimum(minimum);
    }

    public void setStepSize(Number stepSize)
    {
        this.model.setStepSize(stepSize);
    }

    public synchronized void addValueChangedListener(ValueChangedListener listener)
    {
        this.listenerList.add(ValueChangedListener.class, listener);
    }

    public synchronized void removeValueChangedListener(ValueChangedListener listener)
    {
        this.listenerList.remove(ValueChangedListener.class, listener);
    }

    public synchronized ValueChangedListener[] getValueChangedListeners()
    {
        return this.listenerList.getListeners(ValueChangedListener.class);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Protected methods">
    protected void fireValueChanged(double oldValue, double newValue)
    {
        ValueChangedEvent event = new ValueChangedEvent(this, oldValue, newValue);

        for(ValueChangedListener listener : this.getValueChangedListeners())
            listener.valueChanged(event);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrided methods">
    @Override
    public void stateChanged(ChangeEvent e)
    {
        // Skip event if the value didn't change
        if(this.getPreviousValue() == this.getValue())
            return;

        this.fireValueChanged(((Number)this.getPreviousValue()).doubleValue(),
                              ((Number)this.getValue()).doubleValue());
    }
    //</editor-fold>
}
