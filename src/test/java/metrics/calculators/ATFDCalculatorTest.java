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
import java.util.Set;
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
public class ATFDCalculatorTest
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    private final ATFDCalculator calculator;
    //</editor-fold>

    public ATFDCalculatorTest()
    {
        this.calculator = new ATFDCalculator();
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
     * Test of getTypeOfVariable method, of class ATFDCalculator.
     */
    @Test
    public void testGetTypeOfVariable()
    {
        System.out.println("* ATFDCalculatorTest: testGetTypeOfVariable()");

        assertNull(this.calculator.getTypeOfVariable("var"));
    }

    /**
     * Test of addClassToRemove method, of class ATFDCalculator.
     */
    @Test
    public void testClassToRemove()
    {
        System.out.println("* ATFDCalculatorTest: testClassToRemove()");

        String class1 = "Class1";
        String class2 = "Class2";
        this.calculator.addClassToRemove(class1);
        this.calculator.addClassToRemove(class2);

        Set<String> classesToRemove = this.calculator.getClassesToRemove();
        assertTrue(classesToRemove.contains(class1));
        assertTrue(classesToRemove.contains(class2));
        assertTrue(classesToRemove.size() == 2);
    }

    /**
     * Test of addExternalClass method, of class ATFDCalculator.
     */
    @Test
    public void testExternalClass()
    {
        System.out.println("* ATFDCalculatorTest: testExternalClass()");

        String class1 = "Class1";
        String class2 = "Class2";
        this.calculator.addExternalClass(class1);
        this.calculator.addExternalClass(class2);

        Set<String> externalClasses = this.calculator.getExternalClasses();
        assertTrue(externalClasses.contains(class1));
        assertTrue(externalClasses.contains(class2));
        assertTrue(externalClasses.size() == 2);
    }

    /**
     * Test of reset method, of class ATFDCalculator.
     */
    @Test
    public void testReset()
    {
        System.out.println("* ATFDCalculatorTest: testReset()");

        String class1 = "Class1";
        String class2 = "Class2";
        this.calculator.addClassToRemove(class1);
        this.calculator.addClassToRemove(class2);
        this.calculator.addExternalClass(class1);
        this.calculator.addExternalClass(class2);

        this.calculator.reset();

        assertTrue(this.calculator.getClassesToRemove().isEmpty());
        assertTrue(this.calculator.getExternalClasses().isEmpty());
        assertTrue(this.calculator.getMetric() == 0.0);
    }

    /**
     * Test of calculate method, of class ATFDCalculator.
     */
    @Test
    public void testCalculate()
    {
        System.out.println("* ATFDCalculatorTest: testCalculate()");

        try
        {
            File fileMCC = new File(getClass().getResource("/code/ATFD3FullVisitor.java").toURI());

            FileInputStream in = new FileInputStream(fileMCC);
            CompilationUnit cu;

            // Parse the file
            cu = JavaParser.parse(in);

            this.calculator.calculate(cu);
            assertTrue(this.calculator.getMetric() == 3.0);
        }
        catch (URISyntaxException | FileNotFoundException | ParseException ex)
        {
            System.out.println("ATFDCalculatorTest : " + ex.getMessage());
        }
    }
}
