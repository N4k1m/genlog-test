/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package gui.spinner;

import java.util.EventListener;

/**
 *
 * @author Nakim
 */
public interface ValueChangedListener extends EventListener
{
    public void valueChanged(ValueChangedEvent event);
}
