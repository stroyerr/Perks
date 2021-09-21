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

package org.stroyer.perks.Perks.PunchGun;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.stroyer.perks.Commands.PerkPunchCommand;
import org.stroyer.perks.Main;

import java.util.ArrayList;
import java.util.List;

public class PunchGun {
    private Player player;
    private int rounds;
    private Boolean canShoot;
    private static List<PunchGun> allGuns = new ArrayList<>();
    public PunchGun(Player player){
        this.player = player;
        this.rounds = 3;
        this.canShoot = true;
        allGuns.add(this);
    }

    public Boolean canShoot(){
        return this.canShoot;
    }
    public int getRounds(){
        return this.rounds;
    }
    public Player getPlayer(){
        return this.player;
    }
    public void attemptShoot(){
        if(this.canShoot){
            this.shoot();
        }
    }
    public static Boolean hasPunchGun(Player player) {
        for(PunchGun pg : allGuns){
            if(pg.getPlayer().equals(player)){
                return true;
            }
        }
        return false;
    }

    public static PunchGun getPunchGun(Player player){
        for(PunchGun pg : allGuns){
            if(pg.getPlayer().equals(player)){
                return pg;
            }
        }
        return null;
    }

    public void shoot(){
        Fireball f = this.player.launchProjectile(Fireball.class);
        f.setIsIncendiary(false);
        f.setGlowing(true);
        f.setInvulnerable(true);
        f.setYield(0f);
        this.rounds --;
    }

    private static BukkitRunnable ammoHud = new BukkitRunnable() {
        @Override
        public void run() {
            ammoHudUpdate();
        }
    };

    public static void initialise(){
        ammoHud.runTaskTimer(Bukkit.getPluginManager().getPlugin("Perks"), 0L, 4L);
    }

    private static void ammoHudUpdate(){
        for(PunchGun gun : allGuns){
            if(gun.player.getItemInUse().equals(PerkPunchCommand.punchGunItem)){
                if(gun.rounds > 0){
                    if(gun.rounds == 3){
                        gun.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "███"));
                    }
                    if(gun.rounds == 2){
                        gun.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "██" + ChatColor.RED + "█"));
                    }
                    if(gun.rounds == 1){
                        gun.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "█" + ChatColor.RED + "██"));
                    }
                }else{
                    gun.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + "" + ChatColor.BOLD + "You're out of ammo! Left Click to reload..."));
                }
            }
        }
    }
}
