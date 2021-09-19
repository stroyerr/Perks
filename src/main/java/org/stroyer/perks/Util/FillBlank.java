package org.stroyer.perks.Util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class FillBlank {
    public static Inventory updateInventory(Inventory inv){

        Inventory newInv = inv;
        ItemStack blank = NewItem.createGuiItem(Material.BLACK_STAINED_GLASS_PANE, ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Perks by Stroyer_");

        for(int i = 0; i < inv.getSize(); i++){
            if(inv.getItem(i) == null){
                newInv.setItem(i, blank);
            }
        }

        return newInv;

    }
}
