package ru.nsu.chernikov;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Represents a pizzeria with bakers, couriers, and order processing.
 */
public class Pizzeria {
    public final Storage storage = new Storage();
    public final Orders orders = new Orders();
    private final Integer numberOfBakers;
    private final Integer numberOfCouriers;
    private final Integer workTimeSeconds;
    private final Integer maxOrders;
    Thread[] workers;
    private volatile boolean isOpen = true;

    /**
     * Initializes the pizzeria with configuration settings.
     *
     * @param config the pizzeria configuration
     */
    Pizzeria(PizzeriaConfig config) {
        this.numberOfBakers = config.countOfBakers();
        this.numberOfCouriers = config.countOfCouriers();
        this.maxOrders = config.maxOrders();
        this.workTimeSeconds = config.workTime();
    }

    /**
     * Entry point for the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Pizzeria pizzeria = new Pizzeria(PizzeriaConfig.loadConfig("config.json"));
        try {
            pizzeria.workingProcess();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Simulates the pizzeria operating until closing time.
     *
     * @throws InterruptedException if interrupted while sleeping or joining threads
     */
    public void workingtill6() throws InterruptedException {
        TimeUnit.SECONDS.sleep(this.workTimeSeconds);
        isOpen = false;
        for (Thread worker : workers) {
            worker.interrupt();
        }
        for (Thread worker : workers) {
            worker.join();
        }
    }

    /**
     * Starts the working process of the pizzeria, including order creation,
     * baking, and delivery.
     *
     * @throws InterruptedException if a thread is interrupted
     */
    public void workingProcess() throws InterruptedException {
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
            while (i < this.maxOrders && this.isOpen) {
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
}