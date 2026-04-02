package by.innowise.task.third;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;

class Factory implements Callable<Integer> {
    private Map<PartType, Integer> inventory;

    Factory(){
        inventory = new HashMap<>();
        for(int i = 0; i < 4; i++){
            inventory.put(PartType.values()[i], 0);
        }
    }

    @Override
    public Integer call(){
        Random random = new Random();
        int countOfParts = random.nextInt(10) + 1;

        for(int i = 0; i < countOfParts; i++){
            PartType type = PartType.values()[random.nextInt(4)];
            inventory.put(type, inventory.get(type) + 1);
        }

        return inventory.size();
    }

    public synchronized boolean tryGrab(PartType type){
        if(inventory.get(type) != 0){
            inventory.put(type, inventory.get(type) - 1);
            return true;
        } else {
            return false;
        }
    }
}
