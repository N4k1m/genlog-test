/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package utils;

/**
 *
 * @author Nakim
 */
public enum ReturnValue
{
    SUCCESS(0),
    FAILURE(-1);

    private final int returnCode;

    private ReturnValue(int returnCode)
    {
        this.returnCode = returnCode;
    }

    public int getReturnCode()
    {
        return returnCode;
    }
}
