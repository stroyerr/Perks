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
import org.stroyer.perks.Util.FillBlank;
import org.stroyer.perks.Util.NewItem;

public class MainGUI {
    public static Inventory inv = Bukkit.createInventory(null, 27, "Perks");
    public static ItemStack myPerks = NewItem.createGuiItem(Material.EMERALD_BLOCK, ChatColor.GREEN + "My Perks", ChatColor.DARK_GREEN + "View and manage your perks!");
    public static ItemStack allPerks = NewItem.createGuiItem(Material.GOLD_BLOCK, ChatColor.GOLD + "All perks", ChatColor.YELLOW + "View all perks");
    public static ItemStack settings = NewItem.createGuiItem(Material.REDSTONE, ChatColor.GRAY + "Settings");

    public static void open(Player player){
        inv.setItem(11, allPerks);
        inv.setItem(13, myPerks);
        inv.setItem(15, settings);
        inv = FillBlank.updateInventory(inv);
        player.openInventory(inv);
    }

    public static void inventoryEvent(InventoryClickEvent e){
        if(e.getCurrentItem().equals(myPerks)){

        }
    }
}
