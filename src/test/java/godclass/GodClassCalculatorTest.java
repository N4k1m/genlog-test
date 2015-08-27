/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package godclass;

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
public class GodClassCalculatorTest
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    private final GodClassCalculator calculator;
    //</editor-fold>

    public GodClassCalculatorTest()
    {
        this.calculator = new GodClassCalculator(
            GodClassCalculator.DEFAULT_WMC_LIMIT,
            GodClassCalculator.DEFAULT_MCC_LIMIT,
            GodClassCalculator.DEFAULT_TCC_LIMIT,
            GodClassCalculator.DEFAULT_ATFD_LIMIT);
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
     * Test of calculate method, of class GodClassCalculator.
     */
    @Test
    public void testCalculate()
    {
        System.out.println("* MCCCalculatorTest: testCalculate()");

        try
        {
            File fileMCC = new File(getClass().getResource("/code/ATFD2WMC5MCC10TCC7sur10.java").toURI());

            FileInputStream in = new FileInputStream(fileMCC);
            CompilationUnit cu;

            // Parse the file
            cu = JavaParser.parse(in);

            this.calculator.calculate(cu);


            assertTrue(this.calculator.getATFD() == 2.0);
            assertTrue(this.calculator.getWMC() == 5.0);
            assertTrue(this.calculator.getMCC() == 10.0);
            assertTrue(this.calculator.getTCC() == 0.7);
            assertFalse(this.calculator.isGodClass());
        }
        catch (URISyntaxException | FileNotFoundException | ParseException ex)
        {
            System.out.println("MCCCalculatorTest : " + ex.getMessage());
        }
    }
}
