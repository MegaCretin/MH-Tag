package fr.megacretin.htag;

import fr.megacretin.htag.TagsMenu.TagsEvent;
import fr.megacretin.htag.TagsMenu.TagsMenu;
import fr.megacretin.htag.TagsUtils.Database;
import fr.megacretin.htag.TagsUtils.TagsExpension;
import fr.megacretin.htag.commands.TagCommand;
import fr.megacretin.htag.listeners.TagListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static HashMap<UUID, String> map = new HashMap();


    public Main() {
    }



    public void onEnable() {

            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                (new TagsExpension(this)).register();
            }

            try {
                this.reloadTag();
            } catch (SQLException var2) {
            }

            Bukkit.getPluginManager().registerEvents(new TagListener(), this);
            this.getCommand("tag").setExecutor(new TagCommand());
            this.getCommand("tags").setExecutor(new TagsMenu());
            Bukkit.getPluginManager().registerEvents(new TagsEvent(), this);

            TagRunnable inso = new TagRunnable(this);
            inso.runTaskTimerAsynchronously(this, 0, 20*60);




    }



    public void onDisable() {
        try {
            this.saveActiveTag();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        map.clear();
    }


    private void reloadTag() throws SQLException {
        Connection cn = Database.connect();
        String query = "SELECT idTags FROM Used WHERE UUID = ?";
        PreparedStatement ps = cn.prepareStatement(query);
        Iterator var4 = Bukkit.getOnlinePlayers().iterator();

        while(var4.hasNext()) {

            Player online = (Player)var4.next();
            ps.setString(1, online.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int tag = rs.getInt("idTags");
                if (tag != 0) {
                    map.put(online.getUniqueId(), Database.RecupTag(tag));
                }
            }
        }

    }

    private void saveActiveTag() throws SQLException {
        Connection cn = Database.connect();
        String query = "SELECT idTags FROM Used WHERE UUID = ?";
        PreparedStatement ps = cn.prepareStatement(query);
        Iterator var4 = Bukkit.getOnlinePlayers().iterator();

        while(var4.hasNext()) {
            Player p = (Player)var4.next();
            if (map.containsKey(p.getUniqueId())) {
                String tag = (String)map.get(p.getUniqueId());
                if (tag != null && tag != "") {
                    ps.setString(1, p.getUniqueId().toString());
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        if (!tag.equalsIgnoreCase(Database.RecupTag(rs.getInt("idTags")))) {
                            int tags = Database.RecupId(tag.replace("§","&"));
                            System.out.println("Update dans la BDD >> " + tag + " >> " + rs.getInt("idTags"));
                            query = "UPDATE Used SET idTags = ? WHERE UUID = ?";
                            ps = cn.prepareStatement(query);
                            ps.setInt(1, tags);
                            ps.setString(2, p.getUniqueId().toString());
                            ps.executeUpdate();
                        }
                    } else {
                        int tags = Database.RecupId(tag.replace("§","&"));
                        System.out.println("Inscription dans la BDD");
                        query = "INSERT INTO Used (UUID, idTags) VALUES (?, ?)";
                        ps = cn.prepareStatement(query);
                        ps.setString(1, p.getUniqueId().toString());
                        ps.setInt(2, tags);
                        ps.executeUpdate();
                    }

                    map.remove(p.getUniqueId());
                }
            }
        }

        ps.close();
        cn.close();
    }
}
