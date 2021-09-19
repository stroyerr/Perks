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

package org.stroyer.perks.Player;

import org.bukkit.entity.Player;
import org.stroyer.perks.Perks.Perk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PerksPlayer implements Serializable {

    public static List<PerksPlayer> perksPlayers;

    private Player player;
    private List<Perk> perks;
    private int tokens;

    public PerksPlayer(Player player, int tokens, List<Perk> perks){
        this.perks = perks;
        this.player = player;
        this.tokens = tokens;
        perksPlayers.add(this);
    }

    public PerksPlayer(Player player){
        this.player = player;
        this.tokens = 0;
        this.perks = new ArrayList<>();
        perksPlayers.add(this);
    }

    public List<Perk> getPerks() {
        return this.perks;
    }

    public int getTokens(){
        return this.tokens;
    }

    public static PerksPlayer getByPlayer(Player player){
        if(perksPlayers == null){perksPlayers = new ArrayList<>();}
        for(PerksPlayer pp : perksPlayers){
            if(pp.getPlayer().getUniqueId().equals(player.getUniqueId())){
                return pp;
            }
        }
        return null;
    }

    public Player getPlayer(){
        return this.player;
    }
}
