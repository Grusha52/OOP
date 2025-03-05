package ru.nsu.chernikov;


import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Pizzeria {
    public final Storage storage = new Storage();
    public final Orders orders = new Orders();
    private final Integer numberOfBakers;
    private final Integer numberOfCouriers;
    private volatile boolean isOpen = true;
    Thread[] workers;

    Pizzeria(Integer numberOfBakers, Integer numberOfCouriers) {
        this.numberOfBakers = numberOfBakers;
        this.numberOfCouriers = numberOfCouriers;
    }

//    public boolean isOpen() {
//        return isOpen;
//    }

//    public boolean getOrder() {
//        synchronized (orderlock) {
//            if (!isOpen || pizzas == 0) {
//                return false;
//            } else {
//                pizzas -= 1;
//                return true;
//            }
//        }
//    }

//    public void addOrder() {
//        synchronized (orderlock) {
//            pizzas += 1;
//            orderlock.notify();
//        }
//    }


//    synchronized boolean fromStorage(){
//        synchronized (storagelock) {
//            if (!isOpen || storage == 0) {
//                return false;
//            } else {
//                storage -= 1;
//                return true;
//            }
//        }
//    }

//    public void toStorage() {
//        synchronized (storagelock) {
//            storage += 1;
//            storagelock.notify();
//        }
//    }

    private void workingtill6() throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
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
            while(i < 100) {
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
        Pizzeria pizzeria = new Pizzeria(10, 10);
        try {
            pizzeria.workingProcess();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
