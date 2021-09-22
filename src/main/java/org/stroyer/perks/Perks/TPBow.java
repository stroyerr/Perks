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
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

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

    private void reload(){
        if(!reloadTimerActive){
            reloadTimer.runTaskTimer(Bukkit.getPluginManager().getPlugin("Perks"), 0L, 2L);
        }
        reloadingBows.add(this);
        this.isReloading = true;
    }

    private static boolean reloadTimerActive = false;

    private static BukkitRunnable reloadTimer = new BukkitRunnable() {
        @Override
        public void run() {
            reloadTimerActive = true;
            if(reloadingBows.size() == 0){
                reloadTimerActive = false;
                this.cancel();
            }
            reloadTimerEvent();
        }
    };

    private static void reloadTimerEvent(){
        for(TPBow bow : allBows){
            if(bow.isReloading){
                if(bow.reloadTimeElapsed == 20){
                    bow.isReloading = false;
                    bow.reloadTimeElapsed = 0;
                    reloadingBows.remove(bow);
                    if(reloadingBows.size() == 0){
                        reloadTimer.cancel();
                    }
                }
                bow.reloadTimeElapsed ++;
                bow.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent( ChatColor.DARK_GREEN + StringUtils.repeat("▍", (bow.reloadTimeElapsed)) + ChatColor.RED + StringUtils.repeat("▍", (10-bow.reloadTimeElapsed))));
            }
        }
    }

    public boolean isReloading() {
        return this.isReloading;
    }
}
