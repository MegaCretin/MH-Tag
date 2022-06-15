package fr.megacretin.htag;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class TagRunnable extends BukkitRunnable {

    private Boolean give = true;
    private static Main ici;

    public TagRunnable(Main main) {
        ici =  main;
    }

    @Override
    public void run() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("EEEE");
        String day = format.format(now);
        int hour = LocalTime.now().getHour();
        int min = LocalTime.now().getMinute();


        if (hour == 2 && min >= 00 && min <= 02) {
            if(give){
                performCommand();
                give = false;
            }
        }
        else{
            if(!give){
                give = true;
            }
        }



    }


    public static void performCommand() {

          BukkitTask t = new BukkitRunnable() {
              @Override
              public void run() {
                  Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tag giveall 203");
              }
          }.runTask(ici);
    }



}
