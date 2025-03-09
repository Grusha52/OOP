package ru.nsu.chernikov;

public class Order {
    private Integer id;
    private final Integer countofPizzas;
    private OrderState state;

    public Order(Integer id, Integer countofPizzas) {
        this.id = id;
        this.countofPizzas = countofPizzas;
    }

    public Integer getCountofPizzas() {
        return countofPizzas;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Заказ " +
                "номер =" + id +
                ", Количество пицц в заказе =" + countofPizzas +
                '}';
    }

    public OrderState getState() {
        return state;
    }

    public void cooking() {
        this.state = OrderState.COOKING;
        System.out.println("Заказ номер " + this.id + " " + this.state.toString());
    }

    public void delivering() {
        this.state = OrderState.DELIVERING;
        System.out.println("Заказ номер " + this.id + " " + this.state.toString());
    }

    public void done() {
        this.state = OrderState.DONE;
        System.out.println("Заказ номер " + this.id + " " + this.state.toString());
    }

    public void delivered() {
        this.state = OrderState.DELIVERED;
        System.out.println("Заказ номер " + this.id + " " + this.state.toString());
    }

    public void waiting() {
        this.state = OrderState.IN_ORDERLIST;
        System.out.println("Заказ номер " + this.id + " " + this.state.toString());
    }
}
