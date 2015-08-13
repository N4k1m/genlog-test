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
    protected double threshold;
    protected double metric;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    protected MetricCalculator(double threshold)
    {
        this.threshold = threshold;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public methods">
    public double getThreshold()
    {
        return this.threshold;
    }

    public void setThreshold(double threshold)
    {
        this.threshold = threshold;
    }

    public double getMetric()
    {
        return this.metric;
    }

    public void reset(final double threshold)
    {
        this.threshold = threshold;
        this.metric = 0;
    }

    public void reset()
    {
        this.reset(this.threshold);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public abstract methods">
    public abstract void calculate(final CompilationUnit cu);
    //</editor-fold>
}
