/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package metrics.calculators;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Nakim
 */
public class WMCCalculatorTest
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    private WMCCalculator calculator;
    //</editor-fold>

    public WMCCalculatorTest()
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
        this.calculator = new WMCCalculator();
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of all MethodCount methods, of class WMCCalculator.
     */
    @Test
    public void testMethodCount()
    {
        System.out.println("* WMCCalculatorTest: testMethodCount()");

        calculator.setMethodCount(4);
        assertTrue(4 == calculator.getMethodCount());

        calculator.increaseMethodCount();
        assertTrue(5 == calculator.getMethodCount());
    }

    /**
     * Test of reset method, of class WMCCalculator.
     */
    @Test
    public void testReset()
    {
        System.out.println("* WMCCalculatorTest: testReset()");

        calculator.setMethodCount(4);
        assertTrue(4 == calculator.getMethodCount());

        calculator.reset();
        assertTrue(0 == calculator.getMethodCount());
    }

    /**
     * Test of calculate method, of class WMCCalculator.
     */
    @Test
    public void testCalculate()
    {
        System.out.println("* WMCCalculatorTest: testCalculate()");

        try
        {
            File fileWMC7 = new File(getClass().getResource("/code/WMC14.java").toURI());

            FileInputStream in = new FileInputStream(fileWMC7);
            CompilationUnit cu;

            // Parse the file
            cu = JavaParser.parse(in);

            this.calculator.calculate(cu);
            assertTrue(this.calculator.getMetric() == 14.0);
        }
        catch (URISyntaxException | FileNotFoundException | ParseException ex)
        {
            System.out.println("WMCCalculatorTest : " + ex.getMessage());
        }
    }
}
