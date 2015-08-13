/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package utils;

import java.util.Objects;

/**
 *
 * @author Nakim
 */
public class Pair<F, S>
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    protected final F first;    // first member of pair
    protected final S second;   // second member of pair
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Static methods">
    public static <A, B> Pair <A, B> create(A a, B b)
    {
        return new Pair<>(a, b);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Pair(F first, S second)
    {
        this.first = first;
        this.second = second;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public F getFirst()
    {
        return first;
    }

    public S getSecond()
    {
        return second;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrided methods">
    @Override
    public String toString()
    {
        return "[First = " + first + ", Second = " + second + "]";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Pair))
            return false;

        Pair<?, ?> p = (Pair<?, ?>)obj;

        return (Objects.equals(p.first, this.first) && Objects.equals(p.second, this.second)) ||
               (Objects.equals(p.first, this.second) && Objects.equals(p.second, this.first));
    }

    @Override
    public int hashCode()
    {
        return (first == null ? 0 : first.hashCode()) ^
               (second == null ? 0 : second.hashCode());
    }
    //</editor-fold>
}
