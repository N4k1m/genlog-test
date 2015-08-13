/*
 * Institut Supérieur Industriel Liègeois - Département ingénieurs industriels.
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package metrics.visitors;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.List;
import metrics.calculators.TCCCalculator;

/**
 *
 * @author Nakim
 */
public class TCCVariablesMethodsVisitor extends VoidVisitorAdapter<TCCCalculator>
{
    //<editor-fold defaultstate="collapsed" desc="Overrided Methods">
    @Override
    public void visit(FieldDeclaration fieldDeclaration, TCCCalculator tccCalculator)
    {
        List<VariableDeclarator> variables = fieldDeclaration.getVariables();

        for(VariableDeclarator variable : variables)
            System.out.println("[TCC] Nouvelle variable membre = " +
                fieldDeclaration.getType().toString() + " " + variable);

        tccCalculator.addMemberVariables(variables, fieldDeclaration.getType());

        //super.visit(fieldDeclaration, tccCalculator);
    }

    @Override
    public void visit(MethodDeclaration methodDeclaration, TCCCalculator tccCalculator)
    {
        String methodDeclarationString =
            methodDeclaration.getDeclarationAsString(true, false, false);

        /* For TCC and LCC we only consider visible methods
         * A method is visible unless it is Private
         */
        if (methodDeclarationString.startsWith("private"))
            return;

        // Build method name : "name type1 type2 ..."
        String methodName = methodDeclaration.getName();
        for(Parameter parameter : methodDeclaration.getParameters())
            methodName += " " + parameter.getType();

        System.out.println("[TCC] Nouvelle méthode visible = " + methodName);

        tccCalculator.addVisibleMethod(methodName);

        // No need to visit the body of the method
        //super.visit(methodDeclaration, tccCalculator);
    }
    //</editor-fold>
}
