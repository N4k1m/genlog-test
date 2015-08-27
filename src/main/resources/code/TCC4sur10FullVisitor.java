/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */

package code;

class TCC4sur10FullVisitor
{
    public void methodA(String test)
    {
        if (test.isEmpty())
            return;

        this.methodB();

        methodG(x);
        methodG(this.x);

        String empty = "";
        this.methodA(empty);
    }

    public void methodB()
    {
        int w;
        w = 0;
        this.x = 1;
    }

    public void methodC()
    {
        x = 2;
        y = y.substring(x);
    }

    public void methodD()
    {
        this.y = this.y.toLowerCase();

        methodE();
    }

    public void methodE()
    {
        // No variables access
    }

    private void methodF(int var)
    {

    }

    public void methodG(int var)
    {
        methodH(var);
    }

    public void methodH(int var)
    {
        methodI(var);
    }

    public void methodI(int var)
    {
        this.y = String.valueOf(var);
        this.y = this.y.toUpperCase();

        int test = x;
    }

    private int x;
    private String y;
}