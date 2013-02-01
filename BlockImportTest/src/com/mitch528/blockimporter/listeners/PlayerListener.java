package com.mitch528.blockimporter.listeners;

import com.mitch528.blockexporter.api.BlockImporter;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 *
 * @author Mitch528
 */
public class PlayerListener implements Listener
{

    private List<String> playersPlacingBlocks;

    public PlayerListener()
    {
        this.playersPlacingBlocks = new ArrayList<String>();
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent evt)
    {

        Player p = evt.getPlayer();

        String fCmd = evt.getMessage();

        String cmd = fCmd.substring(1).split(" ", 1)[0];

        if (cmd.equalsIgnoreCase("placeblocks"))
        {

            if (!playersPlacingBlocks.contains(p.getName()))
            {
                
                playersPlacingBlocks.add(p.getName());

                p.sendMessage("Click on a block!");

            }

            evt.setCancelled(true);

        }

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent evt)
    {

        Player plr = evt.getPlayer();

        if (playersPlacingBlocks.contains(plr.getName()))
        {

            Block clickedBlock = evt.getClickedBlock();

            try
            {
                BlockImporter.placeBlocks("export.dat", clickedBlock.getLocation());
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            playersPlacingBlocks.remove(plr.getName());

        }

    }
}
