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
import org.stroyer.perks.GUIs.guiobjects.TradeInModifierObject;
import org.stroyer.perks.Internal.Perks;
import org.stroyer.perks.Player.PerksPlayer;
import org.stroyer.perks.Tokens.Token;
import org.stroyer.perks.Util.FillBlank;
import org.stroyer.perks.Util.NewItem;
import org.stroyer.perks.Util.Send;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TradeInGUI {

    public static Inventory mainInv;
    public static List<TradeInModifierObject> objs = new ArrayList<>();
    private static int tokensToTrade = 0;
    private static PerksPlayer perksPlayer;
    private static ItemStack key = NewItem.createGuiItem(Material.TRIPWIRE_HOOK, ChatColor.AQUA + "Vote Key", "Get 1 vote key for 2 tokens.");
    private static ItemStack back = NewItem.createGuiItem(Material.ARROW, ChatColor.GRAY + "Back");
    private static ItemStack money = NewItem.createGuiItem(Material.GOLD_BLOCK, ChatColor.YELLOW + "Cash out", "Cashout $" + 1000*tokensToTrade + "");
    public static void open(Player player){
        perksPlayer = PerksPlayer.getByPlayer(player);
        tokensToTrade = 0;
        mainInv = Bukkit.createInventory(null, 45, "Trade in your tokens");
        objs = Arrays.asList(
                new TradeInModifierObject(-10),
                new TradeInModifierObject(-5),
                new TradeInModifierObject(-1),
                new TradeInModifierObject(0),
                new TradeInModifierObject(1),
                new TradeInModifierObject(5),
                new TradeInModifierObject(10)
        );
        for(TradeInModifierObject modObj : objs){
            mainInv.setItem(19 + objs.indexOf(modObj), modObj.itemStack);
        }

        ItemStack currentTk = NewItem.createGuiItem(Material.WHITE_STAINED_GLASS_PANE, ChatColor.WHITE + "" + ChatColor.BOLD + "Currently trading " + ChatColor.GRAY + tokensToTrade + Token.getSymbol(), ChatColor.DARK_GRAY + "You have " + perksPlayer.getTokens() + Token.getSymbol());

        mainInv.setItem(13, money);

        mainInv.setItem(22, currentTk);

        mainInv.setItem(31, key);

        mainInv.setItem(36, back);

        mainInv = FillBlank.updateInventory(mainInv);

        player.openInventory(mainInv);
    }

    public static void event(InventoryClickEvent e){
        if(e.getCurrentItem() == null){return;}
        Player p = (Player) e.getWhoClicked();
        if(e.getCurrentItem().equals(back)){
            MainGUI.open(p);
            return;
        }
        if(e.getCurrentItem().getType().equals(money.getType())){
            if(tokensToTrade == 0){return;}
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p.getName() + " " + tokensToTrade * 1000);
            perksPlayer.takeTokens(tokensToTrade);
            Send.broadcast(p.getDisplayName() + ChatColor.GREEN + " has traded in " + ChatColor.DARK_GREEN + tokensToTrade + Token.getSymbol() + ChatColor.GREEN + " for " + ChatColor.DARK_GREEN + "$" + 1000*tokensToTrade);
            tokensToTrade = 0;
            update(p);
            p.closeInventory();
            return;
        }
        if(e.getCurrentItem().getType().equals(key.getType())){
            if(tokensToTrade < 2){return;}
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "goldencrates givekey " + p.getName() + " vote " + (tokensToTrade / 2 - ((tokensToTrade % 2) / 2)));
            perksPlayer.takeTokens((tokensToTrade / 2 - ((tokensToTrade % 2) / 2) )* 2);
            Send.broadcast(p.getDisplayName() + ChatColor.GREEN + " has traded in " + ChatColor.DARK_GREEN + "" + (tokensToTrade - (tokensToTrade % 2) / 2) + "" + Token.getSymbol() + ChatColor.GREEN + " for " + ChatColor.DARK_GREEN + "" + (tokensToTrade / 2 - ((tokensToTrade % 2) / 2)) + " Vote Keys");
            update(p);
            p.closeInventory();
            return;
        }
        for(TradeInModifierObject timo : objs){
            if(timo.itemStack.equals(e.getCurrentItem())){
                int newTk = tokensToTrade + timo.modifier;
                if((newTk) < 0){return;}
                if(newTk > perksPlayer.getTokens()){return;}
                tokensToTrade += timo.modifier;
                update(p);
                return;
            }
        }
    }

    public static void update(Player p){
        p.updateInventory();
        p.getOpenInventory().setItem(22,NewItem.createGuiItem(Material.WHITE_STAINED_GLASS_PANE, ChatColor.WHITE + "" + ChatColor.BOLD + "Currently trading " + ChatColor.GRAY + tokensToTrade + Token.getSymbol(), ChatColor.DARK_GRAY + "You have " + perksPlayer.getTokens() + Token.getSymbol()));
        p.getOpenInventory().setItem(13, NewItem.createGuiItem(Material.GOLD_BLOCK, ChatColor.YELLOW + "Cash out", "Cashout $" + 1000*tokensToTrade + ""));
        if(tokensToTrade < 2){
            p.getOpenInventory().setItem(31, NewItem.createGuiItem(Material.TRIPWIRE_HOOK, ChatColor.AQUA + "Vote Key", "You must trade 2 or more tokens."));
        }else{
            p.getOpenInventory().setItem(31, NewItem.createGuiItem(Material.TRIPWIRE_HOOK, ChatColor.AQUA + "Vote Key", "You can get " + (tokensToTrade / 2 - ((tokensToTrade % 2) / 2)) + " keys for " + tokensToTrade + Token.getSymbol()));
        }
    }
}
