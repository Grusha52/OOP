package ru.nsu.chernikov;


import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Pizzeria {
    public final Storage storage = new Storage();
    public final Orders orders = new Orders();
    private final Integer numberOfBakers;
    private final Integer numberOfCouriers;
    private final Integer workTimeSeconds;
    private final Integer maxOrders;
    private volatile boolean isOpen = true;
    Thread[] workers;

    Pizzeria(PizzeriaConfig config) {
        this.numberOfBakers = config.countOfBakers();
        this.numberOfCouriers = config.countOfCouriers();
        this.maxOrders = config.maxOrders();
        this.workTimeSeconds = config.workTime();
    }

    private void workingtill6() throws InterruptedException {
        TimeUnit.SECONDS.sleep(this.workTimeSeconds);
        isOpen = false;
        for(Thread worker : workers) {
            worker.interrupt();
        }
        for(Thread worker : workers) {
            worker.join();
        }
    }

    private void workingProcess() throws InterruptedException {
        Thread working = new Thread(() -> {
            try {
                workingtill6();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        working.start();
        Thread thread = new Thread(() -> {
            int i = 0;
            while(i < this.maxOrders && this.isOpen) {
                i++;
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                    orders.toOrders(new Random().nextInt(5));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();

        Baker[] bakers = new Baker[numberOfBakers];
        Courier[] couriers = new Courier[numberOfCouriers];

        for (int i = 0; i < numberOfBakers; i++) {
            bakers[i] = new Baker(this);
            bakers[i].start();
        }
        for (int i = 0; i < numberOfCouriers; i++) {
            couriers[i] = new Courier(this);
            couriers[i].start();
        }
        workers = new Thread[numberOfCouriers + numberOfBakers];
        System.arraycopy(bakers, 0, workers, 0, numberOfBakers);
        System.arraycopy(couriers, 0, workers, numberOfBakers, numberOfCouriers);
    }

    public static void main(String[] args) {
        Pizzeria pizzeria = new Pizzeria(PizzeriaConfig.loadConfig("config.json"));
        try {
            pizzeria.workingProcess();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
