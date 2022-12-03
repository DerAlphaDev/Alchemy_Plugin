package de.darkkmc.darkmc_alchemy.events;

import de.darkkmc.darkmc_alchemy.commands.Alchemy;
import de.darkkmc.darkmc_alchemy.utils.AlchemyItems;
import de.darkkmc.darkmc_alchemy.utils.InvTypes;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getWhoClicked() instanceof Player && !event.getInventory().getType().equals(InventoryType.ANVIL)) {
            Player p = (Player) event.getWhoClicked();

            if(event.getInventory() == Alchemy.getInvMain()) {
                event.setCancelled(true);
                if(event.getRawSlot() == 12) {
                    Alchemy.builder(InvTypes.SCHADENS_BUFFS, p);
                } else if(event.getRawSlot() == 14) {
                    Alchemy.builder(InvTypes.SUPPORT_BUFFS, p);
                } else if(event.getRawSlot() == 26) {
                    p.closeInventory();
                }
            } else if(event.getInventory() == Alchemy.getInvSchadensBuff()) {
                event.setCancelled(true);
                if(event.getRawSlot() == 26) {
                    Alchemy.builder(InvTypes.MAIN_MENU, p);
                } else if(event.getRawSlot() == 12) {
                    if(p.getInventory().containsAtLeast(AlchemyItems.getALBTRAUMFRAGMENT(), 2) && p.getInventory().containsAtLeast(AlchemyItems.getVERDORBENE_BEERE(), 2)) {
                        for(ItemStack item : p.getInventory()) {
                            if(item.equals(AlchemyItems.getALBTRAUMFRAGMENT())) {
                                item.setAmount(item.getAmount() - 2);
                                p.updateInventory();
                            } else if(item.equals(AlchemyItems.getVERDORBENE_BEERE())) {
                                item.setAmount(item.getAmount() - 2);
                                p.updateInventory();
                            }
                        }

                        p.getInventory().addItem(AlchemyItems.getVERDORBENER_TRANK());
                    } else {
                        p.closeInventory();
                        p.sendMessage("Du hast nicht genug Items!");
                    }
                } else if(event.getRawSlot() == 14) {
                    if(p.getInventory().containsAtLeast(AlchemyItems.getDUNKLE_ASCHE(), 4) && p.getInventory().containsAtLeast(AlchemyItems.getVERDORBENE_BEERE(), 2)) {
                        for(ItemStack item : p.getInventory()) {
                            if(item.equals(AlchemyItems.getDUNKLE_ASCHE())) {
                                item.setAmount(item.getAmount() - 4);
                                p.updateInventory();
                            } else if(item.equals(AlchemyItems.getVERDORBENE_BEERE())) {
                                item.setAmount(item.getAmount() - 2);
                                p.updateInventory();
                            }
                        }

                        p.getInventory().addItem(AlchemyItems.getFINSTERER_TRANK());
                    } else {
                        p.closeInventory();
                        p.sendMessage("Du hast nicht genug Items!");
                    }
                }
            } else if(event.getInventory() == Alchemy.getInvSupportBuffs()) {
                event.setCancelled(true);
                if(event.getRawSlot() == 26) {
                    Alchemy.builder(InvTypes.MAIN_MENU, p);
                } else if(event.getRawSlot() == 12) {
                    if(p.getInventory().containsAtLeast(AlchemyItems.getSCHILDKAROTTE(), 3)) {
                        for(ItemStack item : p.getInventory()) {
                            if(item.equals(AlchemyItems.getSCHILDKAROTTE())) {
                                item.setAmount(item.getAmount() - 4);
                                p.updateInventory();
                            }
                        }

                        p.getInventory().addItem(AlchemyItems.getSCHILD_TRANK());
                    } else {
                        p.closeInventory();
                        p.sendMessage("Du hast nicht genug Items!");
                    }
                }
            }
        } else if(event.getWhoClicked() instanceof Player && event.getInventory().getType().equals(InventoryType.ANVIL)) {
            Player p = (Player) event.getWhoClicked();

            for(ItemStack item : AlchemyItems.getAll()) {
                if(event.getCurrentItem() != null) {
                    if(item == event.getCurrentItem()) {
                        p.closeInventory();
                        p.sendMessage("Du kannst dieses Item nicht im Amboss bearbeiten!");
                    }
                }
            }
        }
    }
}
