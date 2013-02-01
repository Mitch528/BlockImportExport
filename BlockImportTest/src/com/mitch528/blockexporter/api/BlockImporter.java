package com.mitch528.blockexporter.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.Block;

/**
 *
 * @author Mitch528
 */
public class BlockImporter
{

    public static List<NiceBlock> getNBlocks(String blocksDat) throws IOException, ClassNotFoundException
    {

        ObjectInputStream ios = new ObjectInputStream(BlockImporter.class.getResourceAsStream("/" + blocksDat));

        Object nbsObj = ios.readObject();

        List<NiceBlock> niceBlocks = (List<NiceBlock>) nbsObj;

        ios.close();

        return niceBlocks;

    }

    public static List<Block> placeBlocks(String blocksDat, Location loc) throws IOException, ClassNotFoundException
    {
        return placeBlocks(getNBlocks(blocksDat), loc);
    }

    public static List<Block> placeBlocks(List<NiceBlock> nBlocks, Location loc)
    {

        List<Block> blocks = new ArrayList<Block>();

        for (NiceBlock nb : nBlocks)
        {

            Location lc = new Location(loc.getWorld(), nb.getX() + loc.getBlockX(), nb.getY() + loc.getBlockY(), nb.getZ() + loc.getBlockZ());

            Block block = lc.getWorld().getBlockAt(lc);
            block.setTypeId(nb.getBlockType());
            block.setData(nb.getData());

            blocks.add(block);

        }

        return blocks;

    }
}