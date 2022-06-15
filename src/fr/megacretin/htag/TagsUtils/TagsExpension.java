package fr.megacretin.htag.TagsUtils;


import fr.megacretin.htag.Main;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class TagsExpension extends PlaceholderExpansion {
    private Main plugin;
    public static String string = "1";

    public TagsExpension(Main plugin) {
        this.plugin = plugin;
    }

    public boolean persist() {
        return true;
    }

    public boolean canRegister() {
        return true;
    }

    public String getAuthor() {
        return this.plugin.getDescription().getAuthors().toString();
    }

    public String getIdentifier() {
        return "highsky";
    }

    public String getVersion() {
        return this.plugin.getDescription().getVersion();
    }

    public String onPlaceholderRequest(Player player, String identifier) {
         if (identifier.equals("tag")) {
            if (Main.map.containsKey(player.getUniqueId())) {
                String tag = (String) Main.map.get(player.getUniqueId());
                return tag;
            } else {
                return "";
            }
         }

         if(identifier.equals("grade")){
             
             if(player.hasPermission("perm.fiverank")){
                return "§6✦";
             } else if(player.hasPermission("perm.fourrank")){
                 return "&e✦";
             } else if(player.hasPermission("perm.threerank")){
                 return "&a✦";
             } else if(player.hasPermission("perm.tworank")){
                 return "&b✦";
             }else if(player.hasPermission("perm.onerank")){
                 return "&f✦";
             }else{
                 return "";
             }
         } else {
            return null;
         }


    }

    public static String getString() {
        return string;
    }

}
