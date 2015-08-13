/*
 * Institut Supérieur Industriel Liègeois - Département ingénieurs industriels.
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package metrics.visitors;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.List;
import metrics.calculators.TCCCalculator;
import metrics.exceptions.TCCException;

/**
 *
 * @author Nakim
 */
public class TCCConnectionsVisitor extends VoidVisitorAdapter<TCCCalculator>
{
    //<editor-fold defaultstate="collapsed" desc="Overrided methods">
    @Override
    public void visit(MethodDeclaration methodDeclaration, TCCCalculator calculator)
    {
        String methodDeclarationString =
            methodDeclaration.getDeclarationAsString(true, false, false);

        // No need to explore the body of a private method
        if (methodDeclarationString.startsWith("private"))
            return;

        // Build method name : "name type1 type2 ..."
        String methodPrototype = methodDeclaration.getName();
        for(Parameter parameter : methodDeclaration.getParameters())
            methodPrototype += " " + parameter.getType();

        try
        {
            // Change current method
            calculator.setCurrentMethod(methodPrototype);

            // Temporarily save the name and type of parameters
            if (!methodDeclaration.getParameters().isEmpty())
            {
                System.out.println("[TCC] Sauvegarde des parametres");
                calculator.addLocalVariables(methodDeclaration.getParameters());
            }

            // Explore the method body
            super.visit(methodDeclaration, calculator);
        }
        catch (TCCException ex)
        {
            System.out.println("[TCC] Erreur avec la méthode courante : " +
                ex.getMessage());
        }
    }

    @Override
    public void visit(VariableDeclarationExpr variableDeclarationExpr, TCCCalculator calculator)
    {
        /* Création d'une nouvelle variable locale a la methode. La sauvegarder
         * temporairement dans une liste avec son type au cas ou elle serait
         * utilisée comme paramètre lors de l'appel d'une méthode membre. Cela
         * permettra de reconstruire le prototype de la méthode membre appelée.
         */
        calculator.addLocalVariables(variableDeclarationExpr.getVars(),
                                     variableDeclarationExpr.getType());

        super.visit(variableDeclarationExpr, calculator);
    }

    @Override
    public void visit(MethodCallExpr methodCallExpr, TCCCalculator calculator)
    {
        /* Le scope de la methodCallExpr peut être :
         *  1) null ou this : appel d'une méthode membre
         *  2) une variable membre : la méthode courante accède directement à la variable membre
         *  3) une variable (objet) locale à la méthode : ne rien faire
         * Dans tous les cas, vérifier si des variables membres sont passées en paramètres
         */
        Expression scope = methodCallExpr.getScope();

        // 1) null ou this : appel d'une méthode membre
        if (scope == null || scope.toString().equals("this"))
            this.memberMethodCalled(methodCallExpr, calculator);
        // 2) une variable membre : la méthode courante accède à une variable membre
        else if (calculator.containsMemberVariable(scope.toString()))
            this.memberVariableAccessed(scope.toString(), calculator);

        /* Vérifier si les paramètres de la méthode ne sont pas des varaibles membres */
        List<Expression> args = methodCallExpr.getArgs();
        if (args != null && !args.isEmpty())
        {
            for(Expression arg : args)
            {
                String argString = arg.toString();

                // Remove the "this."
                if(argString.startsWith("this"))
                    argString = argString.substring(argString.lastIndexOf(".") +1);

                // Regarde si le paramètre est une varaible membre
                if (calculator.isMemberVariable(argString))
                    this.memberVariableAccessed(argString, calculator);
            }
        }

        super.visit(methodCallExpr, calculator);
    }

    @Override
    public void visit(AssignExpr assignExpr, TCCCalculator calculator)
    {
        // Target (left)
        String expression = assignExpr.getTarget().toString();

        // Remove the "this."
        if(expression.startsWith("this"))
            expression = expression.substring(expression.lastIndexOf(".") +1);

        // Regarde si le paramètre est une varaible membre
        if (calculator.isMemberVariable(expression))
            this.memberVariableAccessed(expression, calculator);


        // Value (right)
        expression = assignExpr.getValue().toString();

        // Remove the "this."
        if(expression.startsWith("this"))
            expression = expression.substring(expression.lastIndexOf(".") +1);

        // Regarde si le paramètre est une varaible membre
        if (calculator.isMemberVariable(expression))
            this.memberVariableAccessed(expression, calculator);

        super.visit(assignExpr, calculator);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Private methods">
    private void memberMethodCalled(MethodCallExpr methodCallExpr, TCCCalculator calculator)
    {
        // Il s'agit d'un appel à une méthode membre --> construire son prototype
        String calledMethodPrototype = methodCallExpr.getName();

        try
        {
            // Recherche des types de tous ses paramètres
            List<Expression> args = methodCallExpr.getArgs();
            if (args != null && !args.isEmpty())
                for(Expression arg : args)
                    calledMethodPrototype += " " + calculator.getTypeOfVariable(arg.toString());

            System.out.println("[TCC] La methode \"" + calculator.getCurrentMethod()
                + "\" invoque la methode \"" + calledMethodPrototype + "\"");

            calculator.addMemberMethodCall(calledMethodPrototype);
        }
        catch (TCCException ex)
        {
            System.out.println("[TCC] Erreur dans la construction du prototype"
                + " de la methode membre " + methodCallExpr.getName() + " : "
                + ex.getMessage());
        }
    }

    private void memberVariableAccessed(String variable, TCCCalculator calculator)
    {
        try
        {
            System.out.println("[TCC] La methode \"" + calculator.getCurrentMethod()
                + "\" accede a la variable \"" + variable + "\"");

            calculator.addMemberVariableAccess(variable);
        }
        catch (TCCException ex)
        {
            System.out.println("[TCC] Erreur en renseignant qu'une méthode"
                + " accédait à une variable membre : " + ex.getMessage());
        }
    }
    //</editor-fold>
}
