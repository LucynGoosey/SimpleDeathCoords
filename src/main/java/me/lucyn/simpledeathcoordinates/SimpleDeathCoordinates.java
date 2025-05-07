package me.lucyn.simpledeathcoordinates;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.World.Environment.*;

public final class SimpleDeathCoordinates extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        this.saveDefaultConfig(); // creates the config file
        getServer().getPluginManager().registerEvents(this, this); // registers the death listener
    }
    @Override
    public void onDisable() {}

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity(); //gets the player that died
        if(!player.hasPermission("simpledeathcoordinates.use")) return; //checks if the player has permission to use the plugin
        String message = getConfig().getString("message"); //gets the message out of the config file

            message = message.replaceAll("%x%", String.valueOf(player.getLocation().getBlockX())); //replaces the placeholder with the x coordinate

            message = message.replaceAll("%y%", String.valueOf(player.getLocation().getBlockY())); //replaces the placeholder with the y coordinate

            message = message.replaceAll("%z%", String.valueOf(player.getLocation().getBlockZ())); //replaces the placeholder with the z coordinate

            message = message.replaceAll("%world%", player.getLocation().getWorld().getName()); //replaces the placeholder with the world name

        String dimension = "";

        if(player.getWorld().getEnvironment() == NETHER) dimension = getConfig().getString("NETHER"); //gets the nether message from the config file

        else if (player.getWorld().getEnvironment() == NORMAL) dimension = getConfig().getString("NORMAL"); //gets the normal message from the config file


        else if (player.getWorld().getEnvironment() == THE_END) dimension = getConfig().getString("THE_END"); //gets the end message from the config file

        else if(player.getWorld().getEnvironment() == CUSTOM) dimension = getConfig().getString("CUSTOM"); //gets the custom message from the config file

            message = message.replaceAll("%dimension%", dimension); //replaces the placeholder with the dimension id

        player.sendMessage(message); //sends the message to the player


    }
}