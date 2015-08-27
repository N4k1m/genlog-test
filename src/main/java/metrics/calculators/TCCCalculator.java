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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import metrics.MetricCalculator;
import metrics.exceptions.TCCException;
import metrics.visitors.TCCConnectionsVisitor;
import metrics.visitors.TCCVariablesMethodsVisitor;
import utils.Pair;
import utils.TreeNode;

/**
 *
 * @author Nakim
 */
public class TCCCalculator extends MetricCalculator
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    protected TCCVariablesMethodsVisitor tccVariablesMethodsVisitor;
    protected TCCConnectionsVisitor tccConnectionsVisitor;

    // Curent method name and local memberVariables
    protected String currentMethod;
    protected Map<String, Type> localVariables;

    protected Map<String, Type> memberVariables; // sauve toutes les variables membres avec leur type
    protected Set<String> visibleMethods;        // sauve toutes les méthodes membres avec les types de leurs paramètres : "name type1 type2"

    protected Map<String, Set<String>> calledMethodsInMethods;
    protected Map<String, Set<String>> connectedMethodsToVariables;

    protected int NDC;
    protected int NP;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public TCCCalculator()
    {
        super();

        this.tccVariablesMethodsVisitor = new TCCVariablesMethodsVisitor();
        this.tccConnectionsVisitor = new TCCConnectionsVisitor();

        this.currentMethod = "";
        this.localVariables = new HashMap<>();

        this.memberVariables = new HashMap<>();
        this.visibleMethods = new HashSet<>();

        this.calledMethodsInMethods = new HashMap<>();
        this.connectedMethodsToVariables = new HashMap<>();

        this.NDC = 0;
        this.NP  = 0;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Private methods">
    private TreeNode<String> buildCallTree(String method)
    {
        // Créer un noeud avec un enfant par méthode appelée
        TreeNode<String> node = new TreeNode<>(method);

        // Si la méthode n'appelle pas d'autre méthode, le noeud seul est renvoyé
        if (!this.calledMethodsInMethods.containsKey(method))
            return node;

        // Récupère la liste des méthodes appelées par la méthode "method"
        Set<String> calledMethods = this.calledMethodsInMethods.get(method);

        for(String calledMethod : calledMethods)
            node.addChildNode(this.buildCallTree(calledMethod));

        return node;
    }

    private void addIndirectConnectedMethodsToVariables()
    {
        // Map qui contiendra la liste des méthodes qui ont un accès indirect aux variable
        Map<String, Set<String>> indirectConnectedMethodsToVariables = new HashMap<>();
        for(String variable : this.connectedMethodsToVariables.keySet())
            indirectConnectedMethodsToVariables.put(variable, new HashSet<String>());

        // Récupération de toutes les méthodes qui font appel à d'autres méthodes
        for(String method : this.calledMethodsInMethods.keySet())
        {
            // Construire l'arbre des appels pour la méthode courante
            TreeNode<String> callTree = this.buildCallTree(method);

            // Récupération des feuilles de "l'arbre des appels" pour la méthode courante
            List<TreeNode<String>> leaves = callTree.getLeaves();

            // Pour chaque feuille
            for(TreeNode<String> leaf : leaves)
            {
                // Pour chaque variable membre
                for (String variable : this.connectedMethodsToVariables.keySet())
                {
                    /* Regarder si la méthode associée à la feuille à un accès
                     * direct à la variable courante :
                     *  - Si oui : ajouter toutes les méthodes des noeuds parents
                     *             jusqu'à la racine aux méthodes qui accèdent
                     *             indirectement à la variable
                     *  - Si non : remonter dans l'arbre vers la racine et retester
                     */

                    TreeNode<String> leafCopy = leaf;
                    Set<String> directAccessMethods = this.connectedMethodsToVariables.get(variable);

                    // Tant qu'on est pas remonté jusqu'à la racine de l'arbre
                    while(leafCopy != null)
                    {
                        // Si la méthode associé à la feuille à un accès direct à la variable courante
                        if (directAccessMethods.contains(leafCopy.getValue()))
                        {
                            Set<String> indirectAccessMethods =
                                indirectConnectedMethodsToVariables.get(variable);

                            /* ajouter toutes les méthodes des noeuds parents
                             * jusqu'à la racine aux méthodes qui accèdent
                             * indirectement à la variable */
                            for(leafCopy = leafCopy.getParent();
                                leafCopy != null;
                                leafCopy = leafCopy.getParent())
                                indirectAccessMethods.add(leafCopy.getValue());
                        }
                        // Sinon, remonter dans l'arbre vers la racine et retester
                        else
                            leafCopy = leafCopy.getParent();
                    }
                }
            }
        }

        // Ajoute les accès indirects aux variables membres
        for(String variable : indirectConnectedMethodsToVariables.keySet())
            this.connectedMethodsToVariables.get(variable).addAll(
                indirectConnectedMethodsToVariables.get(variable));
    }

    private int calculateNP()
    {
        int numberOfVisibleMethods = this.visibleMethods.size();
        return (numberOfVisibleMethods * (numberOfVisibleMethods - 1))/2;
    }

    private int calculateNDC()
    {
        Set<Pair<String, String>> pairMethods = new HashSet<>();

        for(Set<String> methodSet : this.connectedMethodsToVariables.values())
        {
            List<String> methodList = new ArrayList<>(methodSet);

            for (int index1 = 0; index1 < methodList.size() - 1; ++index1)
                for(int index2 = index1+1; index2 < methodList.size(); ++index2)
                    pairMethods.add(new Pair<>(methodList.get(index1), methodList.get(index2)));
        }

        return pairMethods.size();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrided methods">
    @Override
    public void calculate(CompilationUnit cu)
    {
        this.tccVariablesMethodsVisitor.visit(cu, this);

        this.printVisibleMethods();
        this.printMemberVariables();

        this.tccConnectionsVisitor.visit(cu, this);

        this.printConnectedMethodsToVariables();
        this.printCalledMethodsInMethods();

        // Pour chaque variable, ajouter les méthodes qui y accèdent de manière indirecte
        this.addIndirectConnectedMethodsToVariables();

        this.printConnectedMethodsToVariables();

        this.NDC = calculateNDC();
        this.NP  = calculateNP();

        this.metric = (double)NDC/(double)NP;
    }

    @Override
    public void reset()
    {
        // Reset general calculator variables
        super.reset();

        // Reset TCC specific variables
        this.currentMethod = "";
        this.localVariables.clear();

        this.memberVariables.clear();
        this.visibleMethods.clear();

        this.calledMethodsInMethods.clear();
        this.connectedMethodsToVariables.clear();

        this.NDC = 0;
        this.NP  = 0;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Print methods">
    public void printVisibleMethods()
    {
        System.out.println("[TCC] Nombre de méthodes visibles = " + this.visibleMethods.size());
        for(String method : this.visibleMethods)
            System.out.println("Methode = " + method);
    }

    public void printMemberVariables()
    {
        System.out.println("[TCC] Nombre de variables membres = " + this.memberVariables.size());
        for(Map.Entry<String, Type> variable : this.memberVariables.entrySet())
            System.out.println(variable.getValue().toString() + " " + variable.getKey());
    }

    public void printConnectedMethodsToVariables()
    {
        System.out.println("[TCC] Méthode(s) qui accède(nt) (in)directement à une variable membre :");
        for(Map.Entry<String, Set<String>> entry : this.connectedMethodsToVariables.entrySet())
            System.out.println("      Variable = " + entry.getKey() + " --> methodes = " + entry.getValue());
    }

    public void printCalledMethodsInMethods()
    {
        System.out.println("[TCC] Liste des methodes membres appelees par des methodes membres visibles :");
        for(Map.Entry<String, Set<String>> entry : this.calledMethodsInMethods.entrySet())
            System.out.println("      Méthode appelante = " + entry.getKey() +  " --> Méthodes appelées = " + entry.getValue());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Public methods">
    public boolean containsMemberVariable(String variable)
    {
        return this.memberVariables.containsKey(variable);
    }

    public boolean containsLocalVariable(String variable)
    {
        return this.localVariables.containsKey(variable);
    }

    /* Regarde si une variable est une variable membre en tenant compte du fait
     * quelle peut être cachée par une variable locale)
     */
    public boolean isMemberVariable(String variable)
    {
        if (this.containsLocalVariable(variable))
            return false;

        return this.containsMemberVariable(variable);
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

    public boolean addVisibleMethod(final String methodDeclaration)
    {
        return this.visibleMethods.add(methodDeclaration);
    }

    public void setCurrentMethod(final String currentMethodPrototype) throws TCCException
    {
        if (!this.visibleMethods.contains(currentMethodPrototype))
            throw new TCCException("La méthode courante renseignée n'existe pas : "
                + currentMethodPrototype);

        this.currentMethod = currentMethodPrototype;
        this.localVariables.clear();
    }

    public String getCurrentMethod()
    {
        return this.currentMethod;
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

    public Type getTypeOfVariable(String variable) throws TCCException
    {
        // Local variables hide member variables
        for(Map.Entry<String, Type> variableType : this.localVariables.entrySet())
            if (variableType.getKey().equals(variable))
                return variableType.getValue();

        // Search in member variables after local ones
        for(Map.Entry<String, Type> variableType : this.memberVariables.entrySet())
            if (variableType.getKey().equals(variable))
                return variableType.getValue();

        // this should never happen
        throw new TCCException("Erreur de programmation : variable "
            + variable + " inexistante");
    }

    // La méthode courante fait appel à une autre méthode membre
    public void addMemberMethodCall(String calledMemberMethodPrototype) throws TCCException
    {
        if(this.currentMethod == null || this.currentMethod.isEmpty())
            throw new TCCException("La méthode actuelle n'est pas valide");

        if (!this.visibleMethods.contains(calledMemberMethodPrototype))
            throw new TCCException("Methode membre \"" + calledMemberMethodPrototype +"\" inexistante");

        // Skip recursive call
        if (calledMemberMethodPrototype.equals(this.currentMethod))
            return;

        if (!this.calledMethodsInMethods.containsKey(this.currentMethod))
            this.calledMethodsInMethods.put(this.currentMethod, new HashSet<String>());

        // Ajout du prototype de la méthode membre qui est appelée dans la méthode courante
        this.calledMethodsInMethods.get(this.currentMethod).add(calledMemberMethodPrototype);
    }

    public void addMemberVariableAccess(String variable) throws TCCException
    {
        if(this.currentMethod == null || this.currentMethod.isEmpty())
            throw new TCCException("La méthode actuelle n'est pas valide");

        if (!this.isMemberVariable(variable))
            throw new TCCException("Variable membre \"" + variable +"\" inexistante");

        if (!this.connectedMethodsToVariables.containsKey(variable))
            this.connectedMethodsToVariables.put(variable, new HashSet<String>());

        // Ajout du prototype
        this.connectedMethodsToVariables.get(variable).add(this.currentMethod);
    }

    public int getNP()
    {
        return this.NP;
    }

    public int getNDC()
    {
        return this.NDC;
    }
    //</editor-fold>
}
