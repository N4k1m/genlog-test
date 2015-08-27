/*
 * Institut Supérieur Industriel Liègeois - Département ingénieurs industriels.
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package metrics.visitors;

import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.WithDeclaration;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.BinaryExpr.Operator;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.ForeachStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchEntryStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.List;
import metrics.calculators.MCCCalculator;

/**
 *
 * @author Nakim
 */
public class MCCVisitor extends VoidVisitorAdapter<MCCCalculator>
{
    //<editor-fold defaultstate="collapsed" desc="Overrided methods">
    @Override
    public void visit(ConstructorDeclaration constructorDeclaration,
                      MCCCalculator mCCCalculator)
    {
        // Cyclomatic complexity : Start with a count of one for the constructor
        this.initializeMethodCyclomaticComplexity(constructorDeclaration,
                                                  constructorDeclaration.getThrows(),
                                                  mCCCalculator);

        // Visit the constructor body
        super.visit(constructorDeclaration, mCCCalculator);

        // Save cyclomatic complexity for this constructor
        this.validateMethodCyclomaticComplexity(constructorDeclaration, mCCCalculator);
    }

    @Override
    public void visit(MethodDeclaration methodDeclaration, MCCCalculator mCCCalculator)
    {
        // Cyclomatic complexity : Start with a count of one for the method.
        this.initializeMethodCyclomaticComplexity(methodDeclaration,
                                                  methodDeclaration.getThrows(),
                                                  mCCCalculator);

        // Visit the method body
        super.visit(methodDeclaration, mCCCalculator);

        // Save cyclomatic complexity for this method
        this.validateMethodCyclomaticComplexity(methodDeclaration, mCCCalculator);
    }

    @Override
    public void visit(IfStmt ifStmt, MCCCalculator mcCabeCalculator)
    {
        System.out.println("[McCabe]    if statement --> Complexite + 1 ");

        // Add one for each if statement
        mcCabeCalculator.increaseCyclomaticComplexity();

        super.visit(ifStmt, mcCabeCalculator);
    }

    @Override
    public void visit(WhileStmt whileStmt, MCCCalculator mCCCalculator)
    {
        System.out.println("[McCabe]    while statement --> Complexite + 1");

        // Add one for each while statement
        mCCCalculator.increaseCyclomaticComplexity();

        super.visit(whileStmt, mCCCalculator);
    }

    @Override
    public void visit(DoStmt doStmt, MCCCalculator mCCCalculator)
    {
        System.out.println("[McCabe]    do...while statement --> Complexite + 1");

        // Add one for each do...while statement
        mCCCalculator.increaseCyclomaticComplexity();

        super.visit(doStmt, mCCCalculator);
    }

    @Override
    public void visit(ForStmt forStmt, MCCCalculator mCCCalculator)
    {
        System.out.println("[McCabe]    for statement --> Complexite + 1");

        // Add one for each while statement
        mCCCalculator.increaseCyclomaticComplexity();

        super.visit(forStmt, mCCCalculator);
    }

    @Override
    public void visit(ForeachStmt foreachStmt, MCCCalculator mCCCalculator)
    {
        System.out.println("[McCabe]    foreach statement --> Complexite + 1");

        // Add one for each foreach statement
        mCCCalculator.increaseCyclomaticComplexity();

        super.visit(foreachStmt, mCCCalculator);
    }

    @Override
    public void visit(SwitchStmt switchStmt, MCCCalculator mCCCalculator)
    {
        System.out.println("[McCabe]    switch statement --> Complexite + 1 ");

        // Add one for each switch statement
        mCCCalculator.increaseCyclomaticComplexity();

        super.visit(switchStmt, mCCCalculator);
    }

    @Override
    public void visit(SwitchEntryStmt switchEntryStmt, MCCCalculator mCCCalculator)
    {
        /* Add one for each "case" statement. "default" statement is also a
         * SwitchEntryStmt but doesn't increase the cyclomatic complexity
         */
        if (switchEntryStmt.getLabel() != null)
        {
            System.out.println("[McCabe]        case statement --> Complexite + 1 ");
            mCCCalculator.increaseCyclomaticComplexity();
        }

        super.visit(switchEntryStmt, mCCCalculator);
    }

    @Override
    public void visit(CatchClause catchClause, MCCCalculator mCCCalculator)
    {
        System.out.println("[McCabe]    catch clause --> Complexite + 1 ");

        // Add one for each catch clause
        mCCCalculator.increaseCyclomaticComplexity();

        super.visit(catchClause, mCCCalculator);
    }

    @Override
    public void visit(BinaryExpr binaryExpr, MCCCalculator mcCabeCalculator)
    {
        Operator operator = binaryExpr.getOperator();
        if (operator == BinaryExpr.Operator.and || operator == BinaryExpr.Operator.or)
        {
            System.out.println("[McCabe]        expression logique && ou || --> complexite + 1");

            // Add one for each logic expression && or ||
            mcCabeCalculator.increaseCyclomaticComplexity();
        }

        super.visit(binaryExpr, mcCabeCalculator);
    }

    @Override
    public void visit(ThrowStmt throwStmt, MCCCalculator mcCabeCalculator)
    {
        System.out.println("[McCabe]    throw statement --> Complexite + 1");

        // Add one for each throw statement
        mcCabeCalculator.increaseCyclomaticComplexity();

        super.visit(throwStmt, mcCabeCalculator);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Private methods">
    private void initializeMethodCyclomaticComplexity(WithDeclaration methodDeclaration,
                                                      List<NameExpr> throwsClauses,
                                                      MCCCalculator mCCCalculator)
    {
        System.out.println("[McCabe] Nouvelle methode = "
            + methodDeclaration.getDeclarationAsString(false, false, true) + " --> Complexite = 1");

        // Cyclomatic complexity : Start with a count of one for the method.
        mCCCalculator.startNewCyclomaticComplexity();

        // Add one for each throws clause
        if (!throwsClauses.isEmpty())
        {
            mCCCalculator.increaseCyclomaticComplexity(throwsClauses.size());

            for (NameExpr nameExpr : throwsClauses)
                System.out.println("[McCabe]    throws clause = " +
                    nameExpr.getName() + " --> Complexite + 1");
        }
    }

    private void validateMethodCyclomaticComplexity(WithDeclaration methodDeclaration,
                                                    MCCCalculator mCCCalculator)
    {
        // Save cyclomatic complexity for this method
        System.out.println("[McCabe] Sauvegarde de la complexite cyclomatique de la method = "
            + mCCCalculator.getCurrentMethodCyclomaticComplexity());
        mCCCalculator.saveMethodCyclomaticComplexity(
            methodDeclaration.getDeclarationAsString(false, true));
    }
    //</editor-fold>
}
