/*
 * Institut Supérieur Industriel Liègeois - Département ingénieurs industriels.
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package metrics.calculators;

import com.github.javaparser.ast.CompilationUnit;
import metrics.MetricCalculator;
import metrics.visitors.WMCVisitor;

/**
 *
 * @author Nakim
 */
public class WMCCalculator extends MetricCalculator
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    protected WMCVisitor visitor;

    // Number of methods defined in class
    protected int methodCount;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public WMCCalculator()
    {
        this(0.0);
    }

    public WMCCalculator(double threshold)
    {
        super(threshold);
        this.visitor = new WMCVisitor();
        this.methodCount = 0;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrided methods">
    @Override
    public void calculate(CompilationUnit cu)
    {
        this.visitor.visit(cu, this);

        this.metric = (double)this.getMethodCount();
    }

    @Override
    public void reset()
    {
        // Reset general calculator variables
        super.reset();

        // Reset WMC specific variables
        this.methodCount = 0;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public int getMethodCount()
    {
        return methodCount;
    }

    public void setMethodCount(int methodCount)
    {
        this.methodCount = methodCount;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public methods">
    public void increaseMethodCount()
    {
        this.methodCount++; // Post increment isn't slower than pre increment
    }
    //</editor-fold>
}
