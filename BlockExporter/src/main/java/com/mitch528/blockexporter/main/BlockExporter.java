package com.mitch528.blockexporter.main;

import com.mitch528.blockexporter.listeners.PlayerListener;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Mitch528
 */
public class BlockExporter extends JavaPlugin
{

    @Override
    public void onDisable()
    {
        
        HandlerList.unregisterAll(this);
        
    }

    @Override
    public void onEnable()
    {

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

    }
}
