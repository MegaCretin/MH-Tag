package fr.megacretin.htag.TagsUtils;

import fr.megacretin.htag.Main;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class UtilsTags {
    public UtilsTags() {
    }

    public static void TagsMenu(String name, Inventory inv, Integer slot) {
        ItemStack i = new ItemStack(Material.NAME_TAG);
        ItemMeta i2 = i.getItemMeta();
        i2.setDisplayName(name.replace("&", "§"));
        i2.setLore(Arrays.asList("", "§aLes tags sont des éléments", "§ade décorations ils sont visibles", "§auniquement quand tu parle.", "", "§3Tu peux les activer et", "§3désactiver quand tu veux.", "", "Site: highsky.fr"));
        i.setItemMeta(i2);
        inv.setItem(slot, i);

    }


}