package com.singlemace;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class SingleMace extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();

        getLogger().info("SingleMace enabled!");
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {

        ItemStack result = event.getInventory().getResult();

        if (result == null) {
            return;
        }

        if (result.getType().name().equals("MACE")) {

            if (getConfig().getBoolean("mace-crafted", false)) {

                event.getInventory().setResult(null);

            } else {

                getConfig().set("mace-crafted", true);
                saveConfig();
            }
        }
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {

        if (command.getName().equalsIgnoreCase("macecrafted")) {

            if (getConfig().getBoolean("mace-crafted", false)) {

                sender.sendMessage(
                    ChatColor.RED +
                    "Mace on jo craftattu tällä serverillä."
                );

            } else {

                sender.sendMessage(
                    ChatColor.GREEN +
                    "Macea ei ole vielä craftattu."
                );
            }

            return true;
        }

        return false;
    }
}