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

package org.stroyer.perks.Perks.PerkActions;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.stroyer.perks.Main;
import org.stroyer.perks.Perks.Perk;
import org.stroyer.perks.Perks.PerkObject;
import org.stroyer.perks.Player.PerksPlayer;
import org.stroyer.perks.Util.Send;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PerkSoloCommand implements CommandExecutor {

    private final Main main;
    public PerkSoloCommand(Main main) {this.main = main; }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)){
            Send.console("A player must execute this command.");
            return true;
        }

        Player p = (Player) sender;

        List<Perk> perks = new ArrayList<>();
        PerksPlayer pp = PerksPlayer.getByPlayer(p);
        for(Perk perk : pp.getPerks()){
            perks.add(perk);
        }
        Boolean found = false;
        for(Perk prk : perks){
            if(prk.getName().equals(Perk.Solo.getName())){
                found = true;
            }
        }
        if(!found){
            Send.player(p, ChatColor.RED + "Unlock this in /perk");
            return true;
        }


        if(Objects.requireNonNull(PerksPlayer.getByPlayer(p)).getActivePerks().contains(Perk.Solo)){
            Objects.requireNonNull(PerksPlayer.getByPlayer(p)).removeActivePerk(Perk.Solo);
            Send.player(p, "Deactivated Solo Perk!");
            for(Player player : Bukkit.getOnlinePlayers()){
                p.showPlayer(player);
            }
            return true;
        }
        for(Player player : Bukkit.getOnlinePlayers()){
            p.hidePlayer(player);
        }
        PerksPlayer.getByPlayer(p).setPerkActive(Perk.Solo);
        Send.player(p, "Activated Solo Perk!");

        return true;
    }
}
