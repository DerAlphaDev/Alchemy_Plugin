package de.darkkmc.darkmc_alchemy.events;

import de.darkkmc.darkmc_alchemy.commands.Alchemy;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryDrag implements Listener {

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if(event.getInventory() == Alchemy.getInvMain() || event.getInventory() == Alchemy.getInvSchadensBuff() || event.getInventory() == Alchemy.getInvSupportBuffs()) {
            event.setCancelled(true);
        }
    }
}
