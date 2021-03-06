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

package org.stroyer.perks.Util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.stroyer.perks.Main;

import java.util.List;

public class Send {

    public static String prefix = ChatColor.DARK_GREEN + "Perks v" + Bukkit.getPluginManager().getPlugin("Perks").getDescription().getVersion() + ChatColor.GRAY + "// " + ChatColor.GREEN;

    public static void player(Player p, String message){
        p.sendMessage(prefix + message);
    }
    public static void console(String message) {Bukkit.getLogger().info("[Perks] " + message);}
    public static void playerMultipleLines(Player p, String firstLine, List<String> messages){
        player(p, firstLine);
        for(String s : messages){
            p.sendMessage(s);
        }
    }
    public static void broadcast(String message){
        Bukkit.broadcastMessage(prefix + message);
    }
}
