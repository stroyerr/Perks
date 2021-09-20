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

package org.stroyer.perks.Internal;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.stroyer.perks.Perks.Perk;
import org.stroyer.perks.Player.PerksPlayer;
import org.stroyer.perks.Util.Send;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PerkPlayerSerialization implements Serializable {
    private List<Perk> perks;
    private List<Perk> activePerks;
    private UUID playerUUID;
    private int tokens;
    private static List<PerkPlayerSerialization> serializations = new ArrayList<>();
    public PerkPlayerSerialization(PerksPlayer pp){
        this.perks = pp.getPerks();
        this.activePerks = pp.getActivePerks();
        this.playerUUID = pp.getPlayerUUID();
        this.tokens = pp.getTokens();
    }

    public static void save() throws IOException {
        List<PerksPlayer> allPerksPlayers = PerksPlayer.perksPlayers;
        Send.console("Attempting to save player data...");
        for(PerksPlayer pp : allPerksPlayers){
            serializations.add(new PerkPlayerSerialization(pp));
        }
        File f = new File("./plugins/Perks/player.data");
        Path path = Paths.get("./plugins/Perks");
        Send.console("Attempting to find directory...");
        if(!Files.exists(path)){
            Send.console("Directory not found, creating now. If this is not your first time using the plugin, this is a bug!");
            Files.createDirectory(path);
        }
        Send.console("Attempting to find player data file...");
        if(!f.exists()){
            Send.console("Player data file not found! Unless this is your first time using the plugin, this is a bug!");
            f.createNewFile();
        }
        FileOutputStream fOut = new FileOutputStream("./plugins/Perks/player.data");
        ObjectOutputStream oOut = new ObjectOutputStream(fOut);
        Send.console("Writing player data...");
        oOut.writeObject(serializations);
        Send.console("Finished writing player data.");
    }

    public static void load() throws IOException, ClassNotFoundException {
        FileInputStream fIn = new FileInputStream("./plugins/Perks/player.data");
        File f = new File("./plugins/Perks/player.data");
        if(!f.exists()){
            Send.console("Could not load any player data. Unless this is your first time running the plugin, this is a bug.");
            return;
        }
        ObjectInputStream oIn = new ObjectInputStream(fIn);
        Send.console("Reading player data...");
        List<PerkPlayerSerialization> list = (List<PerkPlayerSerialization>) oIn.readObject();
        List<PerksPlayer> newPlayers = new ArrayList<>();
        for(PerkPlayerSerialization pps : list){
            newPlayers.add(new PerksPlayer(pps.playerUUID, pps.tokens, pps.perks, pps.activePerks));
        }
        PerksPlayer.perksPlayers = newPlayers;
        Send.console("Finished reading player data.");
    }
}
