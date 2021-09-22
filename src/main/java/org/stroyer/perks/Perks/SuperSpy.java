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
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.stroyer.perks.Util.Send;

import java.util.ArrayList;
import java.util.List;

public class SuperSpy {
    private Player player;
    private Boolean isInZoom;
    private Location beforeZoomLocation;
    private Location newLocation;
    private static List<SuperSpy> superSpies = new ArrayList<>();
    public static List<SuperSpy> playersInZoom = new ArrayList<>();

    public SuperSpy(Player player){
        this.player = player;
        this.isInZoom = false;
        superSpies.add(this);
    }

    public Player getPlayer(){
        return this.player;
    }

    public void enterZoom(){
        if(this.player.getTargetBlock(120) != null){
            this.beforeZoomLocation = this.player.getLocation();
            this.isInZoom = true;
            this.newLocation = this.player.getTargetBlock(120).getLocation();
            playersInZoom.add(this);
            this.player.setGameMode(GameMode.SPECTATOR);
            this.player.teleport(this.player.getTargetBlock(120).getLocation());
            this.player.sendTitle(ChatColor.GREEN + "Now spying...", ChatColor.DARK_GREEN + "Crouch to exit", 20, 100, 20);
        }else{
            Send.player(this.player, ChatColor.RED + "No block in sight!");
        }
    }

    public void exitZoom(){
        if(this.beforeZoomLocation == null){
            Send.player(this.player, ChatColor.RED + "Right click to zoom, left click to exit.");
            return;
        }
        this.player.setGameMode(GameMode.SURVIVAL);
        this.player.teleport(this.beforeZoomLocation);

        this.isInZoom = false;
        this.player.teleport(this.beforeZoomLocation);
        this.player.teleport(this.beforeZoomLocation);
        this.player.teleport(new Location(this.beforeZoomLocation.getWorld(), this.beforeZoomLocation.getX(), this.beforeZoomLocation.getY(), this.beforeZoomLocation.getZ()));
    }

    public static SuperSpy getSuperSpy(Player player){
        for(SuperSpy s : superSpies){
            if(s.getPlayer().equals(player)){
                return s;
            }
        }
        return null;
    }

    public Boolean isInZoom(){
        return this.isInZoom;
    }

    public Location getNewLocation() {
        return this.newLocation;
    }
}
