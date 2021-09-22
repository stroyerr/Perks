/*
 * Copyright © 2021 stroyerr
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is furnished
 * to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package org.stroyer.perks.Perks;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.stroyer.perks.Util.PlaySound;

import java.util.ArrayList;
import java.util.List;

public class TPBow {
    private Player player;
    private int reloadTimeElapsed;
    private boolean isReloading;
    private static List<TPBow> allBows = new ArrayList<>();
    private static List<TPBow> reloadingBows = new ArrayList<>();
    public TPBow(Player p){
        this.player = p;
        this.reloadTimeElapsed = 0;
        this.isReloading = false;
        allBows.add(this);
    }

    public void attemptReload(){
        if(this.isReloading){
            return;
        }
        this.reload();
    }

    public static TPBow getTPBow(Player player){
        for(TPBow bow : allBows){
            if(bow.player.equals(player)){
                return bow;
            }
        }
        return null;
    }
    public static void initialise(){
        reloadTimer.runTaskTimer(Bukkit.getPluginManager().getPlugin("Perks"), 0L, 2L);
    }

    private void reload(){
        if(!reloadTimerActive){

        }
        reloadingBows.add(this);
        this.isReloading = true;
    }

    private static boolean reloadTimerActive = false;

    private static BukkitRunnable reloadTimer = new BukkitRunnable() {
        @Override
        public void run() {
            reloadTimerActive = true;
            reloadTimerEvent();
        }
    };

    private static void reloadTimerEvent(){
        for(TPBow bow : allBows){
            if(bow.isReloading){
                if(bow.reloadTimeElapsed > 20){
                    bow.isReloading = false;
                    bow.reloadTimeElapsed = 0;
                    reloadingBows.remove(bow);
                    bow.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Reload complete"));
                    PlaySound.player(bow.player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP);
                    return;
                }
                bow.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent( ChatColor.DARK_GREEN + StringUtils.repeat("▍", (bow.reloadTimeElapsed)) + ChatColor.RED + StringUtils.repeat("▍", (20-bow.reloadTimeElapsed))));
                PlaySound.player(bow.player, Sound.BLOCK_METAL_PRESSURE_PLATE_CLICK_OFF);
                bow.reloadTimeElapsed ++;}
        }
    }

    public boolean isReloading() {
        return this.isReloading;
    }
}
