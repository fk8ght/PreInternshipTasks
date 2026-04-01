package by.innowise.task.third;

import java.util.concurrent.*;

class Simulation {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        for(int i = 0; i < 20; i++){
            start();
        }
    }

    private static void start() throws InterruptedException, ExecutionException {
        Factory factory = new Factory();
        Faction faction1 = new Faction("First", factory);
        Faction faction2 = new Faction("Second", factory);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        for(int i = 0; i < 100; i++){
            factory.run();

            Future<Integer> f1 = executor.submit(faction1);
            Future<Integer> f2 = executor.submit(faction2);

            f1.get();
            f2.get();
        }

        System.out.println("Фракция 1: " + faction1.getTotalRobots());
        System.out.println("Фракция 2: " + faction2.getTotalRobots());

        executor.shutdown();
    }
}
