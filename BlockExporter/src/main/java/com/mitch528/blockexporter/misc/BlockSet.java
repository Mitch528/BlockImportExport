/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitch528.blockexporter.misc;

import org.bukkit.util.Vector;

/**
 *
 * @author Mitch528
 */
public class BlockSet
{

    private Vector firstBlock;
    private Vector secondBlock;

    public BlockSet()
    {
    }

    public Vector getFirstBlock()
    {
        return firstBlock;
    }

    public void setFirstBlock(Vector firstBlock)
    {
        this.firstBlock = firstBlock;
    }

    public Vector getSecondBlock()
    {
        return secondBlock;
    }

    public void setSecondBlock(Vector secondBlock)
    {
        this.secondBlock = secondBlock;
    }
}
