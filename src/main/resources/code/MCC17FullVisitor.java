/*
 * Institut Supérieur Industriel Liègeois - Département ingénieurs industriels.
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package code;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Nakim
 */
public class MCC17FullVisitor
{
    // Constructeur : Complexite = 1
    public MCC17FullVisitor()
    {
        // Sauvegarde : MCC = 1 pour la methode MMC()
    }

    // Nouvelle méthode : Complexite = 1
    public void method1(int a)
    {
        // if statement : Complexite + 1
        if (a > 0)
            System.out.println("a positif");

        // while statement : Complexite + 1
        while(a < 100)
            a++;

        // Sauvegarde : MCC = 3 pour la methode method1()
    }

    // Nouvelle méthode : Complexite = 1
    public void method2(int a)
    {
        // do...while statement : Complexite + 1
        do { a++; } while(a < 100);

        // for statement : Complexite + 1
        for (a = 0; a < 10; a++)
            System.out.println("Increment");

        // Random [1-100]
        a = new Random().nextInt((100 - 1) + 1) + 1;

        // switch statement : Complexite + 1
        switch(a)
        {
            // case statement --> Complexite + 1
            case 1:
                System.out.println("a = 1");
                break;
            // case statement --> Complexite + 1
            case 100:
                System.out.println("a = 100");
                break;
            default:
        }

        // Sauvegarde : MCC = 6 pour la methode method2()
    }

    // Nouvelle méthode : Complexite = 1
    // throws clause : Complexite + 1
    public void method3(List<String> words) throws Exception
    {
        // if statement : Complexite + 1
        // expression logique && ou || --> complexite + 1
        if (words == null || words.isEmpty())
            return;

        // foreach statement : Complexite + 1
        for(String word : words)
            System.out.println("Word = " + word);

        try
        {
            // Something ...
        }
        // catch clause --> Complexite + 1
        catch (Exception e)
        {
        }

        // throw statement --> Complexite + 1
        throw new Exception("Exception");

        // Sauvegarde : MCC = 7 pour la methode method3()
    }
}