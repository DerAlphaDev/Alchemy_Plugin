package de.darkkmc.darkmc_alchemy.events;

import com.comphenix.protocol.PacketType;
import creativecrafting.storycrafting.gameplay.Effects.Buffs.ShieldedBuff;
import creativecrafting.storycrafting.gameplay.Effects.Debuffs.CorruptionDebuff;
import creativecrafting.storycrafting.gameplay.Effects.Debuffs.DarknessDebuff;
import de.darkkmc.darkmc_alchemy.utils.AlchemyItems;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerInteract implements Listener {

    private static HashMap<Player, Thread> threads = new HashMap<>();

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player p = event.getPlayer();
            ItemStack heldItem = p.getInventory().getItemInMainHand();

            if (heldItem.getItemMeta().equals(AlchemyItems.getSCHILD_TRANK().getItemMeta())) {
                new ShieldedBuff(p, 120, 20);
                cooldown(p);
            } else if (heldItem.getItemMeta().equals(AlchemyItems.getFINSTERER_TRANK().getItemMeta())) {
                World w = p.getWorld();
                ThrownPotion thrownPotion = p.launchProjectile(ThrownPotion.class);
                thrownPotion.setItem(createSplashPotion());
                cooldown(p);

                for (Player target : getPlayersInPotionRange(w, thrownPotion.getLocation(), 10, p, false)) {
                    new DarknessDebuff(target, 5, 5);
                }
            } else if(heldItem.getItemMeta().equals(AlchemyItems.getVERDORBENER_TRANK())) {
                World w = p.getWorld();
                ThrownPotion thrownPotion = p.launchProjectile(ThrownPotion.class);
                thrownPotion.setItem(createSplashPotion());
                cooldown(p);

                for (Player target : getPlayersInPotionRange(w, thrownPotion.getLocation(), 10, p, false)) {
                    new CorruptionDebuff(p, target, 2, 10);
                }
            }
        }
    }

    private void cooldown(Player p) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }v 
        });

        try {
            threads.put(p, t);
            t.start();
            t.join();
            threads.remove(p);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<Player> getPlayersInPotionRange(World w, Location loc, Integer range, Player thrower, Boolean ownHit) {
        List<Player> players = w.getPlayers();
        List<Player> returnList = new ArrayList<>();

        for(Player p : players) {
            if(p.getLocation().distance(loc) > Double.parseDouble(range.toString())) {
                continue;
            } else {
                if(!ownHit) {
                    if(p == thrower) {
                        continue;
                    } else {
                        returnList.add(p);
                    }
                } else {
                    returnList.add(p);
                }
            }
        }

        return returnList;
    }

    private ItemStack createSplashPotion() {
        ItemStack item = new ItemStack(Material.SPLASH_POTION);
        PotionMeta meta = (PotionMeta) item.getItemMeta();

        meta.setDisplayName("null");
        meta.setLore(null);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        meta.clearCustomEffects();
        meta.addCustomEffect(new PotionEffect(PotionEffectType.LUCK, 1, 0, false, false), true);

        item.setItemMeta(meta);

        return item;
    }
}
