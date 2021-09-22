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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Perk implements Serializable {
    private String name;
    private int cost;
    private String[] description;
    protected final int id;
    private String[] usage;

    public static Perk Solo = new Perk("Solo", 1, new String[]{"Expirience Solo Play!", "Toggle other players visibility!"}, new String[]{"Use /solo to toggle this perk"});
// Coming soon!    public static Perk Launch = new Perk("Launch", 1, new String[]{"Launch into the sky!","Get a boost to start","your elytra flight!"}, new String[]{"Hold crouch then jump","to launch into the sky!"});
    public static Perk SuperSpy = new Perk("Super Spy", 1, new String[]{"Spy on people","from any distance!"}, new String[]{"Use /superspy to get","your Super Spy tool!"});
    public static Perk TPBow = new Perk("Teleport Bow", 2, new String[]{"Teleport by shotting","a bow!"}, new String[]{ "Use /tpbow or /bow","to get your bow!"});
    public static Perk NightVision = new Perk("Night Vision", 2, new String[]{"Get permanent night vision!"}, new String[]{"Use /nightvision or /nv to","toggle night vision!"});
    public static Perk PunchGun = new Perk("Punch Gun", 3, new String[]{"Knock other players back!", "Use your punch gun to gain distance!"}, new String[]{"Use /punch to get your", "punch gun!"});
    public static Perk Parachute = new Perk("Parachute", 3, new String[]{"Never take fall damage again!", "Save yourself from heights!"}, new String[]{"When falling from more","than five blocks, your","parachute will automatically","deploy, saving you","from fall damage!"});

    private static List<Perk> allPerks = Arrays.asList(Solo, TPBow, PunchGun, SuperSpy, NightVision, Parachute);

    public Perk(String name, int cost, String[] description, String[] usage){
        this.name = name;
        this.cost = cost;
        this.usage = usage;
        this.description = description;
        if(allPerks == null){
            this.id = 0;
//            allPerks = Arrays.asList(Solo, TPBow, PunchGun);
            allPerks = new ArrayList<>();
            allPerks.add(this);
        }else{
            this.id = allPerks.size();
            allPerks.add(this);
        }
    }

    public String[] getUsage(){
        return this.usage;
    }

    public static void initialise(){
        allPerks.add(Solo);
    }

    public int getId(){
        return this.id;
    }

    public static List<Perk> getAllPerks() {
        return allPerks;
    }

    public int getCost(){
        return this.cost;
    }

    public String getName(){
        return this.name;
    }

    public String[] getDescription(){
        return this.description;
    }

    public Perk get(String name){
        for(Perk p : allPerks){
            if(p.name.equals(name)){
                return p;
            }
        }
        return null;
    }
}
