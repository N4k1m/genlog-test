/*
 * Institut Supérieur Industriel Liègeois - Département ingénieurs industriels.
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package metrics.visitors;

import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import metrics.calculators.WMCCalculator;

/**
 *
 * @author Nakim
 * McCabe Cyclomatic Complexity (MCC) Visitor
 */
public class WMCVisitor extends VoidVisitorAdapter<WMCCalculator>
{
    //<editor-fold defaultstate="collapsed" desc="Overrided methods">
    @Override
    public void visit(MethodDeclaration methodDeclaration,
                      WMCCalculator wmcCalculator)
    {
        super.visit(methodDeclaration, wmcCalculator);

        // Method count
        System.out.println("[WMC] Nouvelle methode = " + methodDeclaration.getName());
        wmcCalculator.increaseMethodCount();
    }

    @Override
    public void visit(ConstructorDeclaration constructorDeclaration,
                      WMCCalculator wmcCalculator)
    {
        super.visit(constructorDeclaration, wmcCalculator);

        System.out.println("[WMC] Nouveau constructeur = " + constructorDeclaration.getName());
        wmcCalculator.increaseMethodCount();
    }
    //</editor-fold>
}
