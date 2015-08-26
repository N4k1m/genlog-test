/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package godclass;

import gui.mediator.ColleagueColoredLabel;
import java.awt.Color;
import javax.swing.SwingConstants;

/**
 *
 * @author Nakim
 */
public class HorizontalAlignedColoredLabel extends ColleagueColoredLabel
{

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public HorizontalAlignedColoredLabel(GodClassMediator mediator)
    {
        this(mediator, "");
    }

    public HorizontalAlignedColoredLabel(GodClassMediator mediator, String text)
    {
        this(mediator, Color.BLACK, text);
    }

    public HorizontalAlignedColoredLabel(GodClassMediator mediator, Color color, String text)
    {
        super(mediator, color, text);

        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
    //</editor-fold>

}
