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

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.stroyer.perks.Commands.PerkPunchCommand;
import org.stroyer.perks.Perks.Parachute;
import org.stroyer.perks.Perks.Perk;
import org.stroyer.perks.Perks.PunchGun.PunchGun;
import org.stroyer.perks.Perks.SuperSpy;
import org.stroyer.perks.Perks.TPBow;
import org.stroyer.perks.Player.PerksPlayer;
import org.stroyer.perks.Util.Send;

public class PlayerJoin implements Listener {
    @EventHandler
    public static void playerJoin(PlayerLoginEvent e){
        final PerksPlayer[] pl = new PerksPlayer[1];
        BukkitRunnable br = new BukkitRunnable() {
            @Override
            public void run() {
                PerksPlayer pp;
                if(PerksPlayer.getByPlayer(e.getPlayer()) != null){
                    Send.console(e.getPlayer().getName() + " already has a Perk profile. No need to generate a new one.");
                    pl[0] = PerksPlayer.getByPlayer(e.getPlayer());
                    pp = pl[0];
                }else{
                    PerksPlayer newPlayer = new PerksPlayer(e.getPlayer());
                    pl[0] = newPlayer;
                    Send.console(e.getPlayer().getName() + " does not have a Perk profile. Generating one now...");
                    return;
                }
                if(e.getPlayer().getInventory().contains(PerkPunchCommand.punchGunItem)){
                    PunchGun newGun = new PunchGun(e.getPlayer());
                }
                for(Perk p : pp.getPerks()){
                    if(p.getName().equals(Perk.Parachute.getName())){
                        Parachute par = new Parachute(pp.getPlayer());
                        Parachute.addParachute(par);
                    }
                    if(p.getName().equals(Perk.PunchGun.getName())){
                        PunchGun.removePunchGun(PunchGun.getPunchGun(pp.getPlayer()));
                        PunchGun pg = new PunchGun(pp.getPlayer());
                    }
                    if(p.getName().equals(Perk.TPBow.getName())){
                        TPBow tpb = new TPBow(pp.getPlayer());
                    }
                    if(p.getName().equals(Perk.SuperSpy.getName())){
                        SuperSpy ss = new SuperSpy(pp.getPlayer());
                    }
                }
            }
        };
        br.runTaskLater(Bukkit.getPluginManager().getPlugin("Perks"), 10L);

        for(PerksPlayer pp : PerksPlayer.perksPlayers){
            if(pp.getActivePerks().contains(Perk.Solo)){
                pp.getPlayer().hidePlayer(e.getPlayer());
            }
        }

    }
}
