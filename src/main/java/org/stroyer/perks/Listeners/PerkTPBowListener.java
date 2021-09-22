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

import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.stroyer.perks.Commands.PerkTPBowCommand;
import org.stroyer.perks.Perks.Perk;
import org.stroyer.perks.Player.PerksPlayer;
import org.stroyer.perks.Util.PlaySound;
import org.stroyer.perks.Util.Send;

import java.util.ArrayList;
import java.util.List;

public class PerkTPBowListener implements Listener {
    private static List<Player> tpArrows = new ArrayList<>();
    @EventHandler
    public static void bowUse(PlayerInteractEvent e){
        if(!(PerksPlayer.getByPlayer(e.getPlayer()).hasPerk(Perk.TPBow))){
            return;
        }
        if(!(e.getItem().equals(PerkTPBowCommand.tpBowItem))){
            return;
        }
        if(!(e.getAction().isRightClick())){
            return;
        }
        e.getPlayer().launchProjectile(Arrow.class, e.getPlayer().getLocation().getDirection());
        e.setCancelled(true);
        tpArrows.add(e.getPlayer());
    }
    @EventHandler
    public static void arrowHit(ProjectileHitEvent e){
        if(!(e.getEntity().getShooter() instanceof Player)){
            return;
        }
        Player p = (Player) e.getEntity().getShooter();
        if(tpArrows.contains(p)){
            p.teleport(e.getEntity().getLocation());
            PlaySound.player(p, Sound.ENTITY_ENDERMAN_TELEPORT);
            e.getEntity().remove();
            tpArrows.remove(p);
        }
    }
}
