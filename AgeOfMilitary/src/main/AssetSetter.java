package main;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import object.OBJ_boost;
import object.OBJ_heartpower;
import object.OBJ_powerup;
import object.OBJ_shield;

public class AssetSetter {
    private GamePanel gp;
    
    //fixed coors for powerups
    //int spawnarea[][] = {{16,7},{35,18},{20,20},{20,14},{4,14},{9,23},{28,6}};
    private int spawnarea[][] = {{8,3},{12,7},{38,10},{25,13},{20,16},{40,19},{4,22},{22,24}};
    private List<Integer> setspawn = new ArrayList<>();
    private List<Integer> spawn;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        // set object placing with reference of map1.txt
        // need to do some check to make sure items only spawn on tile 0
    	if(setspawn != null) {
    		setspawn.clear(); // empty array after selecting coors, to ensure no repeating elements
    	}
    	for(int x=0;x<spawnarea.length;x++) {
    		setspawn.add(x);// storing coors list index into an array
    	}
        gp.obj[0] = new OBJ_powerup();
        gp.obj[1] = new OBJ_boost();
        gp.obj[2] = new OBJ_shield();
        gp.obj[3] = new OBJ_heartpower();
        
        spawn = getRandomElement(setspawn,4);// get new list with randomized 4 elements of range coors list index 
        
        for (int i=0;i<spawn.size();i++) {
        	gp.obj[i].setWorldX(spawnarea[spawn.get(i)][0]*gp.ORIGINAL_TILE_SIZE);
        	gp.obj[i].setWorldY(spawnarea[spawn.get(i)][1]*gp.ORIGINAL_TILE_SIZE);
        }
    }
    
    public List<Integer> getRandomElement(List<Integer> list, int totalItems)
    {
        Random rand = new Random();
 
        // create a temporary list for storing
        // selected element
        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i < totalItems; i++) {
 
            // take a random index between 0 to size
            // of given List
            int randomIndex = rand.nextInt(list.size());
 
            // add element in temporary list
            newList.add(list.get(randomIndex));
 
            // Remove selected element from original list
            list.remove(randomIndex);
        }
        return newList;
    }
}
