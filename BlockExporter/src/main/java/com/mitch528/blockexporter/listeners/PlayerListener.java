package com.mitch528.blockexporter.listeners;

import com.mitch528.blockexporter.misc.BlockSet;
import com.mitch528.blockexporter.api.NiceBlock;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;

/**
 *
 * @author Mitch528
 */
public class PlayerListener implements Listener
{

    private HashMap<String, BlockSet> bSets;

    public PlayerListener()
    {
        bSets = new HashMap<String, BlockSet>();
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent evt)
    {

        Player p = evt.getPlayer();

        String fCmd = evt.getMessage();

        String cmd = fCmd.substring(1).split(" ", 1)[0];

        String[] args = fCmd.substring(cmd.length() + 1).split(" ");

        if (cmd.equalsIgnoreCase("startexport"))
        {

            p.sendMessage("Starting export!");

            bSets.put(p.getName(), new BlockSet());

            evt.setCancelled(true);

        }
        else if (cmd.equalsIgnoreCase("export"))
        {

            BlockSet bs = bSets.get(p.getName());

            if (bs == null)
            {

                p.sendMessage("You must first use the /startexport command!");

                evt.setCancelled(true);

                return;

            }

            p.sendMessage("Exporting...");

            List<NiceBlock> niceBlocks = new ArrayList<NiceBlock>();


            Vector firstBlock = bs.getFirstBlock();
            Vector secondBlock = bs.getSecondBlock();

            World w = p.getWorld();

            //http://forums.bukkit.org/threads/block-listed-within-cuboid-x-y-coodinates.103681/

            int topBlockX = (firstBlock.getBlockX() < secondBlock.getBlockX() ? secondBlock.getBlockX() : firstBlock.getBlockX());
            int bottomBlockX = (firstBlock.getBlockX() > secondBlock.getBlockX() ? secondBlock.getBlockX() : firstBlock.getBlockX());

            int topBlockY = (firstBlock.getBlockY() < secondBlock.getBlockY() ? secondBlock.getBlockY() : firstBlock.getBlockY());
            int bottomBlockY = (firstBlock.getBlockY() > secondBlock.getBlockY() ? secondBlock.getBlockY() : firstBlock.getBlockY());

            int topBlockZ = (firstBlock.getBlockZ() < secondBlock.getBlockZ() ? secondBlock.getBlockZ() : firstBlock.getBlockZ());
            int bottomBlockZ = (firstBlock.getBlockZ() > secondBlock.getBlockZ() ? secondBlock.getBlockZ() : firstBlock.getBlockZ());

            int xCtr = 0;

            for (int x = bottomBlockX; x <= topBlockX; x++)
            {

                int zCtr = 0;

                for (int z = bottomBlockZ; z <= topBlockZ; z++)
                {

                    int yCtr = 0;

                    for (int y = bottomBlockY; y <= topBlockY; y++)
                    {

                        Block b = w.getBlockAt(x, y, z);

                        MaterialData data = b.getState().getData();

                        NiceBlock nb = new NiceBlock(b.getTypeId(), xCtr, yCtr, zCtr, data.getData());

                        niceBlocks.add(nb);

                        yCtr++;

                    }

                    zCtr++;
                }

                xCtr++;

            }

            File exportFolder = new File("block_exports");

            if (!exportFolder.exists())
            {
                exportFolder.mkdir();
            }

            try
            {

                File datFile = new File(exportFolder, "export.dat");

                if (!datFile.exists())
                {
                    datFile.createNewFile();
                }

                FileOutputStream fos = new FileOutputStream(datFile);

                ObjectOutputStream oos = new ObjectOutputStream(fos);

                oos.writeObject(niceBlocks);
                oos.close();

            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            p.sendMessage("Done!");

            bSets.remove(p.getName());

            evt.setCancelled(true);

        }

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent evt)
    {

        Player plr = evt.getPlayer();

        BlockSet bSet = bSets.get(plr.getName());

        if (evt.getAction() == Action.LEFT_CLICK_BLOCK && bSet != null)
        {

            Vector v = evt.getClickedBlock().getLocation().toVector().clone();


            if (bSet.getFirstBlock() == null)
            {
                plr.sendMessage("First block set!");
                bSet.setFirstBlock(v);
            }
            else if (bSet.getSecondBlock() == null)
            {
                plr.sendMessage("Second block set!");
                bSet.setSecondBlock(v);
            }
            else
            {
                bSets.remove(plr.getName());
            }

        }

    }
}
