package by.innowise.task.third;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;

class Faction implements Callable<Integer> {
    private final String name;
    private final Factory factory;
    private final RobotBuilder builder;
    private final Map<PartType, Integer> privateInventory;
    @Getter
    private int totalRobots;

    Faction(String name, Factory factory, RobotBuilder builder){
        this.name = name;
        this.factory = factory;
        this.builder = builder;

        privateInventory = new HashMap<>();
        for(int i = 0; i < 4; i++){
            privateInventory.put(PartType.values()[i], 0);
        }
    }

    @Override
    public Integer call() {
        Random random = new Random();

        for(int i = 0; i < 5; i++) {
            //вместо рандома логику надо додумать
            PartType typeToGrab = PartType.values()[random.nextInt(4)];
            if(factory.tryGrab(typeToGrab)){
                privateInventory.put(typeToGrab, privateInventory.get(typeToGrab) + 1);
            }
        }

        while(builder.tryCreate(privateInventory)){
            totalRobots++;
        }

        return totalRobots;
    }
}
