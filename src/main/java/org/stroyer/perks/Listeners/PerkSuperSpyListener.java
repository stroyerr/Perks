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
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.stroyer.perks.Commands.PerkSuperSpyCommand;
import org.stroyer.perks.Perks.SuperSpy;
import org.stroyer.perks.Util.Send;

public class PerkSuperSpyListener implements Listener {
    @EventHandler
    public static void onLeave(PlayerQuitEvent e){
        if(SuperSpy.getSuperSpy(e.getPlayer()) != null){
            if(SuperSpy.getSuperSpy(e.getPlayer()).isInZoom()){
                SuperSpy.getSuperSpy(e.getPlayer()).exitZoom();
            }
        }
    }

    @EventHandler
    public static void playerInteract(PlayerInteractEvent e){
        if(SuperSpy.getSuperSpy(e.getPlayer()) == null){
            return;
        }
        if(e.getPlayer().getInventory().getItemInMainHand() == null){
            return;
        }
        if(!(e.getPlayer().getInventory().getItemInMainHand().equals(PerkSuperSpyCommand.superSpyItem))){
            return;
        }
        if(e.getAction().isRightClick()){
            if(SuperSpy.getSuperSpy(e.getPlayer()).isInZoom()){
                Send.player(e.getPlayer(), ChatColor.RED + "Left click to exit zoom.");
                return;
            }
            SuperSpy.getSuperSpy(e.getPlayer()).enterZoom();
        }
        if(e.getAction().isLeftClick()){
            if(!SuperSpy.getSuperSpy(e.getPlayer()).isInZoom()){
                Send.player(e.getPlayer(), ChatColor.RED + "Right click to zoom.");
                return;
            }
            SuperSpy.getSuperSpy(e.getPlayer()).exitZoom();
        }
    }

    @EventHandler
    public static void onMove(PlayerMoveEvent e){
        if(SuperSpy.getSuperSpy(e.getPlayer()) == null){
            return;
        }
        if(SuperSpy.getSuperSpy(e.getPlayer()).isInZoom()){
            if(e.getTo().getY() < e.getFrom().getY()){
                SuperSpy.getSuperSpy(e.getPlayer()).exitZoom();
            }
            e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), SuperSpy.getSuperSpy(e.getPlayer()).getNewLocation().getX(), SuperSpy.getSuperSpy(e.getPlayer()).getNewLocation().getY(), SuperSpy.getSuperSpy(e.getPlayer()).getNewLocation().getZ()));
        }
    }
}
