package ru.nsu.chernikov;

/**
 * Represents a pizza order.
 */
public class Order {
    private final Integer id;
    private final Integer countofPizzas;
    private OrderState state;

    /**
     * Creates an order with a given ID and pizza count.
     *
     * @param id            the order ID
     * @param countofPizzas the number of pizzas in the order
     */
    public Order(Integer id, Integer countofPizzas) {
        this.id = id;
        this.countofPizzas = countofPizzas;
    }

    /** GET COUNT OF PIZZA IN ORDER.
     * @return the number of pizzas in the order
     */
    public Integer getCountofPizzas() {
        return countofPizzas;
    }

    /** GET ID.
     * @return the order ID
     */
    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Order #" + id + ", Pizzas: " + countofPizzas;
    }

    /** GET STATE.
     * @return the current order state
     */
    public OrderState getState() {
        return state;
    }

    /** GET STATE.
     * Marks the order as being cooked.
     */
    public void cooking() {
        this.state = OrderState.COOKING;
        System.out.println("Order #" + this.id + " " + this.state);
    }

    /** GET STATE.
     * Marks the order as being delivered.
     */
    public void delivering() {
        this.state = OrderState.DELIVERING;
        System.out.println("Order #" + this.id + " " + this.state);
    }

    /**
     * Marks the order as completed.
     */
    public void done() {
        this.state = OrderState.DONE;
        System.out.println("Order #" + this.id + " " + this.state);
    }

    /**
     * Marks the order as delivered.
     */
    public void delivered() {
        this.state = OrderState.DELIVERED;
        System.out.println("Order #" + this.id + " " + this.state);
    }

    /**
     * Marks the order as waiting in the order list.
     */
    public void waiting() {
        this.state = OrderState.IN_ORDERLIST;
        System.out.println("Order #" + this.id + " " + this.state);
    }
}