/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package dragdrop;

/**
 *
 * @author Nakim
 */
public abstract class DragDropAdapter implements DragDropListener
{
    @Override
    public void fileDropped(DragDropEvent event)
    {
        // Nothing to do here ...
    }

    @Override
    public void filesDropped(MultipleDragDropEvent event)
    {
        // Nothing to do here ...
    }
}
