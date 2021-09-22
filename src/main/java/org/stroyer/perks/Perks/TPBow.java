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

package org.stroyer.perks.Perks;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.stroyer.perks.Util.Send;

import java.util.ArrayList;
import java.util.List;

public class TPBow {
    private Player player;
    private int timeElapsed;
    private boolean isReloading;
    private static List<TPBow> allBows = new ArrayList<>();
    public TPBow(Player p){
        this.player = p;
        this.timeElapsed = 0;
        this.isReloading = false;
    }

    public void attemptReload(){
        if(this.isReloading){
            Send.player(this.player, ChatColor.RED + "You're");
            return;
        }
    }

    public static TPBow getTPBow(Player player){
        for(TPBow bow : allBows){
            if(bow.player.equals(player)){
                return bow;
            }
        }
        return null;
    }
}
