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

package org.stroyer.perks.Style.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.stroyer.perks.Util.FillBlank;
import org.stroyer.perks.Util.NewItem;

public class StylesGUI {
    public static Inventory inv = Bukkit.createInventory(null, 45, "Styles");
    public static final ItemStack halo = NewItem.createGuiItem(Material.LEATHER_HELMET, ChatColor.RED + "Halo");
    public static final ItemStack trail = NewItem.createGuiItem(Material.BLAZE_POWDER, ChatColor.GOLD + "Trail");
    public static final ItemStack back = NewItem.createGuiItem(Material.ARROW, ChatColor.WHITE + "Back");

    public static void openMain(Player player){
        inv = Bukkit.createInventory(null, 45, "Styles");
        inv.setItem(35, back);
        inv.setItem(22, halo);
        inv.setItem(24, trail);
        inv = FillBlank.updateInventory(inv);
        player.openInventory(inv);
    }
}
