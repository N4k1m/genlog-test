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
        this.wmcCalculator.reset();
        this.wmcCalculator.calculate(cu);

        this.mccCalculator.reset();
        this.mccCalculator.calculate(cu);

        this.tccCalculator.reset();
        this.tccCalculator.calculate(cu);

        this.atfdCalculator.reset();
        this.atfdCalculator.calculate(cu);
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
