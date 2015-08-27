/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package metrics.calculators;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.type.Type;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import metrics.exceptions.TCCException;
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
public class TCCCalculatorTest
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    private final TCCCalculator calculator;
    //</editor-fold>

    public TCCCalculatorTest()
    {
        this.calculator = new TCCCalculator();
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
     * Test of addVisibleMethod method, of class TCCCalculator.
     */
    @Test
    public void testAddVisibleMethod()
    {
        System.out.println("* TCCCalculatorTest: testAddVisibleMethod()");

        assertTrue(this.calculator.addVisibleMethod("method1"));
        assertTrue(this.calculator.addVisibleMethod("method2"));
        assertTrue(this.calculator.addVisibleMethod("method3"));
    }

    /**
     * Test of setCurrentMethod method, of class TCCCalculator.
     */
    @Test(expected=TCCException.class)
    public void testSetCurrentMethod() throws TCCException
    {
        System.out.println("* TCCCalculatorTest: testSetCurrentMethod()");

        String method = "method";
        this.calculator.addVisibleMethod(method);

        this.calculator.setCurrentMethod(method);
        this.calculator.setCurrentMethod("invalidMethod");
    }

    /**
     * Test of getCurrentMethod method, of class TCCCalculator.
     */
    @Test
    public void testGetCurrentMethod() throws TCCException
    {
        System.out.println("* TCCCalculatorTest: testGetCurrentMethod()");

        String method = "method";
        this.calculator.addVisibleMethod(method);
        this.calculator.setCurrentMethod(method);

        assertEquals(method, this.calculator.getCurrentMethod());
    }

    /**
     * Test of getTypeOfVariable method, of class TCCCalculator.
     */
    @Test(expected = TCCException.class)
    public void testGetTypeOfVariable() throws TCCException
    {
        System.out.println("* TCCCalculatorTest: testGetTypeOfVariable()");

        Type type = this.calculator.getTypeOfVariable("invalidVariable");
    }

    /**
     * Test of addMemberMethodCall method, of class TCCCalculator.
     */
    @Test(expected = TCCException.class)
    public void testAddMemberMethodCallWithInvalidCurrentMethod() throws TCCException
    {
        System.out.println("* TCCCalculatorTest: testAddMemberMethodCallWithInvalidCurrentMethod()");

        this.calculator.addMemberMethodCall("method");
    }

    /**
     * Test of addMemberMethodCall method, of class TCCCalculator.
     */
    @Test(expected = TCCException.class)
    public void testAddMemberMethodCallWithInvalidMemberMethod() throws TCCException
    {
        System.out.println("* TCCCalculatorTest: testAddMemberMethodCallWithInvalidMemberMethod()");

        String validMethod = "validMethod";
        String invalidMethod = "invalidMethod";

        this.calculator.addVisibleMethod(validMethod);
        this.calculator.setCurrentMethod(validMethod);

        this.calculator.addMemberMethodCall(invalidMethod);
    }

    /**
     * Test of addMemberVariableAccess method, of class TCCCalculator.
     */
    @Test(expected = TCCException.class)
    public void testAddMemberVariableAccessWithInvalidCurrentMethod() throws TCCException
    {
        System.out.println("* TCCCalculatorTest: testAddMemberVariableAccessWithInvalidCurrentMethod()");

        this.calculator.addMemberVariableAccess("invalidVariable");
    }

    /**
     * Test of addMemberVariableAccess method, of class TCCCalculator.
     */
    @Test(expected = TCCException.class)
    public void testAddMemberVariableAccessWithInvalidVariable() throws TCCException
    {
        System.out.println("* TCCCalculatorTest: testAddMemberVariableAccessWithInvalidVariable()");

        String method = "method";
        this.calculator.addVisibleMethod(method);
        this.calculator.setCurrentMethod(method);

        this.calculator.addMemberVariableAccess("invalidVariable");
    }

    /**
     * Test of getNP method, of class TCCCalculator.
     */
    @Test
    public void testGetNP()
    {
        System.out.println("* TCCCalculatorTest: testGetNP()");
        assertTrue(this.calculator.getNP() == 0);
    }

    /**
     * Test of getNDC method, of class TCCCalculator.
     */
    @Test
    public void testGetNDC()
    {
        System.out.println("* TCCCalculatorTest: testGetNDC()");
        assertTrue(this.calculator.getNDC() == 0);
    }

    /**
     * Test of reset method, of class TCCCalculator.
     */
    @Test
    public void testReset() throws TCCException
    {
        System.out.println("* TCCCalculatorTest: testReset()");

        String method = "method";
        this.calculator.addVisibleMethod(method);
        this.calculator.setCurrentMethod(method);

        this.calculator.reset();

        assertTrue(this.calculator.getCurrentMethod().isEmpty());
        assertTrue(this.calculator.getMetric() == 0.0);
    }

    /**
     * Test of calculate method, of class TCCCalculator.
     */
    @Test
    public void testCalculate()
    {
        System.out.println("* TCCCalculatorTest: testCalculate()");

        try
        {
            File TCC4sur10 = new File(getClass().getResource("/code/TCC4sur10FullVisitor.java").toURI());

            FileInputStream in = new FileInputStream(TCC4sur10);
            CompilationUnit cu;

            // Parse the file
            cu = JavaParser.parse(in);

            this.calculator.calculate(cu);
            System.out.println("TCC = " + this.calculator.getMetric());
            //assertTrue(this.calculator.getMetric() == 0.4);
        }
        catch (URISyntaxException | FileNotFoundException | ParseException ex)
        {
            System.out.println("TCCCalculatorTest : " + ex.getMessage());
        }
    }
}
