/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package dragdrop;

import java.util.EventListener;

/**
 *
 * @author Nakim
 */
public interface DragDropListener extends EventListener
{
    void fileDropped(DragDropEvent event);
    void filesDropped(MultipleDragDropEvent event);
}
