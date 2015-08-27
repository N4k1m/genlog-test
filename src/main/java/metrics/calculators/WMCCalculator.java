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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public WMCCalculator()
    {
        super();
        this.visitor = new WMCVisitor();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrided methods">
    @Override
    public void calculate(CompilationUnit cu)
    {
        this.visitor.visit(cu, this);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public int getMethodCount()
    {
        return (int)this.metric;
    }

    public void setMethodCount(int methodCount)
    {
        this.metric = (double)methodCount;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public methods">
    public void increaseMethodCount()
    {
        this.metric++; // Post increment isn't slower than pre increment
    }
    //</editor-fold>
}
