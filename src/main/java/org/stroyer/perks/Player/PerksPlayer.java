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

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.stroyer.perks.Perks.Parachute;
import org.stroyer.perks.Perks.Perk;
import org.stroyer.perks.Tokens.Token;
import org.stroyer.perks.Util.Send;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PerksPlayer implements Serializable {

    public static List<PerksPlayer> perksPlayers = new ArrayList<>();

    private UUID playerUUID;
    private List<Perk> perks;
    private List<Perk> activePerks = new ArrayList<>();
    private int tokens;

    public PerksPlayer(Player player, int tokens, List<Perk> perks, List<Perk> activePerks){
        this.perks = perks;
        this.playerUUID = player.getUniqueId();
        this.tokens = tokens;
        this.activePerks = activePerks;
        perksPlayers.add(this);
    }

    public PerksPlayer(UUID playerUUID, int tokens, List<Perk> perks, List<Perk> activePerks){
        this.perks = perks;
        this.playerUUID = playerUUID;
        this.tokens = tokens;
        this.activePerks = activePerks;
        perksPlayers.add(this);
    }

    public PerksPlayer(Player player){
        this.playerUUID = player.getUniqueId();
        this.tokens = 0;
        this.perks = new ArrayList<>();
        this.activePerks = new ArrayList<>();
        perksPlayers.add(this);
    }

    public List<Perk> getActivePerks(){
        return this.activePerks;
    }

    public void setPerkActive(Perk perk){
        if(this.getActivePerks().contains(perk)){
            return;
        }

        Send.console("added");
        this.activePerks.add(perk);

    }

    public void removeActivePerk(Perk perk){
        this.activePerks.remove(perk);
    }

    public List<Perk> getPerks() {
        return this.perks;
    }

    public Boolean hasPerk(Perk perk){
        for(Perk p : this.getPerks()){
            if(p.equals(perk)){
                return true;
            }
        }
        return false;
    }

    public int getTokens(){
        return this.tokens;
    }

    public static PerksPlayer getByPlayer(Player player){
        if(perksPlayers == null){perksPlayers = new ArrayList<>();}
        for(PerksPlayer pp : perksPlayers){
            if(pp.getPlayer().equals(player)){
                return pp;
            }
        }
        return null;
    }

    public Player getPlayer(){
        return Bukkit.getPlayer(this.playerUUID);
    }

    public void attemptPurchase(Perk perk){
        if(this.getPerks().contains(perk)){Send.player(this.getPlayer(), ChatColor.RED + "You already have this perk!"); return;}
        if(this.getTokens() >= perk.getCost()){
            this.givePerk(perk);
            this.tokens = this.tokens - perk.getCost();
            Send.player(this.getPlayer(), ChatColor.GREEN + "Successfuly unlocked for the cost of " + perk.getCost() + "" + Token.getSymbol());
        }else{
            Send.player(this.getPlayer(), ChatColor.RED + "You do not have enough tokens to unlock this!");
        }
    }

    public void giveToken(int amount){
        this.tokens += amount;
    }

    public void givePerk(Perk perk){
        this.perks.add(perk);
        if (perk.getName().equals(Perk.Parachute.getName())) {
        Parachute.addParachute(new Parachute(this.getPlayer()));
        }
    }

    public void disable(){
        for(Perk p : this.getPerks()){
            switch (p.getName()){
                case "Solo":
                    for(Player player : Bukkit.getOnlinePlayers()){
                        this.getPlayer().showPlayer(player);
                    }
                    this.removeActivePerk(Perk.Solo);
            }
        }
    }

    public UUID getPlayerUUID() {
        return this.playerUUID;
    }
}