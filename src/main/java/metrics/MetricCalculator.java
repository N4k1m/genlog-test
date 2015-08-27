/*
 * Institut Supérieur Industriel Liègeois - Département ingénieurs industriels.
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package metrics;

import com.github.javaparser.ast.CompilationUnit;

/**
 *
 * @author Nakim
 */
public abstract class MetricCalculator
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    protected double metric;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    protected MetricCalculator()
    {
        this.metric = 0.0;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public methods">
    public double getMetric()
    {
        return this.metric;
    }

    public void reset()
    {
        this.metric = 0;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public abstract methods">
    public abstract void calculate(final CompilationUnit cu);
    //</editor-fold>
}
