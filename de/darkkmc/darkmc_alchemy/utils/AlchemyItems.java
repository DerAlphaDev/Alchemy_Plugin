package de.darkkmc.darkmc_alchemy.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlchemyItems {

    private static ItemStack ALBTRAUMFRAGMENT = createItem(Material.AMETHYST_SHARD, "Albtraumfragment", 2, new String[]{ChatColor.DARK_GRAY + "Ein mächtiges Fragment, welches sicherlich nützlich ist."}, null, null, null),
                             GROßES_SPINNENAUGE = createItem(Material.SPIDER_EYE, "Großes Spinnenauge", 5,  new String[]{ChatColor.DARK_GRAY + "Wahrlich ekelhaft"}, null, null, null),
                             SCHILDKAROTTE = createItem(Material.CARROT, "Schildkarotte", 1, new String[]{ChatColor.DARK_GRAY + "Eine Karotte? Welche schützenden Eigenschaften soll die schon haben?"}, null, null, null),
                             NETHERFLAMMEN = createItem(Material.BLAZE_POWDER, "Netherflammen", 12, new String[]{ChatColor.DARK_GRAY + "Eine unendliche Flamme..."}, null, null, null),
                             DUNKLE_ASCHE = createItem(Material.GUNPOWDER, "Dunkle Asche", 1, new String[]{ChatColor.DARK_GRAY + "Asche aus purer Dunkelheit"}, null, null, null),
                             VERDORBENE_BEERE = createItem(Material.SWEET_BERRIES, "Verdorbene Beere", 1, new String[]{ChatColor.DARK_GRAY + "Eine Beere verdorben von Korruption"}, null, null, null),
                             VERDORBENER_TRANK = createItem(Material.DRAGON_BREATH, "Verdorbener Trank", 1, new String[]{ChatColor.DARK_GRAY + "Fügt dem Gegner den Korruptions Debuff zu"}, "Korruption", 40, "min"),
                             FINSTERER_TRANK = createItem(Material.DRAGON_BREATH, "Finsterer Trank", 2, new String[]{ChatColor.DARK_GRAY + "Fügt dem Gegner nach 5 hits den Darkness Debuff zu"}, "Dunkelheit", 10, "min"),
                             SCHILD_TRANK = createItem(Material.DRAGON_BREATH, "Schild Trank", 3, new String[]{ChatColor.DARK_GRAY + "Gibt dir ein Absorbationsschild"}, "Absorbationsschild", 20, "sek");;


    public static ItemStack getALBTRAUMFRAGMENT() {
        return ALBTRAUMFRAGMENT;
    }

    public static ItemStack getGROßES_SPINNENAUGE() {
        return GROßES_SPINNENAUGE;
    }

    public static ItemStack getSCHILDKAROTTE() {
        return SCHILDKAROTTE;
    }

    public static ItemStack getNETHERFLAMMEN() {
        return NETHERFLAMMEN;
    }

    public static ItemStack getDUNKLE_ASCHE() {
        return DUNKLE_ASCHE;
    }

    public static ItemStack getVERDORBENE_BEERE() {
        return VERDORBENE_BEERE;
    }

    public static ItemStack getVERDORBENER_TRANK() { return VERDORBENER_TRANK; }

    public static ItemStack getFINSTERER_TRANK() { return FINSTERER_TRANK; }

    public static ItemStack getSCHILD_TRANK() { return SCHILD_TRANK; }

    public static ItemStack[] getAll() {
        return new ItemStack[]{ALBTRAUMFRAGMENT, GROßES_SPINNENAUGE, SCHILDKAROTTE, NETHERFLAMMEN, DUNKLE_ASCHE, VERDORBENE_BEERE, VERDORBENER_TRANK, FINSTERER_TRANK, SCHILD_TRANK};
    }

    private static ItemStack createItem(Material material, String name, Integer modelData, String[] lore, String effektName, Integer effektDuration, String einheit) {
        if(effektName != null && effektDuration != null && einheit != null) {
            ItemStack item = new ItemStack(material);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(name);
            meta.addEnchant(Enchantment.DURABILITY, 255, true);
            meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
            meta.setCustomModelData(modelData);

            try {
                meta.setLore(createPotionLore(lore, effektName, effektDuration, einheit));
            } catch (NullPointerException e) {
                meta.setLore(null);
            }

            item.setItemMeta(meta);

            return item;
        } else {
            ItemStack item = new ItemStack(material);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(name);
            meta.setCustomModelData(modelData);

            try {
                meta.setLore(Arrays.asList(lore));
            } catch (NullPointerException e) {
                meta.setLore(null);
            }

            item.setItemMeta(meta);

            return item;
        }
    }

    private static List<String> createPotionLore(String[] lore, String name, Integer duration, String einheit) {
        if(lore != null) {
            ArrayList<String> finalLore = new ArrayList<>();
            for(String s : lore) {
                finalLore.add(s);
            }

            finalLore.add(ChatColor.DARK_GRAY + "Effekt: " + name);
            finalLore.add(ChatColor.DARK_GRAY + "Braudauer: " + duration + einheit);

            return finalLore;
        } else {
            return Arrays.asList(new String[] {
                    ChatColor.DARK_GRAY + "Effekt: " + name,
                    ChatColor.DARK_GRAY + "Braudauer: " + duration + einheit
            });
        }
    }

}
