/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */

package network;

import java.util.HashMap;

/**
 *
 * @author Nakim
 */
public abstract class AbstractPacket implements Packet
{
    //<editor-fold defaultstate="collapsed" desc="Variables declaration">
    protected Enum command;
    protected HashMap<Enum, Object> headers;
    protected Enum status;
    protected byte[] payload;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Enum">
    protected enum Command
    {
        // Empty. Need to be overrided
    }

    protected enum Header
    {
        // Empty. Need to be overrided
    }

    protected enum Status
    {
        SUCCESS,
        FAILURE
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public AbstractPacket()
    {
        this.headers = new HashMap<>();
        this.command = null;
        this.status  = null;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrided methods">
    @Override
    public Enum getCommand()
    {
        return this.command;
    }

    @Override
    public void setCommand(Enum command)
    {
        this.command = command;
    }

    @Override
    public boolean is(Enum command)
    {
        return this.command == command;
    }

    @Override
    public Object getHeader(Enum header)
    {
        return this.headers.get(header);
    }

    @Override
    public HashMap<Enum, Object> getHeaders()
    {
        return this.headers;
    }

    @Override
    public void addHeader(Enum header, Object value)
    {
        this.headers.put(header, value);
    }

    @Override
    public void addHeaders(HashMap<Enum, Object> values)
    {
        this.headers.putAll(values);
    }

    @Override
    public void setHeaders(HashMap<Enum, Object> headers)
    {
        this.headers = headers;
    }

    @Override
    public void clearHeaders()
    {
        this.headers.clear();
    }

    @Override
    public Enum getStatus()
    {
        return this.status;
    }

    @Override
    public void setStatus(Enum status)
    {
        this.status = status;
    }

    @Override
    public void setPayload(byte[] message)
    {
        this.payload = message;
    }

    @Override
    public byte[] getPayload()
    {
        return this.payload;
    }
    //</editor-fold>
}