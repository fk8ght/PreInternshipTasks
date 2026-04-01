package by.innowise.task.third;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;

class Faction implements Callable<Integer> {
    private final String name;
    private final Factory factory;
    private final Map<PartType, Integer> privateInventory;
    @Getter
    private int totalRobots;

    Faction(String name, Factory factory){
        this.name = name;
        this.factory = factory;

        privateInventory = new HashMap<>();
        for(int i = 0; i < 4; i++){
            privateInventory.put(PartType.values()[i], 0);
        }
    }

    @Override
    public Integer call() {
        Random random = new Random();

        for(int i = 0; i < 5; i++) {
            //вместо рандома логику надо додумать и поменять логику если не находит
            PartType typeToGrab = PartType.values()[random.nextInt(4)];
            if(factory.tryGrab(typeToGrab)){
                privateInventory.put(typeToGrab, privateInventory.get(typeToGrab) + 1);
            }
        }

        while(tryCreate()){
            totalRobots++;
        }

        return totalRobots;
    }

    private boolean tryCreate(){
        if(privateInventory.get(PartType.HAND) > 2 && privateInventory.get(PartType.FEET) > 2
                && privateInventory.get(PartType.HEAD) > 1 && privateInventory.get(PartType.TORSO) > 1){
            privateInventory.put(PartType.HAND, privateInventory.get(PartType.HAND) - 2);
            privateInventory.put(PartType.FEET, privateInventory.get(PartType.FEET) - 2);
            privateInventory.put(PartType.HEAD, privateInventory.get(PartType.HEAD) - 1);
            privateInventory.put(PartType.TORSO, privateInventory.get(PartType.TORSO) - 1);
            return true;
        } else{
            return false;
        }
    }
}
