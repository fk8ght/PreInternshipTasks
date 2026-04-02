package by.innowise.task.third;

import java.util.Map;

class RobotBuilder {
    public boolean tryCreate(Map<PartType, Integer> privateInventory){
        if(privateInventory.get(PartType.HAND) >= 2 && privateInventory.get(PartType.FEET) >= 2
                && privateInventory.get(PartType.HEAD) >= 1 && privateInventory.get(PartType.TORSO) >= 1){
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
