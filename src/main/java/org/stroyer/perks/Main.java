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

package org.stroyer.perks;

import org.bukkit.plugin.java.JavaPlugin;
import org.stroyer.perks.Commands.*;
import org.stroyer.perks.Internal.PerkPlayerSerialization;
import org.stroyer.perks.Listeners.*;
import org.stroyer.perks.Perks.Parachute;
import org.stroyer.perks.Perks.PunchGun.PunchGun;
import org.stroyer.perks.Perks.TPBow;

import java.io.IOException;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        try {
            PerkPlayerSerialization.load();
        } catch (IOException | ClassNotFoundException e) {}

        getCommand("punch").setExecutor(new PerkPunchCommand(this));
        getCommand("perks").setExecutor(new GeneralCommand(this));
        getCommand("solo").setExecutor(new PerkSoloCommand(this));
        getCommand("tpbow").setExecutor(new PerkTPBowCommand(this));
        getCommand("superspy").setExecutor(new PerkSuperSpyCommand(this));
        getServer().getPluginManager().registerEvents(new PerkParachuteListener(), this);
        getServer().getPluginManager().registerEvents(new PerkSuperSpyListener(), this);
        getServer().getPluginManager().registerEvents(new PerkTPBowListener(), this);
        getServer().getPluginManager().registerEvents(new PerkPunchGunListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);

        PunchGun.initialise();
        TPBow.initialise();
        Parachute.intitialise();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        try {
            PerkPlayerSerialization.save();
        } catch (IOException e) {}
    }
}
