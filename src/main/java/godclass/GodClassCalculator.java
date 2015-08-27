/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package godclass;

import com.github.javaparser.ast.CompilationUnit;
import metrics.calculators.ATFDCalculator;
import metrics.calculators.MCCCalculator;
import metrics.calculators.TCCCalculator;
import metrics.calculators.WMCCalculator;

/**
 *
 * @author Nakim
 */
public class GodClassCalculator
{
    //<editor-fold defaultstate="collapsed" desc="Static declaration">
    public static double DEFAULT_ATFD_LIMIT;
    public static double DEFAULT_WMC_LIMIT;
    public static double DEFAULT_MCC_LIMIT;
    public static double DEFAULT_TCC_LIMIT;

    static
    {
        DEFAULT_ATFD_LIMIT = 4;
        DEFAULT_WMC_LIMIT  = 15;
        DEFAULT_MCC_LIMIT  = 47;
        DEFAULT_TCC_LIMIT  = 1.0/3.0;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    protected WMCCalculator wmcCalculator;
    protected MCCCalculator mccCalculator;
    protected TCCCalculator tccCalculator;
    protected ATFDCalculator atfdCalculator;

    protected double wmcLimit;
    protected double mccLimit;
    protected double tccLimit;
    protected double atfdLimit;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public GodClassCalculator(double WMCLimit,
                              double MCCLimit,
                              double TCCLimit,
                              double ATFDLimit)
    {
        this.wmcCalculator  = new WMCCalculator();
        this.mccCalculator  = new MCCCalculator();
        this.tccCalculator  = new TCCCalculator();
        this.atfdCalculator = new ATFDCalculator();

        this.wmcLimit  = WMCLimit;
        this.mccLimit  = MCCLimit;
        this.tccLimit  = TCCLimit;
        this.atfdLimit = ATFDLimit;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public methods">
    public void calculate(final CompilationUnit cu)
    {
        System.out.println("=================================================");
        
        this.wmcCalculator.reset();
        this.wmcCalculator.calculate(cu);

        System.out.println("=================================================");

        this.mccCalculator.reset();
        this.mccCalculator.calculate(cu);

        System.out.println("=================================================");

        this.tccCalculator.reset();
        this.tccCalculator.calculate(cu);

        System.out.println("=================================================");

        this.atfdCalculator.reset();
        this.atfdCalculator.calculate(cu);

        System.out.println("=================================================");
    }

    public boolean isGodClass()
    {
        return this.getATFD() > this.atfdLimit &&
               this.getWMC() >= this.wmcLimit  &&
               this.getMCC() >= this.mccLimit  &&
               this.getTCC() <  this.tccLimit;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="WMC">
    public double getWMC()
    {
        return this.wmcCalculator.getMetric();
    }

    public WMCCalculator getWMCCalculator()
    {
        return this.wmcCalculator;
    }

    public double getWMCLimit()
    {
        return this.wmcLimit;
    }

    public void setWMCLimit(double wmcLimit)
    {
        this.wmcLimit = wmcLimit;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="MCC">
    public double getMCC()
    {
        return this.mccCalculator.getMetric();
    }

    public MCCCalculator getMCCCalculator()
    {
        return this.mccCalculator;
    }

    public double getMCCLimit()
    {
        return this.mccLimit;
    }

    public void setMCCLimit(double mccLimit)
    {
        this.mccLimit = mccLimit;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="TCC">
    public double getTCC()
    {
        return this.tccCalculator.getMetric();
    }

    public TCCCalculator getTCCCalculator()
    {
        return this.tccCalculator;
    }

    public double getTCCLimit()
    {
        return this.tccLimit;
    }

    public void setTCCLimit(double tccLimit)
    {
        this.tccLimit = tccLimit;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ATFD">
    public double getATFD()
    {
        return this.atfdCalculator.getMetric();
    }

    public ATFDCalculator getATFDCalculator()
    {
        return this.atfdCalculator;
    }

    public double getATFDLimit()
    {
        return this.atfdLimit;
    }

    public void setATFDLimit(double atfdLimit)
    {
        this.atfdLimit = atfdLimit;
    }
    //</editor-fold>
}
