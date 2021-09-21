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

package org.stroyer.perks.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.stroyer.perks.Commands.PerkPunchCommand;
import org.stroyer.perks.Perks.PunchGun.PunchGun;
import org.stroyer.perks.Util.Send;

public class PerkPunchGunListener implements Listener {
    @EventHandler
    public static void playerClick(PlayerInteractEvent e){
        if(e.getItem() == null){
            return;
        }
        if(e.getItem().equals(PerkPunchCommand.punchGunItem)){
            if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)){
                if(PunchGun.hasPunchGun(e.getPlayer())){
                    PunchGun.getPunchGun(e.getPlayer()).attemptShoot();
                }
            }else{
                Send.player(e.getPlayer(), ChatColor.RED + "Oi! You haven't unlocked this yet!");
            }
        }
    }

    @EventHandler
    public static void gunCollision(ProjectileHitEvent  e){
        if(e.getEntity() instanceof Fireball){
            e.setCancelled(true);
            if(e.getHitEntity() != null){
                return;
            }
            if(!(e.getHitEntity() instanceof Player)){
                return;
            }
            e.getHitEntity()
        }
    }
}
