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
    private int id;
    private String[] usage;

    public static Perk Solo = new Perk("Solo", 1, new String[]{"Expirience Solo Play!", "Toggle other players visibility!"}, new String[]{"Use /solo to toggle this perk"});


    private static List<Perk> allPerks = Arrays.asList(new Perk[]{
            Solo
    });

    public Perk(String name, int cost, String[] description, String[] usage){
        this.name = name;
        this.cost = cost;
        this.usage = usage;
        this.description = description;
        if(allPerks == null){
            this.id = 0;
        }else{
            this.id = allPerks.size();
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
