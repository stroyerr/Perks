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

package org.stroyer.perks.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.stroyer.perks.GUIs.AllPerks;
import org.stroyer.perks.GUIs.MainGUI;
import org.stroyer.perks.GUIs.MyPerks;

public class InventoryClick implements Listener {

    @EventHandler
    public static void onClick(InventoryClickEvent e){
        if(e.getInventory().equals(MainGUI.inv)){
            e.setCancelled(true);
            MainGUI.inventoryEvent(e);
        }
        if(e.getInventory().equals(MyPerks.inv)){
            e.setCancelled(true);
            MyPerks.event(e);
        }
        if(e.getInventory().equals(AllPerks.inv)){
            e.setCancelled(true);
            AllPerks.event(e);
        }
    }
}
