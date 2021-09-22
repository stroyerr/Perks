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

package org.stroyer.perks.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.stroyer.perks.Main;
import org.stroyer.perks.Perks.Perk;
import org.stroyer.perks.Perks.PunchGun.PunchGun;
import org.stroyer.perks.Player.PerksPlayer;
import org.stroyer.perks.Util.NewItem;
import org.stroyer.perks.Util.Send;

public class PerkPunchCommand implements CommandExecutor {

    public static ItemStack punchGunItem = NewItem.createGuiItem(Material.BLAZE_ROD, ChatColor.GOLD + "" + ChatColor.BOLD + "Punch Gun", ChatColor.RED + "Knock other players back!");

    private final Main main;
    public PerkPunchCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            Send.console("A player must execute this command.");
            return true;
        }
        Player p = (Player) sender;
        if(!PerksPlayer.getByPlayer(p).hasPerk(Perk.PunchGun)){
            Send.player(p, ChatColor.RED + "Unlock this perk in /perks");
            return true;
        }

        if(p.getInventory().getItemInMainHand().getType().isItem()){
            if(p.getInventory().getItemInMainHand().getType() != null){
                if(!p.getInventory().getItemInMainHand().getType().isAir()){
                    p.getWorld().dropItem(p.getLocation(), p.getInventory().getItemInMainHand());
                }

            }
        }

        if(p.getInventory().getItemInMainHand().equals(punchGunItem)){return true;}


        p.setItemInHand(punchGunItem);

        PunchGun newGun = new PunchGun(p);

        return true;
    }
}
