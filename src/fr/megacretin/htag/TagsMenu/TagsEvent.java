package fr.megacretin.htag.TagsMenu;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mysql.jdbc.Util;
import fr.megacretin.htag.Main;
import fr.megacretin.htag.TagsUtils.Database;
import fr.megacretin.htag.TagsUtils.UtilsTags;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

import static org.bukkit.Material.SKULL_ITEM;

public class TagsEvent implements Listener {
    public TagsEvent() {
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) throws SQLException {
        Player p = (Player)e.getWhoClicked();
        if (e.getClickedInventory() == null) {
            e.setCancelled(true);
        }

        if (e.getView().getTitle().contains("§6§l>> §bTags")) {
            e.setCancelled(true);
            if (e.getInventory() == null || e.getCurrentItem() == null || e.getCurrentItem().getType() == null || !e.getCurrentItem().hasItemMeta()) {
                p.closeInventory();
                return;
            }

            if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equals("§a§lEn Jeux")){
                p.closeInventory();

                Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, "§6§l>> §bTags§f/§aEn Jeux §6§l<<");

                ItemStack retour = new ItemStack(Material.ARROW);
                ItemMeta retourx = retour.getItemMeta();
                retourx.setDisplayName("§cRetour");
                retour.setItemMeta(retourx);
                inv.setItem(0, retour);

                ItemStack Insomniaque = new ItemStack(Material.NAME_TAG);
                ItemMeta Insomniaquex = Insomniaque.getItemMeta();
                Insomniaquex.setDisplayName(("&1#&9Insomniaque").replace("&", "§"));
                Insomniaquex.setLore(Arrays.asList("", "§aLes tags sont des éléments", "§ade décorations ils sont visibles", "§auniquement quand tu parle.", "", "§3Tu peux les activer et", "§3désactiver quand tu veux.", "", "Obtenable après 2h du matin"));
                Insomniaque.setItemMeta(Insomniaquex);
                inv.setItem(10, Insomniaque);

                ItemStack Millionnaire = new ItemStack(Material.NAME_TAG);
                ItemMeta Millionnairex = Millionnaire.getItemMeta();
                Millionnairex.setDisplayName(("&6#&eMillionnaire").replace("&", "§"));
                Millionnairex.setLore(Arrays.asList("", "§aLes tags sont des éléments", "§ade décorations ils sont visibles", "§auniquement quand tu parle.", "", "§3Tu peux les activer et", "§3désactiver quand tu veux.", "", "Obtenable après avoir 1M$"));
                Millionnaire.setItemMeta(Millionnairex);
                inv.setItem(11, Millionnaire);

                ItemStack Vote = new ItemStack(Material.NAME_TAG);
                ItemMeta Votex = Vote.getItemMeta();
                Votex.setDisplayName(("&a#Voteur").replace("&", "§"));
                Votex.setLore(Arrays.asList("", "§aLes tags sont des éléments", "§ade décorations ils sont visibles", "§auniquement quand tu parle.", "", "§3Tu peux les activer et", "§3désactiver quand tu veux.", "", "Obtenable après 250 Votes sur le serveur"));
                Vote.setItemMeta(Votex);
                inv.setItem(12, Vote);

                ItemStack it1 = new ItemStack(SKULL_ITEM, 1, (short) 3);
                SkullMeta itemMeta1 = (SkullMeta) it1.getItemMeta();
                itemMeta1.setDisplayName("§f§lQuêtes");
                GameProfile profile1 = new GameProfile(UUID.randomUUID(), null);
                byte[] encodedData1 = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", "http://textures.minecraft.net/texture/56330a4a22ff55871fc8c618e421a37733ac1dcab9c8e1a4bb73ae645a4a4e").getBytes());
                profile1.getProperties().put("textures", new Property("textures", new String(encodedData1)));
                Field profileField1 = null;
                try
                {
                    profileField1 = itemMeta1.getClass().getDeclaredField("profile");
                    profileField1.setAccessible(true);
                    profileField1.set(itemMeta1, profile1);
                }
                catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException er)
                {
                    er.printStackTrace();
                }
                it1.setItemMeta(itemMeta1);
                inv.setItem(14, it1);

