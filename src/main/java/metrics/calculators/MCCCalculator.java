/*
 * Institut Supérieur Industriel Liègeois - Département ingénieurs industriels.
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package metrics.calculators;

import com.github.javaparser.ast.CompilationUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import metrics.MetricCalculator;
import metrics.exceptions.MCCException;
import metrics.visitors.MCCVisitor;

/**
 *
 * @author Nakim
 */
public class MCCCalculator extends MetricCalculator
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    protected MCCVisitor visitor;

    // Cyclomatic complexity (McCabe) of the current method
    protected int currentMethodCyclomaticComplexity;
    // Save cyclomatic complexity for each method
    protected Map<String, Integer> methodsCyclomaticComplexity;

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public MCCCalculator()
    {
        this(0.0);
    }

    public MCCCalculator(double threshold)
    {
        super(threshold);
        this.visitor = new MCCVisitor();
        this.methodsCyclomaticComplexity = new HashMap<>();
        this.currentMethodCyclomaticComplexity = 0;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrided methods">
    @Override
    public void calculate(CompilationUnit cu)
    {
        this.visitor.visit(cu, this);

        this.metric = (double)this.getSumOfCyclomaticComplexity();
    }

    @Override
    public void reset()
    {
        // Reset general calculator variables
        super.reset();

        // Reset McCabe specific variables
        this.currentMethodCyclomaticComplexity = 0;
        this.methodsCyclomaticComplexity.clear();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public int getCurrentMethodCyclomaticComplexity()
    {
        return currentMethodCyclomaticComplexity;
    }

    public void setCurrentMethodCyclomaticComplexity(int currentMethodCyclomaticComplexity)
    {
        this.currentMethodCyclomaticComplexity = currentMethodCyclomaticComplexity;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public methods">
    public void startNewCyclomaticComplexity()
    {
        this.currentMethodCyclomaticComplexity = 1;
    }

    public void increaseCyclomaticComplexity()
    {
        this.currentMethodCyclomaticComplexity++;
    }

    public void increaseCyclomaticComplexity(int value)
    {
        this.currentMethodCyclomaticComplexity += value;
    }

    public void saveMethodCyclomaticComplexity(final String methodDeclaration)
    {
        this.methodsCyclomaticComplexity.put(methodDeclaration, this.currentMethodCyclomaticComplexity);
        this.currentMethodCyclomaticComplexity = 0;
    }

    public int getMethodCount()
    {
        return this.methodsCyclomaticComplexity.size();
    }

    public int getCyclomaticComplexity(String methodDeclaration) throws MCCException
    {
        for (String key : this.methodsCyclomaticComplexity.keySet())
            if (key.endsWith(methodDeclaration))
                return this.methodsCyclomaticComplexity.get(key);

        // Method not found
        throw new MCCException("Method " + methodDeclaration + " not found");
    }

    public int getSumOfCyclomaticComplexity()
    {
        int v_G = 0;

        for(Entry<String, Integer> entry: this.methodsCyclomaticComplexity.entrySet())
            v_G += entry.getValue() /* -1 ???*/;

        return v_G;
    }
    //</editor-fold>
}
