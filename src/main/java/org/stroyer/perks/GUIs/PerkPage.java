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
import org.stroyer.perks.Perks.Perk;
import org.stroyer.perks.Player.PerksPlayer;
import org.stroyer.perks.Tokens.Token;
import org.stroyer.perks.Util.FillBlank;
import org.stroyer.perks.Util.NewItem;

import java.util.Arrays;
import java.util.List;

public class PerkPage {
    public static Inventory inv = Bukkit.createInventory(null, 54, "");
    public static ItemStack back = NewItem.createGuiItem(Material.BARRIER, ChatColor.RED + "Back");
    public static ItemStack unlock = NewItem.createGuiItem(Material.EMERALD_BLOCK, ChatColor.GREEN + "Unlock now!");
    private static Perk currentPerk;
    public static ItemStack usage;
    public static ItemStack description;

    public static void open(Player player, Perk perk){
        currentPerk = perk;
        PerksPlayer pp = PerksPlayer.getByPlayer(player);
        ItemStack details = NewItem.createGuiItem(Material.PLAYER_HEAD, ChatColor.GRAY + player.getName(), ChatColor.GRAY + "" + pp.getTokens() + "" + Token.getSymbol());
        ItemMeta unlockMeta = unlock.getItemMeta();
        usage = NewItem.createGuiItem(Material.PAPER, ChatColor.GOLD + "Usage");
        ItemMeta usageMeta = usage.getItemMeta();
        usageMeta.setLore(Arrays.asList(perk.getUsage()));
        usage.setItemMeta(usageMeta);
        description = NewItem.createGuiItem(Material.FILLED_MAP, ChatColor.GOLD + "Description");
        ItemMeta descMeta = description.getItemMeta();
        descMeta.setLore(Arrays.asList(perk.getDescription()));
        description.setItemMeta(descMeta);
        List<String> unlockLore = Arrays.asList(new String[]{
                ChatColor.GOLD + perk.getName(), ChatColor.YELLOW + "" + perk.getCost() + "" + Token.getSymbol()
        });
        unlockMeta.setLore(unlockLore);
        unlock.setItemMeta(unlockMeta);

        SkullMeta sm = (SkullMeta) details.getItemMeta();
        sm.setOwner(player.getName());
        sm.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + player.getName());

        inv.setItem(45, back);
        inv.setItem(53, details);
        inv.setItem(20, description);
        inv.setItem(22, unlock);
        inv.setItem(24, usage);

        inv = FillBlank.updateInventory(inv);
        player.openInventory(inv);
    }

    public static void event(InventoryClickEvent e){
        if(e.getCurrentItem().equals(back)){
            AllPerks.open((Player) e.getWhoClicked());
        }
        if(e.getCurrentItem().equals(unlock)){
            PerksPlayer.getByPlayer((Player) e.getWhoClicked()).attemptPurchase(currentPerk);
            e.getWhoClicked().closeInventory();
        }
    }

}
