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
import metrics.exceptions.MCCException;
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
public class MCCCalculatorTest
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    private final MCCCalculator calculator;
    //</editor-fold>

    public MCCCalculatorTest()
    {
        this.calculator = new MCCCalculator();
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
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of all CurrentMethodCyclomaticComplexity method, of class MCCCalculator.
     */
    @Test
    public void testCurrentMethodCyclomaticComplexity()
    {
        System.out.println("* MCCCalculatorTest: testCurrentMethodCyclomaticComplexity()");

        this.calculator.startNewCyclomaticComplexity();
        assertTrue(this.calculator.getCurrentMethodCyclomaticComplexity() == 1);

        this.calculator.setCurrentMethodCyclomaticComplexity(42);
        assertTrue(this.calculator.getCurrentMethodCyclomaticComplexity() == 42);

        this.calculator.increaseCyclomaticComplexity();
        assertTrue(this.calculator.getCurrentMethodCyclomaticComplexity() == 43);

        this.calculator.increaseCyclomaticComplexity(10);
        assertTrue(this.calculator.getCurrentMethodCyclomaticComplexity() == 53);
    }

    /**
     * Test of saveMethodCyclomaticComplexity method, of class MCCCalculator.
     */
    @Test
    public void testSaveMethodCyclomaticComplexity()
    {
        System.out.println("* MCCCalculatorTest: testSaveMethodCyclomaticComplexity()");

        this.calculator.setCurrentMethodCyclomaticComplexity(42);
        this.calculator.saveMethodCyclomaticComplexity("method1");
        this.calculator.saveMethodCyclomaticComplexity("method2");
        this.calculator.saveMethodCyclomaticComplexity("method3");

        assertTrue(this.calculator.getMethodCount() == 3);
    }

    /**
     * Test of reset method, of class MCCCalculator.
     */
    @Test
    public void testReset()
    {
        System.out.println("* MCCCalculatorTest: testReset()");

        this.calculator.setCurrentMethodCyclomaticComplexity(42);
        this.calculator.saveMethodCyclomaticComplexity("method1");

        this.calculator.reset();

        assertTrue(this.calculator.getCurrentMethodCyclomaticComplexity() == 0);
        assertTrue(this.calculator.getMethodCount() == 0);
    }

    /**
     * Test of getCyclomaticComplexity method, of class MCCCalculator.
     */
    @Test(expected=MCCException.class)
    public void testGetCyclomaticComplexity() throws MCCException
    {
        System.out.println("* MCCCalculatorTest: testGetCyclomaticComplexity()");

        this.calculator.setCurrentMethodCyclomaticComplexity(1);
        this.calculator.saveMethodCyclomaticComplexity("method1");

        this.calculator.setCurrentMethodCyclomaticComplexity(2);
        this.calculator.saveMethodCyclomaticComplexity("method2");

        assertTrue(this.calculator.getCyclomaticComplexity("method1") == 1);
        assertTrue(this.calculator.getCyclomaticComplexity("method2") == 2);

        // Throws MCCException
        System.out.println("MCC = " + this.calculator.getCyclomaticComplexity("mthod3"));
    }

    /**
     * Test of getSumOfCyclomaticComplexity method, of class MCCCalculator.
     */
    @Test
    public void testGetSumOfCyclomaticComplexity()
    {
        System.out.println("* MCCCalculatorTest: testGetSumOfCyclomaticComplexity()");

        this.calculator.setCurrentMethodCyclomaticComplexity(1);
        this.calculator.saveMethodCyclomaticComplexity("method1");

        this.calculator.setCurrentMethodCyclomaticComplexity(2);
        this.calculator.saveMethodCyclomaticComplexity("method2");

        this.calculator.setCurrentMethodCyclomaticComplexity(3);
        this.calculator.saveMethodCyclomaticComplexity("method3");

        assertTrue(this.calculator.getSumOfCyclomaticComplexity() == 6);
    }

    /**
     * Test of calculate method, of class MCCCalculator.
     * Méthode qui permet de tester le visitor
     */
    @Test
    public void testCalculate()
    {
        System.out.println("* MCCCalculatorTest: testCalculate()");

        try
        {
            File fileMCC = new File(getClass().getResource("/code/MCC17FullVisitor.java").toURI());

            FileInputStream in = new FileInputStream(fileMCC);
            CompilationUnit cu;

            // Parse the file
            cu = JavaParser.parse(in);

            this.calculator.calculate(cu);
            assertTrue(this.calculator.getMetric() == 17.0);
        }
        catch (URISyntaxException | FileNotFoundException | ParseException ex)
        {
            System.out.println("MCCCalculatorTest : " + ex.getMessage());
        }
    }

}
