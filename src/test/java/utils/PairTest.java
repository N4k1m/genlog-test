/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package utils;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Nakim
 */
public class PairTest
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    private Pair<Integer, Double> pair;
    private final Integer a;
    private final Double  b;
    //</editor-fold>

    public PairTest()
    {
        this.a = new Integer(1);
        this.b = new Double(4.2);
    }

    @Before
    public void setUp()
    {
        this.pair = new Pair<>(a, b);
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of create method, of class Pair.
     */
    @Test
    public void testCreate()
    {
        System.out.println("* PairTest: testCreate()");

        assertEquals(this.pair, Pair.create(a, b));
    }

    /**
     * Test of getFirst method, of class Pair.
     */
    @Test
    public void testGetFirst()
    {
        System.out.println("* PairTest: testGetFirst()");

        assertEquals(this.a, this.pair.getFirst());
        assertNotSame(this.b, this.pair.getFirst());
    }

    /**
     * Test of getSecond method, of class Pair.
     */
    @Test
    public void testGetSecond()
    {
        System.out.println("* PairTest: testGetSecond()");

        assertEquals(this.b, this.pair.getSecond());
        assertNotSame(this.a, this.pair.getSecond());
    }

    /**
     * Test of toString method, of class Pair.
     */
    @Test
    public void testToString()
    {
    }

    /**
     * Test of equals method, of class Pair.
     */
    @Test
    public void testEquals()
    {
    }
}
