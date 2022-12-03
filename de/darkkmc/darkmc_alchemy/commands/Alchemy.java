package de.darkkmc.darkmc_alchemy.commands;

import de.darkkmc.darkmc_alchemy.utils.InvTypes;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class Alchemy implements CommandExecutor {

    private static ArrayList<Integer> emptyMain = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 17, 26, 25, 24, 23, 22, 21, 20, 19, 18, 9, 10, 11, 13, 15, 16)),
                                      emptySchadensBuff = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 17, 26, 25, 24, 23, 22, 21, 20, 19, 18, 9, 10, 11, 12, 13, 14, 15, 16));;
    private static Inventory invMain, invSchadensBuff, invSupportBuffs;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            invMain = Bukkit.createInventory(null, 27, "Alchemy");
            invSchadensBuff = Bukkit.createInventory(null, 27, "Schadensbuffs");
            invSupportBuffs = Bukkit.createInventory(null, 27, "Support Buffs");

            Player p = (Player) commandSender;

            builder(InvTypes.MAIN_MENU, p);
        } else {
            commandSender.sendMessage("Dieser Befehl kann nur von Spielern ausgefÃ¼hrt werden!");
        }
        return true;
    }

    public static void builder(InvTypes type, Player p) {
        if(type == InvTypes.MAIN_MENU) {
            buildMainMenu(invMain, p);
        } else if(type == InvTypes.SCHADENS_BUFFS) {
            buildSchadensBuffs(invSchadensBuff, p);
        } else if(type == InvTypes.SUPPORT_BUFFS) {
            buildSupportBuffs(invSupportBuffs, p);
        }
    }

    public static Inventory getInvMain() {
        return invMain;
    }

    public static Inventory getInvSchadensBuff() {
        return invSchadensBuff;
    }

    public static Inventory getInvSupportBuffs() {
        return invSupportBuffs;
    }

    private static void buildMainMenu(Inventory inv, Player p) {
        p.openInventory(inv);
        new Thread(() -> {
            try {
                for(Integer i : emptyMain) {
                    if(i == 0) {
                        inv.setItem(i, addItem(Material.GRAY_STAINED_GLASS_PANE, " ", null));
                    } else if(i == 26) {
                        inv.setItem(i, addItem(Material.RED_STAINED_GLASS_PANE, "SCHLIEßEN", null));
                    } else {
                        Thread.sleep(50);
                        inv.setItem(i, addItem(Material.GRAY_STAINED_GLASS_PANE, " ", null));
                    }
                }

                Thread.sleep(200);
                inv.setItem(12, addItem(Material.POTION, "Schadensbuffs", new String[]{
                        ChatColor.DARK_GRAY + "Schadensbuffs verstärken deine ",
                        ChatColor.DARK_GRAY + "Basisattacken: Es können nur 2 ",
                        ChatColor.DARK_GRAY + "Schadensbuffs gleichzeitig ",
                        ChatColor.DARK_GRAY + "aktiv sein. Basisattacken werden ",
                        ChatColor.DARK_GRAY + "entweder mit einem schlichten ",
                        ChatColor.DARK_GRAY + "Schadensbonus, oder mit einem ",
                        ChatColor.DARK_GRAY + "Debuff (welcher der Gegner ",
                        ChatColor.DARK_GRAY + "bekommt), verstärkt."
                }));
                Thread.sleep(200);
                inv.setItem(14, addItem(Material.POTION, "Support Buffs", new String[]{
                        ChatColor.DARK_GRAY + "Support Buffs verstärken die ",
                        ChatColor.DARK_GRAY + "Überlebensfähigkeit des Spielers."
                }));
                Thread.currentThread().interrupt();
            } catch(InterruptedException e) {

            }
        }).start();
    }

    private static void buildSupportBuffs(Inventory inv, Player p) {
        p.openInventory(inv);
        new Thread(() -> {
            try {
                for(Integer i : emptySchadensBuff) {
                    if(i == 0) {
                        inv.setItem(i, addItem(Material.GRAY_STAINED_GLASS_PANE, " ", null));
                    } else if(i == 26) {
                        inv.setItem(i, addItem(Material.RED_STAINED_GLASS_PANE, "ZURÜCK", null));
                    } else {
                        Thread.sleep(50);
                        inv.setItem(i, addItem(Material.GRAY_STAINED_GLASS_PANE, " ", null));
                    }
                }

                Thread.sleep(200);
                inv.setItem(13, addItem(Material.POTION, "Schild Trank", new String[]{
                        ChatColor.DARK_GRAY + "Du erhälst einen Absorbationsschild",
                        ChatColor.DARK_GRAY + "Zutaten: 3 x Schildkarotten",
                        ChatColor.DARK_GRAY + "Zeit: 20sek"
                }));
                Thread.currentThread().interrupt();
            } catch (InterruptedException e) {

            }
        }).start();
    }

    private static void buildSchadensBuffs(Inventory inv, Player p) {
        p.openInventory(inv);
        new Thread(() -> {
            try {
                for(Integer i : emptySchadensBuff) {
                    if(i == 0) {
                        inv.setItem(i, addItem(Material.GRAY_STAINED_GLASS_PANE, " ", null));
                    } else if(i == 26) {
                        Thread.sleep(50);
                        inv.setItem(i, addItem(Material.RED_STAINED_GLASS_PANE, "ZURÜCK", null));
                    } else {
                        Thread.sleep(50);
                        inv.setItem(i, addItem(Material.GRAY_STAINED_GLASS_PANE, " ", null));
                    }
                }

                Thread.sleep(200);
                inv.setItem(12, addItem(Material.POTION, "Verdorbener Trank", new String[]{
                    ChatColor.DARK_GRAY + "Deine Angriffe fügen Gegnern den ",
                    ChatColor.DARK_GRAY + "Korruptions Debuff zu.",
                    ChatColor.DARK_GRAY + "Zutaten: 2 x Albtraumfragment",
                    ChatColor.DARK_GRAY + "          2 x Verdorbene Beere",
                    ChatColor.DARK_GRAY + "Zeit: 40min"
                }));
                Thread.sleep(200);
                inv.setItem(14, addItem(Material.POTION, "Finsterer Trank", new String[]{
                    ChatColor.DARK_GRAY + "Angriffe geben dem Gegner einen Stapel ",
                    ChatColor.DARK_GRAY + "\"Dunkelheit\". Bei 5 Stapeln wird dein ",
                    ChatColor.DARK_GRAY + "Gegner bewegungsunfähig.",
                    ChatColor.DARK_GRAY + "Zutaten: 4 x Dunkle Asche",
                    ChatColor.DARK_GRAY + "         2 x Verdorbene Beere",
                    ChatColor.DARK_GRAY + "Zeit: 10min"
                }));
                Thread.currentThread().interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static ItemStack addItem(Material material, String name, String[] lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);

        try {
            meta.setLore(Arrays.asList(lore));
        } catch (NullPointerException e) {
            meta.setLore(null);
        }

        item.setItemMeta(meta);

        return item;
    }
}
