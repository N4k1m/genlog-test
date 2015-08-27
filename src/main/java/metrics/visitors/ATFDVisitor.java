/*
 * Institut Supérieur Industriel Liègeois - Département ingénieurs industriels.
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package metrics.visitors;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.List;
import metrics.calculators.ATFDCalculator;

/**
 *
 * @author Nakim
 */
public class ATFDVisitor extends VoidVisitorAdapter<ATFDCalculator>
{

    @Override
    public void visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration,
                      ATFDCalculator calculator)
    {
        // If it's a class
        if (!classOrInterfaceDeclaration.isInterface())
        {
            System.out.println("[ATFD] Nouvelle déclaration de classe = " +
                classOrInterfaceDeclaration.getName());

            calculator.addClassToRemove(classOrInterfaceDeclaration.getName());
        }

        super.visit(classOrInterfaceDeclaration, calculator);
    }

    @Override
    public void visit(FieldDeclaration fieldDeclaration, ATFDCalculator calculator)
    {
        List<VariableDeclarator> variables = fieldDeclaration.getVariables();

        for(VariableDeclarator var : variables)
            System.out.println("[ATFD] Nouvelle variable membre = " +
                fieldDeclaration.getType().toString() + " " + var.getId().getName());

        calculator.addMemberVariables(variables, fieldDeclaration.getType());

        super.visit(fieldDeclaration, calculator);
    }

    @Override
    public void visit(MethodDeclaration methodDeclaration, ATFDCalculator calculator)
    {
        System.out.println("[ATFD] Nouvelle méthode = " + methodDeclaration.getName());

        if (!methodDeclaration.getParameters().isEmpty())
        {
            System.out.println("[ATFD] Sauvegarde des parametres");
            calculator.addLocalVariables(methodDeclaration.getParameters());
        }

        super.visit(methodDeclaration, calculator);

        // Clear variables local to the method
        calculator.clearLocalVariables();
    }

    @Override
    public void visit(FieldAccessExpr fieldAccessExpr, ATFDCalculator calculator)
    {
        System.out.println("[ATFD] Nouvel attribut accede = " + fieldAccessExpr.toString());

        // Scope is supposed to be the variable name
        calculator.addExternalClassOfVariable(fieldAccessExpr.getScope().toString());

        super.visit(fieldAccessExpr, calculator);
    }

    @Override
    public void visit(VariableDeclarationExpr variableDeclarationExpr, ATFDCalculator calculator)
    {
        System.out.println("[ATFD] Nouvelle(s) variable(s) locale(s) = " + variableDeclarationExpr.toString());

        calculator.addLocalVariables(variableDeclarationExpr.getVars(),
                                     variableDeclarationExpr.getType());

        super.visit(variableDeclarationExpr, calculator);
    }

    @Override
    public void visit(MethodCallExpr methodCallExpr, ATFDCalculator calculator)
    {
        Expression scope = methodCallExpr.getScope();

        // scope is null or egals to "this" if it's a member method call
        if (scope != null && !scope.toString().startsWith("this"))
        {
            String methodName = methodCallExpr.getName().toUpperCase();

            if (methodName.startsWith("GET") || methodName.startsWith("SET"))
            {
                System.out.println("[ATFD] Nouvel appel d'un accesseur = " + methodCallExpr.toString());
                calculator.addExternalClassOfVariable(scope.toString());
            }
        }

        super.visit(methodCallExpr, calculator);
    }
}
