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

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.stroyer.perks.Player.PerksPlayer;

import java.util.ArrayList;
import java.util.List;

public class Parachute {
    private Player player;
    private Boolean fallProtectionActive;
    private static List<Parachute> parachutes = new ArrayList<>();

    public Parachute(Player player){
        this.player = player;
        this.fallProtectionActive = false;
    }

    public static void addParachute(Parachute parachute) {
        parachutes.add(parachute);
    }

    public Player getPlayer(){
        return this.player;
    }

    public Boolean fallProtectionActive(){
        return this.fallProtectionActive;
    }

    public static Parachute getParachute(Player player){
        for(Parachute p : parachutes){
            if(p.getPlayer().equals(player)){
                return p;
            }
        }
        return null;
    }

    public static void intitialise(){
        parachuteChecker.runTaskTimer(Bukkit.getPluginManager().getPlugin("Perks"), 0L, 2L);
        for(PerksPlayer p : PerksPlayer.perksPlayers){
            if(p.hasPerk(Perk.Parachute)){
                parachutes.add(new Parachute(p.getPlayer()));
            }
        }
    }

    private static BukkitRunnable parachuteChecker = new BukkitRunnable() {
        @Override
        public void run() {
            for(Parachute p : parachutes){
                if(p.getPlayer().isOnGround()){
                    continue;
                }
                Boolean shouldDeploy = false;
                for(int i = 1; i < 5; i++) {
                    if (p.getPlayer().getWorld().getBlockAt((int) Math.round(p.getPlayer().getLocation().getX()), (int) Math.round(p.getPlayer().getLocation().getY() - i), (int) Math.round(p.getPlayer().getLocation().getZ())).getType().equals(Material.AIR)){
                        shouldDeploy = true;
                    }else{
                        shouldDeploy = false;
                        break;
                    }
                }

                if(shouldDeploy && p.getPlayer().getVelocity().getY() < 0){
                    p.getPlayer().getWorld().spawnParticle(Particle.CRIT_MAGIC,new Location(p.getPlayer().getWorld(), p.getPlayer().getLocation().getX(), p.getPlayer().getLocation().getY() - 0.3f, p.getPlayer().getLocation().getZ()), 5);
                    p.getPlayer().setVelocity(new Vector(p.getPlayer().getVelocity().getX(), -0.5, p.getPlayer().getVelocity().getZ()));
                }

            }
        }
    };
}
