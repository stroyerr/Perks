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

package org.stroyer.perks.Internal;

import org.bukkit.entity.Player;
import org.stroyer.perks.Perks.Perk;
import org.stroyer.perks.Player.PerksPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Settings {
    private static List<Settings> loadedSettings = new ArrayList<>();
    private PerksPlayer player;
    private List<Setting> playerSettings = new ArrayList<>();
    public Settings(PerksPlayer player){
        this.player = player;
        for(Perk p : player.getPerks()){
            this.playerSettings.add(new Setting(p, true));
        }
        loadedSettings.add(this);
    }

    public static Settings getSettings(Player player){
        for(Settings s : loadedSettings){
            if(s.getPerksPlayer() == null){continue;}
            if(s.getPerksPlayer().getPlayer() == null){continue;}
            if(s.getPerksPlayer().getPlayer().getUniqueId().equals(player.getUniqueId())){
                return s;
            }
        }
        return null;
    }

    public Boolean isEnabled(Perk perk){
        for(Setting s : this.playerSettings){
            if(s.getPerk().getName().equals(perk.getName())){
                if(s.isEnabled()){
                    return true;
                }
            }
        }
        return false;
    }

    public PerksPlayer getPerksPlayer(){
        return this.player;
    }

    public static Boolean hasSettingsProfile(Player player){
        for(Settings s : loadedSettings){
            if(s.getPerksPlayer().getPlayer().getUniqueId().equals(player.getUniqueId())){
                return true;
            }
        }
        return false;
    }

    public void toggle(Perk perk){
        for(Setting s : this.playerSettings){
            if(s.getPerk().getName().equals(perk.getName())){
                s.toggle();
                return;
            }
        }
    }
}
