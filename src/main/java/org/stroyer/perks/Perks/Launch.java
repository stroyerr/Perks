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
import org.stroyer.perks.Player.PerksPlayer;

import java.util.ArrayList;
import java.util.List;

public class Launch {
    private Player player;
    private int ticksElapsed;
    private static List<Launch> launchObjects = new ArrayList<>();
    private static List<Launch> pendingLaunch = new ArrayList<>();

    public Launch(Player player){
        this.player = player;
        this.ticksElapsed = 0;
        launchObjects.add(this);
    }

    public Player getPlayer(){
        return this.player;
    }

    public static Launch getLaunch(Player player){
        for(Launch l : launchObjects){
            if(l.getPlayer().equals(player)){
                return l;
            }
        }
        return null;
    }

    public static BukkitRunnable tickTimer = new BukkitRunnable() {
        @Override
        public void run() {
            tickEvent();
        }
    };

    public static void initialise(){
        for(PerksPlayer p : PerksPlayer.perksPlayers){
            if(p.hasPerk(Perk.Launch)){Launch l = new Launch(p.getPlayer());}
        }
        tickTimer.runTaskTimer(Bukkit.getPluginManager().getPlugin("Perks"), 0L, 1L);
    }

    public static void tickEvent(){
        for(Launch l1 : launchObjects){
            if(l1.getPlayer().isSneaking() && !pendingLaunch.contains(l1)){pendingLaunch.add(l1);}
        }
        for(Launch l : pendingLaunch){
            if(l.getPlayer().isSneaking()){
                l.ticksElapsed ++;
                l.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.DARK_GREEN + StringUtils.repeat("-", l.ticksElapsed) + ChatColor.DARK_RED + StringUtils.repeat("-", 60-l.ticksElapsed)));
            }else{
                l.ticksElapsed = 0;
                l.removeFromPending();
            }
        }
    }

    public void removeFromPending(){
        pendingLaunch.remove(this);
    }
}