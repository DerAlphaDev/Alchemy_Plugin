package de.darkkmc.darkmc_alchemy;

import creativecrafting.storycrafting.gameplay.Effects.Debuffs.DarknessDebuff;
import de.darkkmc.darkmc_alchemy.commands.Alchemy;
import de.darkkmc.darkmc_alchemy.commands.Items;
import de.darkkmc.darkmc_alchemy.events.InventoryClick;
import de.darkkmc.darkmc_alchemy.events.InventoryDrag;
import de.darkkmc.darkmc_alchemy.events.InventoryMoveItem;
import de.darkkmc.darkmc_alchemy.events.PlayerInteract;
import de.darkkmc.darkmc_alchemy.utils.AlchemyItems;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class AlchemyMain extends JavaPlugin {

    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getServer().getPluginManager().registerEvents(new InventoryMoveItem(), this);
        getServer().getPluginManager().registerEvents(new InventoryDrag(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);

        getCommand("alchemy").setExecutor(new Alchemy());
        getCommand("items").setExecutor(new Items());
    }

    @Override
    public void onDisable() {super.onDisable();
    }
}
