/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package code;

public class ATFD1
{
    public void m1(Avion test)
    {
        Voiture maVoiture;

    	m2();
    }

    public void m2()
    {
        monCamion.poids++;

        monCamion.stop();

        a = 1;
    }

    public void m3()
    {
        System.out.println("Poids = " + monCamion.poids);
        System.out.getClass();
        a = 2;
        b = 1;
    }

    public void m4()
    {

    }

    public void m5()
    {
        m4();

        b = 2;
    }

    public class InnerClass
    {

    }

    private int a;
    private int b, c;
    private Camion monCamion;
}