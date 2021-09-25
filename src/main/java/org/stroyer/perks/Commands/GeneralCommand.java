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

import java.util.Arrays;
import java.util.List;

public class GeneralCommand implements CommandExecutor {

    private final Main main;
    public GeneralCommand(Main main) {this.main = main; }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)){
            if(args.length == 2){
                if(args[0].equalsIgnoreCase("givetoken")){
                    for(Player p : Bukkit.getOnlinePlayers()){
                        if(p.getUniqueId().equals(Bukkit.getPlayer(args[1]).getUniqueId())){
                            PerksPlayer.getByPlayer(p).giveToken(1);
                            Send.console("Gave " + args[1] + " a token.");
                            Send.player(p, "You recieved a token!");
                            return true;
                        }
                    }
                    Send.console(ChatColor.RED + "Could not find player " + args[1]);
                    return true;
                }
            }
            if(args.length == 3){
                if(args[0].equalsIgnoreCase("givetoken")){
                    for(Player p : Bukkit.getOnlinePlayers()){
                        if(p.getUniqueId().equals(Bukkit.getPlayer(args[1]).getUniqueId())){
                            PerksPlayer.getByPlayer(p).giveToken(Integer.parseInt(args[2]));
                            Send.console("Gave " + args[1] + " " + args[2] + " tokens.");
                            Send.player(p, "You recieved " + args[2] + " tokens!");
                            return true;
                        }
                    }
                    Send.console(ChatColor.RED + "Could not find player " + ChatColor.DARK_RED + args[1]);
                    return true;
                }
            }
            Send.console("Perks by Stroyer_");
            return true;
        }


        Player player = (Player) sender;



        if(args.length == 0){
            Send.player(player, "Perks by Stroyer_");
            MainGUI.open(player);
            return true;
        }

        if(!player.hasPermission("perks.admin")){
            Send.player(player, ChatColor.RED + "You do not have the " + ChatColor.DARK_RED + "perks.admin" + ChatColor.RED + " permission.");
            return true;
        }

        if(args.length == 1){

            if(args[0].equalsIgnoreCase("get")){
                for(Player p : Bukkit.getOnlinePlayers()){
                    if(p.getName().equalsIgnoreCase(ChatColor.stripColor(args[1]))){
                        Send.player(player, "Player " + ChatColor.YELLOW + args[1] + ChatColor.GREEN + " has " + ChatColor.YELLOW + PerksPlayer.getByPlayer(p).getTokens() + ChatColor.GREEN + " tokens.");
                        return true;
                    }
                }
                Send.player(player, ChatColor.DARK_RED + args[1] + ChatColor.RED + " is not online.");
                return true;
            }

            if(args[0].equalsIgnoreCase("debug")){
                if(player.hasPermission("perks.debug")){
                    List<String> msg = Arrays.asList(
                            ChatColor.GOLD + "PerksPlayer Array Size: " + ChatColor.YELLOW + PerksPlayer.perksPlayers.size(),
                            ChatColor.GOLD + "PerksPlayerByPlayer found of sender: " + ChatColor.YELLOW + PerksPlayer.getByPlayer(player).getPlayer().getDisplayName() + ChatColor.GOLD + " with UUID of " + ChatColor.YELLOW + PerksPlayer.getByPlayer(player).getPlayerUUID(),
                            ChatColor.GOLD + "PerksPlayerByPlayer perks of sender: " + ChatColor.YELLOW + "" + PerksPlayer.getByPlayer(player).getPerks().size(),
                            ChatColor.GOLD + "Found PerksPlayer by sender UUID: " + ChatColor.YELLOW + (PerksPlayer.getByPlayer(player).getPlayer().equals(player)),
                            ChatColor.GOLD + "PerksPlayer tokens of: " + ChatColor.YELLOW + PerksPlayer.getByPlayer(player).getTokens()
                    );

                    Send.playerMultipleLines(player, "Debug stats", msg);
                }else{
                    Send.player(player, ChatColor.RED + "You do not have sufficient permissions to execute this command.");
                }
                return true;
            }
        }

        if(args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                String[] helpMsgs = new String[]{
                        ChatColor.GOLD + "/perks" + ChatColor.YELLOW + " Open Perks GUI",
                        ChatColor.GOLD + "/perks debug" + ChatColor.YELLOW + " Get debug stats.",
                        ChatColor.GOLD + "/perks givetoken <Player> [amount]" + ChatColor.YELLOW + " Gives the indicated player a token, or a certain amount of tokens if you specify.",
                        ChatColor.GOLD + "/perks get <Player> " + ChatColor.YELLOW + "Returns the amount of tokens the player has.",
                        ChatColor.GOLD + "/perks help" + ChatColor.YELLOW + " Returns this menu."
                };
                Send.playerMultipleLines(player, "Perks Command Help", Arrays.asList(helpMsgs));
                return true;
            }
        }
        if(args.length == 2){
            if(args[0].equalsIgnoreCase("givetoken")){
                for(Player p : Bukkit.getOnlinePlayers()){
                    if(p.getUniqueId().equals(Bukkit.getPlayer(args[1]).getUniqueId())){
                        PerksPlayer.getByPlayer(p).giveToken(1);
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
                        PerksPlayer.getByPlayer(p).giveToken(Integer.parseInt(args[2]));
                        Send.player(player, "Gave " + args[1] + " " + args[2] + " tokens.");
                        Send.player(p, "You recieved " + args[2] + " tokens!");
                        return true;
                    }
                }
                Send.player(player, ChatColor.RED + "Could not find player " + ChatColor.DARK_RED + args[1]);
                return true;
            }
        }

        Send.player(player, ChatColor.RED + "Unkown command. Use /perks help");
        return true;
    }
}
