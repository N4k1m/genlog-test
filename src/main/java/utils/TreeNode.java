/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 *
 * @author Nakim
 */
public class TreeNode<N>
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    protected TreeNode<N> parent;
    protected List<TreeNode<N>> children;
    protected N value;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public TreeNode(N value)
    {
        this.parent = null;
        this.children = new ArrayList<>();
        this.value = value;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public methods">
    public void remove()
    {
        if(this.parent != null)
            parent.removeChild(this);
    }

    private void removeChild(TreeNode<N> child)
    {
        if (this.children.contains(child))
            this.children.remove(child);
    }

    public void addChildNode(TreeNode<N> child)
    {
        child.parent = this;
        if (!children.contains(child))
            children.add(child);
    }

    public TreeNode<N> deepCopy()
    {
        TreeNode<N> newNode = new TreeNode(this.value);

        for (TreeNode<N> child : this.children)
            newNode.addChildNode(child.deepCopy());

        return newNode;
    }

    public int getLevel()
    {
        int level = 0;
        TreeNode p = this.parent;

        while(p != null)
        {
            ++level;
            p = p.parent;
        }

        return level;
    }

    public List<TreeNode<N>> getChildren()
    {
        return this.children;
    }

    public List<TreeNode<N>> getLeaves()
    {
        List<TreeNode<N>> leaves = new ArrayList<>();

        DepthFirstIterator it = this.new DepthFirstIterator();
        while (it.hasNext())
        {
            TreeNode<N> node = it.next();
            if(node.isLeaf())
                leaves.add(node);
        }

        return leaves;
    }

    public int childrenCount()
    {
        return this.children.size();
    }

    public TreeNode<N> getParent()
    {
        return this.parent;
    }

    public N getValue()
    {
        return this.value;
    }

    public void setValue(N value)
    {
        this.value = value;
    }

    public boolean isLeaf()
    {
        return this.children.isEmpty();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Iterator">
    public class DepthFirstIterator implements Iterator<TreeNode<N>>
    {
        //<editor-fold defaultstate="collapsed" desc="Variables declaration">
        protected Stack<TreeNode<N>> fringe;
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Constructors">
        public DepthFirstIterator()
        {
            this.fringe = new Stack<>();
            fringe.push(TreeNode.this);
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Overrided methods">
        @Override
        public boolean hasNext()
        {
            return !this.fringe.isEmpty();
        }

        @Override
        public TreeNode<N> next()
        {
            if(!this.hasNext())
              throw new NoSuchElementException("Tree ran out of elements");

            TreeNode<N> node = fringe.pop();

            for(TreeNode<N> childNode : node.children)
                this.fringe.push(childNode);

            return node;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        //</editor-fold>
    }
    //</editor-fold>
}
