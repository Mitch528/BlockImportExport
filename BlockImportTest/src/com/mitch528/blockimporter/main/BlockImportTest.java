/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitch528.blockimporter.main;

import com.mitch528.blockimporter.listeners.PlayerListener;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Mitch528
 */
public class BlockImportTest extends JavaPlugin
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
