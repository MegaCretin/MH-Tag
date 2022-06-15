package fr.megacretin.htag.commands;

import fr.megacretin.htag.TagsUtils.Database;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import fr.megacretin.htag.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;
import java.util.Arrays;


public class TagCommand implements CommandExecutor {
    public TagCommand() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("give")) {
                String target = args[1];
                if (Bukkit.getPlayer(target) != null) {
                    int idTag = Integer.parseInt(args[2]);
                    String Tag = null;
                    try {
                        Tag = Database.RecupTag(idTag);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (Tag != null) {
                        Player targetPlayer = Bukkit.getPlayer(target);
                        String tag = Tag.replace("&", "§");

                        ItemStack prefix = new ItemStack(Material.NAME_TAG);
                        ItemMeta prefixm = prefix.getItemMeta();
                        prefixm.setDisplayName(tag);
                        prefixm.setLore(Arrays.asList("","Faites Clique-Droit pour l'ajouter a votre liste", ""));
                        prefix.setItemMeta(prefixm);
                        targetPlayer.getInventory().addItem(prefix);
                        targetPlayer.updateInventory();

                        if(sender instanceof Player){
                            sender.sendMessage("§6§lHigh§b§lSky §bVous venez give un préfix à [" + targetPlayer.getDisplayName() + "]§r");
                        }

                    }
                }
            }

        } else if(args.length == 2){
            if (args[0].equalsIgnoreCase("giveall")) {

                int idTag = Integer.parseInt(args[1]);
                String Tag = null;
                try {
                    Tag = Database.RecupTag(idTag);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (Tag != null) {

                    for(Player p: Bukkit.getOnlinePlayers()){
                        Player targetPlayer = p;

                        try {
                            boolean verif = Database.VerifTag(p,idTag);

                            if (verif == false){
                                String tag = Tag.replace("&", "§");
                                ItemStack prefix = new ItemStack(Material.NAME_TAG);
                                ItemMeta prefixm = prefix.getItemMeta();
                                prefixm.setDisplayName(tag);
                                prefixm.setLore(Arrays.asList("","Faites Clique-Droit pour l'ajouter à votre liste", ""));
                                prefix.setItemMeta(prefixm);
                                targetPlayer.getInventory().addItem(prefix);
                                targetPlayer.updateInventory();
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                    if (sender instanceof Player){
                        sender.sendMessage("Vous avez give le tag : " + Tag.replace("&", "§"));
                    }

                }

            }
        } else if (args.length != 3 && args[0].equalsIgnoreCase("help")) {
            if(sender instanceof Player){
                sender.sendMessage("§6§lHigh§b§lSky §bVous avez a votre disposition la commande /tag give <Player> <idTag>");
            }

        }


        return false;
    }
}