                ItemStack it2 = new ItemStack(SKULL_ITEM, 1, (short) 3);
                SkullMeta itemMeta2 = (SkullMeta) it2.getItemMeta();
                itemMeta2.setDisplayName("§f§lDH");
                GameProfile profile2 = new GameProfile(UUID.randomUUID(), null);
                byte[] encodedData2 = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", "http://textures.minecraft.net/texture/56330a4a22ff55871fc8c618e421a37733ac1dcab9c8e1a4bb73ae645a4a4e").getBytes());
                profile2.getProperties().put("textures", new Property("textures", new String(encodedData2)));
                Field profileField2 = null;
                try
                {
                    profileField2 = itemMeta2.getClass().getDeclaredField("profile");
                    profileField2.setAccessible(true);
                    profileField2.set(itemMeta2, profile2);
                }
                catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException er)
                {
                    er.printStackTrace();
                }
                it2.setItemMeta(itemMeta2);
                inv.setItem(16, it2);

                ItemStack bar = new ItemStack(Material.BARRIER);
                ItemMeta barx = bar.getItemMeta();
                barx.setDisplayName("§cReset du TAG");
                bar.setItemMeta(barx);
                inv.setItem(26, bar);

                p.openInventory(inv);
            }

            if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equals("§f§lCommun")){
                p.closeInventory();

                Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, "§6§l>> §bTags§f/§8Commun §6§l<<");

                ItemStack retour = new ItemStack(Material.ARROW);
                ItemMeta retourx = retour.getItemMeta();
                retourx.setDisplayName("§cRetour");
                retour.setItemMeta(retourx);
                inv.setItem(0, retour);

                UtilsTags.TagsMenu("&9#Pro", inv, 1);
                UtilsTags.TagsMenu("&9✮Star✮", inv, 2);
                UtilsTags.TagsMenu("#&l&mIdiot", inv, 6);
                UtilsTags.TagsMenu("&3#&lMusic♪", inv, 7);
                UtilsTags.TagsMenu("&8#&lYin&r☯", inv, 10);
                UtilsTags.TagsMenu("&f#&lYang&r☯", inv, 11);
                UtilsTags.TagsMenu("&7☾ &l&8Night &7☽", inv, 12);
                UtilsTags.TagsMenu("&e&l☽&rSleep &7zz&8zZ&0Z", inv, 13);
                UtilsTags.TagsMenu("#&6Empereur", inv, 14);
                UtilsTags.TagsMenu("&7#Inflitré", inv, 15);
                UtilsTags.TagsMenu("&a#Big&2Boss", inv, 16);
                UtilsTags.TagsMenu("&f#&c&lC&6&lo&e&ll&a&lo&b&lr", inv, 19);
                UtilsTags.TagsMenu("&d&lL&r&d❤&lve", inv, 20);
                UtilsTags.TagsMenu("&cFr&fan&9ce", inv, 21);
                UtilsTags.TagsMenu("&8Bel&egi&cque", inv, 22);
                UtilsTags.TagsMenu("&cSui&fsse", inv, 23);
                UtilsTags.TagsMenu("&d#2020", inv, 24);
                UtilsTags.TagsMenu("&7#Jacky&cR&aG&9B", inv, 25);

                ItemStack bar = new ItemStack(Material.BARRIER);
                ItemMeta barx = bar.getItemMeta();
                barx.setDisplayName("§cReset du TAG");
                bar.setItemMeta(barx);
                inv.setItem(4, bar);

                p.openInventory(inv);
            }

            if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equals("§b§lRare")){
                p.closeInventory();

                Inventory inv = Bukkit.createInventory((InventoryHolder) null, 27, "§6§l>> §bTags§f/§bRare §6§l<<");

                ItemStack retour = new ItemStack(Material.ARROW);
                ItemMeta retourx = retour.getItemMeta();
                retourx.setDisplayName("§cRetour");
                retour.setItemMeta(retourx);
                inv.setItem(0, retour);

                UtilsTags.TagsMenu("&6&lHigh&b&lSky", inv, 2);
                UtilsTags.TagsMenu("&k#&r&6H&rors&bS&rervice", inv, 3);
                UtilsTags.TagsMenu("&c#C&fa&cn&fa&cd&fa♣", inv, 4);
                UtilsTags.TagsMenu("&9✮Gal&3acti&bque✮", inv, 5);
                UtilsTags.TagsMenu("&8&l✞Dead✞", inv, 6);
                UtilsTags.TagsMenu("&6&l$&b&lPay&e&l2&a&lWin&6&l$", inv, 11);
                UtilsTags.TagsMenu("&4&lK&c&li&4&ll&c&ll&4&le&c&lr", inv, 12);
                UtilsTags.TagsMenu("&8Wit&fher", inv, 14);
                UtilsTags.TagsMenu("&dEnder&fDragon", inv, 15);
                UtilsTags.TagsMenu("#&eEdition&6Limitée", inv, 20);
                UtilsTags.TagsMenu("&7Iron&fGolem", inv, 21);
                UtilsTags.TagsMenu("&2♣Lucky♣", inv, 22);
                UtilsTags.TagsMenu("&8#Black&cMarket", inv, 23);
                UtilsTags.TagsMenu("&c&n❤&b&nHigh&e&nSky", inv, 24);

                ItemStack bar = new ItemStack(Material.BARRIER);
                ItemMeta barx = bar.getItemMeta();
                barx.setDisplayName("§cReset du TAG");
                bar.setItemMeta(barx);
                inv.setItem(26, bar);

                p.openInventory(inv);
            }

            if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lLegendaire")){
                p.closeInventory();

                Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, "§6§l>> §bTags§f/§cLegendaire §6§l<<");

                ItemStack retour = new ItemStack(Material.ARROW);
                ItemMeta retourx = retour.getItemMeta();
                retourx.setDisplayName("§cRetour");
                retour.setItemMeta(retourx);
                inv.setItem(0, retour);

                UtilsTags.TagsMenu("&4&k&l$&b&lPay&e&l&n2&a&lWin&4&l&k$&r", inv, 11);
                UtilsTags.TagsMenu("&e&k&n.&e&nHigh&b&nSky&k&n.", inv, 12);
                UtilsTags.TagsMenu("#&kyyyyy", inv, 13);
                UtilsTags.TagsMenu("&c#NO&eL&bI&3F&dE", inv, 14);
                UtilsTags.TagsMenu("&2T&ao&2x&ai&2c &a☣", inv, 15);

                ItemStack bar = new ItemStack(Material.BARRIER);
                ItemMeta barx = bar.getItemMeta();
                barx.setDisplayName("§cReset du TAG");
                bar.setItemMeta(barx);
                inv.setItem(26, bar);

                p.openInventory(inv);
            }

            if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equals("§d§lExclusif")){
                p.closeInventory();

                Inventory inv = Bukkit.createInventory((InventoryHolder)null, 9, "§6§l>> §bTags§f/§dExclusif §6§l<<");

                ItemStack retour = new ItemStack(Material.ARROW);
                ItemMeta retourx = retour.getItemMeta();
                retourx.setDisplayName("§cRetour");
                retour.setItemMeta(retourx);
                inv.setItem(0, retour);

                ItemStack HighSky = new ItemStack(Material.NAME_TAG);
                ItemMeta HighSkyx = HighSky.getItemMeta();
                HighSkyx.setDisplayName(("&6&lHigh&e&lSky").replace("&", "§"));
                HighSkyx.setLore(Arrays.asList("", "§aLes tags sont des éléments", "§ade décorations ils sont visibles", "§auniquement quand tu parle.", "", "§3Tu peux les activer et", "§3désactiver quand tu veux.", "", "Obtenable que lors de la première semaine d'ouverture"));
                HighSky.setItemMeta(HighSkyx);
                inv.setItem(3, HighSky);

                ItemStack Donnateur = new ItemStack(Material.NAME_TAG);
                ItemMeta Donnateurx = Donnateur.getItemMeta();
                Donnateurx.setDisplayName(("&f#&cD&6o&en&2a&at&3e&9u&dr &e$$$").replace("&", "§"));
                Donnateurx.setLore(Arrays.asList("", "§aLes tags sont des éléments", "§ade décorations ils sont visibles", "§auniquement quand tu parle.", "", "§3Tu peux les activer et", "§3désactiver quand tu veux.", "", "Obtenable après avec payé 50 euros sur le serveur"));
                Donnateur.setItemMeta(Donnateurx);
                inv.setItem(4, Donnateur);

                ItemStack Pass = new ItemStack(Material.NAME_TAG);
                ItemMeta Passx = Pass.getItemMeta();
                Passx.setDisplayName(("&6&lPass&a&l✓").replace("&", "§"));
                Passx.setLore(Arrays.asList("", "§aLes tags sont des éléments", "§ade décorations ils sont visibles", "§auniquement quand tu parle.", "", "§3Tu peux les activer et", "§3désactiver quand tu veux.", "", "Obtenable dans le pass"));
                Pass.setItemMeta(Passx);
                inv.setItem(5, Pass);

                ItemStack bar = new ItemStack(Material.BARRIER);
                ItemMeta barx = bar.getItemMeta();
                barx.setDisplayName("§cReset du TAG");
                bar.setItemMeta(barx);
                inv.setItem(8, bar);

                p.openInventory(inv);

            }

            if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equals("§f§lQuêtes")){
                p.closeInventory();

                Inventory inv = Bukkit.createInventory((InventoryHolder)null, 18, "§6§l>> §bTags§f/§aEn Jeux§f/§8Quêtes §6§l<<");

                ItemStack retour = new ItemStack(Material.ARROW);
                ItemMeta retourx = retour.getItemMeta();
                retourx.setDisplayName("§cRetour");
                retour.setItemMeta(retourx);
                inv.setItem(0, retour);

                ItemStack Neophyte = new ItemStack(Material.NAME_TAG);
                ItemMeta Neophytex = Neophyte.getItemMeta();
                Neophytex.setDisplayName(("&5&l#Facile").replace("&", "§"));
                Neophytex.setLore(Arrays.asList("", "§aLes tags sont des éléments", "§ade décorations ils sont visibles", "§auniquement quand tu parle.", "", "§3Tu peux les activer et", "§3désactiver quand tu veux.", "", "Obtenable dès les quêtes Facile terminées "));
                Neophyte.setItemMeta(Neophytex);
                inv.setItem(2, Neophyte);

                ItemStack Initie = new ItemStack(Material.NAME_TAG);
                ItemMeta Initiex = Initie.getItemMeta();
                Initiex.setDisplayName(("&d&l#Normal").replace("&", "§"));
                Initiex.setLore(Arrays.asList("", "§aLes tags sont des éléments", "§ade décorations ils sont visibles", "§auniquement quand tu parle.", "", "§3Tu peux les activer et", "§3désactiver quand tu veux.", "", "Obtenable dès les quêtes Normal terminées "));
                Initie.setItemMeta(Initiex);
                inv.setItem(3, Initie);

                ItemStack Intermediaire = new ItemStack(Material.NAME_TAG);
                ItemMeta Intermediairex = Intermediaire.getItemMeta();
                Intermediairex.setDisplayName(("&c&l#Intermédiaire").replace("&", "§"));
                Intermediairex.setLore(Arrays.asList("", "§aLes tags sont des éléments", "§ade décorations ils sont visibles", "§auniquement quand tu parle.", "", "§3Tu peux les activer et", "§3désactiver quand tu veux.", "", "Obtenable dès les quêtes Intermédiaires terminées "));
                Intermediaire.setItemMeta(Intermediairex);
                inv.setItem(4, Intermediaire);

                ItemStack Avance = new ItemStack(Material.NAME_TAG);
                ItemMeta Avancex = Avance.getItemMeta();
                Avancex.setDisplayName(("&6&l#Courageux").replace("&", "§"));
                Avancex.setLore(Arrays.asList("", "§aLes tags sont des éléments", "§ade décorations ils sont visibles", "§auniquement quand tu parle.", "", "§3Tu peux les activer et", "§3désactiver quand tu veux.", "", "Obtenable dès les quêtes Courageux terminées "));
                Avance.setItemMeta(Avancex);
                inv.setItem(5, Avance);

                ItemStack Confirme = new ItemStack(Material.NAME_TAG);
                ItemMeta Confirmex = Confirme.getItemMeta();
                Confirmex.setDisplayName(("&e&l#Epique").replace("&", "§"));
                Confirmex.setLore(Arrays.asList("", "§aLes tags sont des éléments", "§ade décorations ils sont visibles", "§auniquement quand tu parle.", "", "§3Tu peux les activer et", "§3désactiver quand tu veux.", "", "Obtenable dès les quêtes Epique terminées "));
                Confirme.setItemMeta(Confirmex);
                inv.setItem(11, Confirme);

                ItemStack Maitre = new ItemStack(Material.NAME_TAG);
                ItemMeta Maitrex = Maitre.getItemMeta();
                Maitrex.setDisplayName(("&a&l#Héroïque").replace("&", "§"));
                Maitrex.setLore(Arrays.asList("", "§aLes tags sont des éléments", "§ade décorations ils sont visibles", "§auniquement quand tu parle.", "", "§3Tu peux les activer et", "§3désactiver quand tu veux.", "", "Obtenable dès les quêtes Héroïque terminées "));
                Maitre.setItemMeta(Maitrex);
                inv.setItem(12, Maitre);

                ItemStack Expert = new ItemStack(Material.NAME_TAG);
                ItemMeta Expertx = Expert.getItemMeta();
                Expertx.setDisplayName(("&3&l#Déterminé").replace("&", "§"));
                Expertx.setLore(Arrays.asList("", "§aLes tags sont des éléments", "§ade décorations ils sont visibles", "§auniquement quand tu parle.", "", "§3Tu peux les activer et", "§3désactiver quand tu veux.", "", "Obtenable dès les quêtes Déterminé terminées "));
                Expert.setItemMeta(Expertx);
                inv.setItem(13, Expert);

                ItemStack Master = new ItemStack(Material.NAME_TAG);
                ItemMeta Masterx = Master.getItemMeta();
                Masterx.setDisplayName(("&9&l#Légendaire").replace("&", "§"));
                Masterx.setLore(Arrays.asList("", "§aLes tags sont des éléments", "§ade décorations ils sont visibles", "§auniquement quand tu parle.", "", "§3Tu peux les activer et", "§3désactiver quand tu veux.", "", "Obtenable dès les quêtes Légendaire terminées "));
                Master.setItemMeta(Masterx);
                inv.setItem(14, Master);

                ItemStack bar = new ItemStack(Material.BARRIER);
                ItemMeta barx = bar.getItemMeta();
                barx.setDisplayName("§cReset du TAG");
                bar.setItemMeta(barx);
                inv.setItem(17, bar);

                p.openInventory(inv);
            }

            if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equals("§f§lDH")){
                p.closeInventory();

                Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, "§6§l>> §bTags§f/§aEn Jeux§f/§8DH §6§l<<");

                ItemStack retour = new ItemStack(Material.ARROW);
                ItemMeta retourx = retour.getItemMeta();
                retourx.setDisplayName("§cRetour");
                retour.setItemMeta(retourx);
                inv.setItem(0, retour);

                UtilsTags.TagsMenu("#&eRoi&2Cactus", inv, 2);
                UtilsTags.TagsMenu("#&eRoi&6Carotte", inv, 3);
                UtilsTags.TagsMenu("#&eRoi&6Pattate", inv, 4);
                UtilsTags.TagsMenu("#&eRoi&6Ci&etr&6ou&eil&6le", inv, 5);
                UtilsTags.TagsMenu("#&eRoi&aSucre", inv, 6);
                UtilsTags.TagsMenu("#&eRoi&7Caillou", inv, 11);
                UtilsTags.TagsMenu("#&eRoi&7Pierre", inv, 12);
                UtilsTags.TagsMenu("#&eRoi&cVerrue", inv, 14);
                UtilsTags.TagsMenu("#&eRoi&dChorus", inv, 15);
                UtilsTags.TagsMenu("#&eRoi&8Bois", inv, 20);
                UtilsTags.TagsMenu("#&eRoi&aFeuillage", inv, 21);
                UtilsTags.TagsMenu("#&eRoi&8Cochon", inv, 22);
                UtilsTags.TagsMenu("#&eRoi&5Betterave", inv, 23);
                UtilsTags.TagsMenu("#&eRoi&8Encre", inv, 24);

                ItemStack bar = new ItemStack(Material.BARRIER);
                ItemMeta barx = bar.getItemMeta();
                barx.setDisplayName("§cReset du TAG");
                bar.setItemMeta(barx);
                inv.setItem(26, bar);

                p.openInventory(inv);
            }

            switch(e.getCurrentItem().getType()) {
                case NAME_TAG:

                    String tag = (e.getCurrentItem().getItemMeta().getDisplayName()).replace("§", "&");

                    int idTag = Database.RecupId(tag);


                    if (idTag != 0){

                        boolean possede = Database.VerifTag(p, idTag);

                        if (possede == true){

                            p.sendMessage("§6§lHigh§b§lSky §7§l>> §aVotre Tag a bien été mis");

                            if (!Main.map.containsKey(p.getUniqueId())) {
                                Main.map.put(p.getUniqueId(), tag.replace("&", "§"));
                            } else {
                                Main.map.replace(p.getUniqueId(), tag.replace("&", "§"));

                            }

                            p.closeInventory();
                        }
                        else{
                            p.sendMessage("§6§lHigh§b§lSky §7§l>> §cVous ne possédez pas ce Tag");
                            return;
                        }
                    }



                    break;
                case BARRIER:
                    Main.map.replace(p.getUniqueId(), "");
                    p.sendMessage("§6§lHigh§b§lSky §7§l>> §cTu as retiré ton TAG.");
                    p.closeInventory();

                case ARROW:
                    p.closeInventory();
                    p.performCommand("tags");
            }
        }

    }
}
