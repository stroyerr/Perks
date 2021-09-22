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

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.stroyer.perks.Util.Send;

import java.util.ArrayList;
import java.util.List;

public class NightVision {
    protected final Player player;
    private Boolean enabled;
    private static List<NightVision> nightVisionObjects = new ArrayList<>();
    public NightVision(Player player){
        this.player = player;
        this.enabled = false;
        nightVisionObjects.add(this);
    }

    public Player getPlayer(){
        return this.player;
    }

    public static NightVision getNightVision(Player player){
        for(NightVision nv : nightVisionObjects){
            if(nv.getPlayer().equals(player)){
                return nv;
            }
        }
        return null;
    }

    public Boolean isEnabled(){
        return this.enabled;
    }

    public void enable(){
        this.enabled = true;
        this.player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1));
    }

    public void disable(){
        this.enabled = false;
        this.player.removePotionEffect(PotionEffectType.NIGHT_VISION);
    }

    public void toggleEnabled(){
        if(this.isEnabled()){
            this.disable();
            Send.player(this.getPlayer(), "Night vision disabled.");
        }else{
            this.enable();
            Send.player(this.getPlayer(), "Night vision enabled.");
        }
    }

    public static Boolean hasNightVisionObject(Player player){
        for(NightVision nv : nightVisionObjects){
            if(nv.getPlayer().equals(player)){
                return true;
            }
        }
        return false;
    }
}
