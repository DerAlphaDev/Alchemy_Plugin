package de.darkkmc.darkmc_alchemy.events;

import de.darkkmc.darkmc_alchemy.commands.Alchemy;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

public class InventoryMoveItem implements Listener {

    @EventHandler
    public void onInventoryMoveItem(InventoryMoveItemEvent event) {
        if(event.getSource() == Alchemy.getInvMain() || event.getSource() == Alchemy.getInvSchadensBuff() || event.getSource() == Alchemy.getInvSupportBuffs()) {
            event.setCancelled(true);
        }
    }
}
