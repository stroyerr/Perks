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

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.stroyer.perks.GUIs.MainGUI;
import org.stroyer.perks.Main;
import org.stroyer.perks.Player.PerksPlayer;
import org.stroyer.perks.Util.Send;

public class GeneralCommand implements CommandExecutor {

    private final Main main;
    public GeneralCommand(Main main) {this.main = main; }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)){
            Send.console("Perks by Stroyer_");
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 0){
            Send.player(player, "Perks by Stroyer_");
            MainGUI.open(player);
            return true;
        }

        if(args.length == 2){
            if(args[0].equalsIgnoreCase("givetoken")){
                for(Player p : Bukkit.getOnlinePlayers()){
                    if(p.getUniqueId().equals(Bukkit.getPlayer(args[1]).getUniqueId())){
                        PerksPlayer.getByPlayer(player).giveToken(1);
                        Send.player(player, "Gave " + args[1] + " a token.");
                        Send.player(p, "You recieved a token!");
                        return true;
                    }
                }
                Send.player(player, ChatColor.RED + "Could not find player " + args[1]);
                return true;
            }
        }

        if(args.length == 3){
            if(args[0].equalsIgnoreCase("givetoken")){
                for(Player p : Bukkit.getOnlinePlayers()){
                    if(p.getUniqueId().equals(Bukkit.getPlayer(args[1]).getUniqueId())){
                        PerksPlayer.getByPlayer(player).giveToken(Integer.parseInt(args[2]));
                        Send.player(player, "Gave " + args[1] + " " + args[2] + " tokens.");
                        Send.player(p, "You recieved " + args[2] + " tokens!");
                        return true;
                    }
                }
                Send.player(player, ChatColor.RED + "Could not find player " + args[1]);
                return true;
            }
        }

        Send.player(player, ChatColor.RED + "Unkown command.");
        return true;
    }
}
