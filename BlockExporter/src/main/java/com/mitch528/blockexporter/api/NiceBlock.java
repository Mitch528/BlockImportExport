package com.mitch528.blockexporter.api;

import java.io.Serializable;

/**
 *
 * @author Mitch528
 */
public class NiceBlock implements Serializable
{

    private int x;
    private int y;
    private int z;
    private int blockType;
    private byte data;

    public NiceBlock()
    {
    }

    public NiceBlock(int blockType, int x, int y, int z, byte data)
    {
        this.blockType = blockType;
        this.x = x;
        this.y = y;
        this.z = z;
        this.data = data;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getZ()
    {
        return z;
    }

    public void setZ(int z)
    {
        this.z = z;
    }

    public int getBlockType()
    {
        return this.blockType;
    }

    public void setBlockType(int blockType)
    {
        this.blockType = blockType;
    }

    public byte getData()
    {
        return this.data;
    }

    public void setData(byte data)
    {
        this.data = data;
    }
}
