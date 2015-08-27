/*
 * Institut Supérieur Industriel Liègeois - Département ingénieurs industriels.
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package metrics.calculators;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.type.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import metrics.MetricCalculator;
import metrics.visitors.ATFDVisitor;

/**
 *
 * @author Nakim
 */
public class ATFDCalculator extends MetricCalculator
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    protected ATFDVisitor visitor;

    protected Set<String> classesToRemove; // Inner class or same class instance
    protected Set<String> externalClasses;
    protected Set<String> unknownClassesVariables;

    protected Map<String, Type> memberVariables;
    protected Map<String, Type> localVariables;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public ATFDCalculator()
    {
        super();
        this.visitor = new ATFDVisitor();
        this.classesToRemove = new HashSet<>();
        this.externalClasses = new HashSet<>();
        this.unknownClassesVariables = new HashSet<>();
        this.memberVariables = new HashMap<>();
        this.localVariables = new HashMap<>();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrided methods">
    @Override
    public void calculate(CompilationUnit cu)
    {
        this.visitor.visit(cu, this);

        /* Ajoute le nom de la classe des variables membres qui accédaient à un
         * attribut mais pour lesquelles on ne connaissait pas encore le type
         * Du au fait que la déclaration des variables membres se trouvait à la
         * fin de la classe --> types des varaibles pas connu durant le parsing
         */
        this.checkClassOfUnknownClassVariable();

        /* Supprime les innerclass de la liste des classes externes et la classe
         * courante dans le cas ou une ou plusieurs variables membres sont du
         * même type que la classe courante que l'on analyse
         */
        System.out.println("[ATFD] Retrait des classes suivantes : " + this.classesToRemove);
        this.externalClasses.removeAll(this.classesToRemove);

        // Calcule le métrique
        this.metric = (double)this.externalClasses.size();
    }

    @Override
    public void reset()
    {
        super.reset();

        // Reset ATFD specific variables
        this.classesToRemove.clear();
        this.externalClasses.clear();
        this.unknownClassesVariables.clear();
        this.memberVariables.clear();
        this.localVariables.clear();
    }


    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public methods">
    public Type getTypeOfVariable(String variable)
    {
        // Local variables hide member memberVariables
        for(Map.Entry<String, Type> variableType : this.localVariables.entrySet())
            if (variableType.getKey().equals(variable))
                return variableType.getValue();

        // Search in member memberVariables after local ones
        for(Map.Entry<String, Type> variableType : this.memberVariables.entrySet())
            if (variableType.getKey().equals(variable))
                return variableType.getValue();

        // Variable type not found
        return null;
    }

    public void addClassToRemove(final String classeName)
    {
        this.classesToRemove.add(classeName);
    }

    public Set<String> getClassesToRemove()
    {
        return this.classesToRemove;
    }

    public boolean addExternalClass(final String className)
    {
        return this.externalClasses.add(className);
    }

    public Set<String> getExternalClasses()
    {
        return this.externalClasses;
    }

    public boolean addExternalClassOfVariable(final String variableName)
    {
        // Get variable type (class name)
        Type classType = this.getTypeOfVariable(variableName);

        // If type is know (local or member variable), add className to the list
        if (classType != null)
            return this.addExternalClass(classType.toString());

        /* If type is unknown (member variables declaration is at the end of the
         * class), save the variable name to be able to add the corresponding
         * class name to the list at the end of the parsing
         */
        return this.addUnknownClassVariable(variableName);
    }

    public boolean addUnknownClassVariable(String variableName)
    {
        return this.unknownClassesVariables.add(variableName);
    }

    public void addMemberVariable(final String variable, final Type type)
    {
        this.memberVariables.put(variable, type);
    }

    public void addMemberVariables(final List<VariableDeclarator> variables, final Type type)
    {
        for(VariableDeclarator variable : variables)
            this.addMemberVariable(variable.toString(), type);
    }

    public void addLocalVariable(final String variable, final Type type)
    {
        this.localVariables.put(variable, type);
    }

    public void addLocalVariables(final List<Parameter> variables)
    {
        for(Parameter variable : variables)
            this.addLocalVariable(variable.getId().getName(), variable.getType());
    }

    public void addLocalVariables(final List<VariableDeclarator> variables, final Type type)
    {
        for(VariableDeclarator variable : variables)
            this.addLocalVariable(variable.getId().toString(), type);
    }

    public void clearLocalVariables()
    {
        this.localVariables.clear();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Private methods">
    private void checkClassOfUnknownClassVariable()
    {
        for (String variable : this.unknownClassesVariables)
        {
            Type classType = this.getTypeOfVariable(variable);

            if (classType != null)
                this.addExternalClass(classType.toString());
        }
    }
    //</editor-fold>
}
