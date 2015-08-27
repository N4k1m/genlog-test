/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package utils;

import java.util.List;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.TreeNode.DepthFirstIterator;

/**
 *
 * @author Nakim
 */
public class TreeNodeTest
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    private TreeNode<String> root;
    //</editor-fold>

    public TreeNodeTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
        this.root = new TreeNode<>("root");
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of addChildNode method, of class TreeNode.
     */
    @Test
    public void testAddChildNode()
    {
        System.out.println("* TreeNodeTest: testAddChildNode()");

        TreeNode<String> child = new TreeNode<>("child");

        assertTrue(this.root.addChildNode(child));
        assertFalse(this.root.addChildNode(child)); // Child already registered
    }

    /**
     * Test of forgetParent method, of class TreeNode.
     */
    @Test
    public void testForgetParent()
    {
        System.out.println("* TreeNodeTest: testForgetParent()");

        TreeNode<String> child = new TreeNode<>("child");
        this.root.addChildNode(child);

        assertTrue(child.forgetParent());
        assertTrue(child.forgetParent()); // Child has no parent
        assertNull(child.getParent());
    }

    /**
     * Test of removeChild method, of class TreeNode.
     */
    @Test
    public void testRemoveChild()
    {
        System.out.println("* TreeNodeTest: testRemoveChild()");

        TreeNode<String> child = new TreeNode<>("child");
        this.root.addChildNode(child);

        assertTrue(this.root.removeChild(child));
        assertFalse(this.root.removeChild(child));
        assertNull(child.getParent());
    }

    /**
     * Test of removeChildAt method, of class TreeNode.
     */
    @Test(expected=IndexOutOfBoundsException.class)
    public void testRemoveChildAt()
    {
        System.out.println("* TreeNodeTest: testRemoveChildAt()");

        TreeNode<String> child = new TreeNode<>("child");
        this.root.addChildNode(child);

        assertEquals(child, this.root.removeChildAt(0));

        this.root.removeChildAt(42); // Throws IndexOutOfBoundsException
    }

    /**
     * Test of removeChildren method, of class TreeNode.
     */
    @Test
    public void testRemoveChildren()
    {
        System.out.println("* TreeNodeTest: testRemoveChildAt()");

        this.root.addChildNode(new TreeNode<>("child1"));
        this.root.addChildNode(new TreeNode<>("child2"));
        this.root.addChildNode(new TreeNode<>("child3"));

        this.root.removeChildren();

        assertTrue(this.root.getChildren().isEmpty());
    }

    /**
     * Test of deepCopy method, of class TreeNode.
     */
    @Test
    public void testDeepCopy()
    {
        System.out.println("* TreeNodeTest: testDeepCopy()");

        this.root.addChildNode(new TreeNode<>("child1"));
        this.root.addChildNode(new TreeNode<>("child2"));
        this.root.addChildNode(new TreeNode<>("child3"));

        TreeNode<String> rootCopy = this.root.deepCopy();

        assertEquals(this.root.childrenCount(), rootCopy.childrenCount());
    }

    /**
     * Test of getLevel method, of class TreeNode.
     */
    @Test
    public void testGetLevel()
    {
        System.out.println("* TreeNodeTest: testGetLevel()");

        TreeNode<String> childLvl3 = new TreeNode<>("childLvl3");

        TreeNode<String> childLvl2 = new TreeNode<>("childLvl2");
        childLvl2.addChildNode(childLvl3);

        TreeNode<String> childLvl1 = new TreeNode<>("childLvl1");
        childLvl1.addChildNode(childLvl2);

        this.root.addChildNode(childLvl1);

        assertTrue(childLvl3.getLevel() == 3);
        assertTrue(childLvl2.getLevel() == 2);
        assertTrue(childLvl1.getLevel() == 1);
        assertTrue(this.root.getLevel() == 0);
    }

    /**
     * Test of getChildren method, of class TreeNode.
     */
    @Test
    public void testGetChildren()
    {
        System.out.println("* TreeNodeTest: testGetChildren()");

        TreeNode<String> child1 = new TreeNode<>("child1");
        TreeNode<String> child2 = new TreeNode<>("child2");
        TreeNode<String> child3 = new TreeNode<>("child3");

        child2.addChildNode(child3);


        this.root.addChildNode(child1);
        this.root.addChildNode(child2);

        List<TreeNode<String>> children = this.root.getChildren();

        assertTrue(children.size() == 2);
        assertTrue(children.contains(child1));
        assertTrue(children.contains(child2));
        assertFalse(children.contains(child3));
    }

    /**
     * Test of getLeaves method, of class TreeNode.
     */
    @Test
    public void testGetLeaves()
    {
        System.out.println("* TreeNodeTest: testGetLeaves()");

        TreeNode<String> child1 = new TreeNode<>("child1");
        TreeNode<String> child2 = new TreeNode<>("child2");
        TreeNode<String> child3 = new TreeNode<>("child3");

        child1.addChildNode(child2);
        child1.addChildNode(child3);


        this.root.addChildNode(child1);

        List<TreeNode<String>> children = this.root.getLeaves();

        assertTrue(children.size() == 2);
        assertTrue(children.contains(child2));
        assertTrue(children.contains(child3));
        assertFalse(children.contains(child1));
    }

    /**
     * Test of childrenCount method, of class TreeNode.
     */
    @Test
    public void testChildrenCount()
    {
        System.out.println("* TreeNodeTest: testChildrenCount()");

        TreeNode<String> child1 = new TreeNode<>("child1");
        TreeNode<String> child2 = new TreeNode<>("child2");
        TreeNode<String> child3 = new TreeNode<>("child3");

        this.root.addChildNode(child1);
        this.root.addChildNode(child2);
        this.root.addChildNode(child3);

        assertTrue(this.root.childrenCount() == 3);
    }

    /**
     * Test of getParent method, of class TreeNode.
     */
    @Test
    public void testGetParent()
    {
        System.out.println("* TreeNodeTest: testGetParent()");

        TreeNode<String> child1 = new TreeNode<>("child1");
        TreeNode<String> child2 = new TreeNode<>("child2");

        child1.addChildNode(child2);
        this.root.addChildNode(child1);

        assertEquals(child1.getParent(), this.root);
        assertNotSame(child2.getParent(), this.root);
        assertEquals(child2.getParent(), child1);
    }

    /**
     * Test of getValue method, of class TreeNode.
     */
    @Test
    public void testGetAndSetValue()
    {
        System.out.println("* TreeNodeTest: testGetAndSetValue()");

        assertEquals("root", this.root.getValue());

        this.root.setValue("ROOT");
        assertNotSame("root", this.root.getValue());
    }

    /**
     * Test of isLeaf method, of class TreeNode.
     */
    @Test
    public void testIsLeaf()
    {
        System.out.println("* TreeNodeTest: testIsLeaf()");

        TreeNode<String> leaf1 = new TreeNode<>("leaf1");
        TreeNode<String> leaf2 = new TreeNode<>("leaf2");

        TreeNode<String> node = new TreeNode<>("node");
        node.addChildNode(leaf1);
        node.addChildNode(leaf2);

        this.root.addChildNode(node);

        assertTrue(leaf1.isLeaf());
        assertTrue(leaf2.isLeaf());
        assertFalse(node.isLeaf());
        assertFalse(node.isLeaf());
    }

    @Test(expected=NoSuchElementException.class)
    public void testDepthFirstIteratorNext()
    {
        System.out.println("* TreeNodeTest: testDepthFirstIteratorNext()");

        DepthFirstIterator it = root.new DepthFirstIterator();

        while(it.hasNext())
            it.next();

        TreeNode<String> unExistingNode = it.next();
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testDepthFirstIteratorRemove()
    {
        System.out.println("* TreeNodeTest: testDepthFirstIteratorRemove()");

        DepthFirstIterator it = root.new DepthFirstIterator();
        it.remove();
    }
}
