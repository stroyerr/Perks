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

package org.stroyer.perks.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.stroyer.perks.GUIs.guiobjects.TradeInModifierObject;
import org.stroyer.perks.Player.PerksPlayer;
import org.stroyer.perks.Tokens.Token;
import org.stroyer.perks.Util.FillBlank;
import org.stroyer.perks.Util.NewItem;

public class MainGUI {
    public static Inventory inv = Bukkit.createInventory(null, 27, "Perks");
    public static ItemStack myPerks = NewItem.createGuiItem(Material.EMERALD_BLOCK, ChatColor.GREEN + "My Perks", ChatColor.DARK_GREEN + "View and manage your perks!");
    public static ItemStack allPerks = NewItem.createGuiItem(Material.GOLD_BLOCK, ChatColor.GOLD + "All perks", ChatColor.YELLOW + "View all perks");
    public static ItemStack settings = NewItem.createGuiItem(Material.DIAMOND_CHESTPLATE, ChatColor.AQUA  + "" + ChatColor.BOLD + "Style", ChatColor.DARK_AQUA + "Coming soon!");
    public static ItemStack tradeIn = NewItem.createGuiItem(Material.GOLD_INGOT, ChatColor.GOLD + "Trade in your " + ChatColor.GOLD + Token.getSymbol());
    public static ItemStack playerDetails;

    public static void open(Player player){

        inv = Bukkit.createInventory(null, 27, "Perks");
        playerDetails = NewItem.createGuiItem(Material.PLAYER_HEAD, ChatColor.WHITE + "" + ChatColor.BOLD + player.getName(), ChatColor.YELLOW + "Your perks: " + ChatColor.GOLD + PerksPlayer.getByPlayer(player).getPerks().size() + "   " + ChatColor.WHITE + "&" +  "   "+ ChatColor.LIGHT_PURPLE + PerksPlayer.getByPlayer(player).getTokens() + " " + Token.getSymbol());
        SkullMeta headMeta = (SkullMeta) playerDetails.getItemMeta();
        headMeta.setOwner(player.getName());
        playerDetails.setItemMeta(headMeta);

        inv.setItem(10, allPerks);
        inv.setItem(12, myPerks);
        inv.setItem(14, tradeIn);
        inv.setItem(16, settings);
        inv.setItem(26, playerDetails);
        inv = FillBlank.updateInventory(inv);
        player.openInventory(inv);
    }

    public static void inventoryEvent(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getCurrentItem() == null){return;}
        if(e.getCurrentItem().equals(myPerks)){
            MyPerks.open(p);
        }
        if(e.getCurrentItem().equals(allPerks)){
            AllPerks.open(p);
        }
        if(e.getCurrentItem().equals(tradeIn)){
            TradeInGUI.open(p);
        }
    }
}
