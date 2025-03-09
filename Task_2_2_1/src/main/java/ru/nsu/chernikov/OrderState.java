package ru.nsu.chernikov;

/**
 * Enum representing the different states of an order.
 */
public enum OrderState {
    IN_ORDERLIST("В списке заказов"),
    COOKING("Готовится"),
    DONE("Готово"),
    DELIVERING("Доставляется"),
    DELIVERED("Доставлено");

    private final String description;

    /**
     * Constructor for the OrderState.
     *
     * @param description the state description
     */
    OrderState(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the order state.
     *
     * @return the description of the state
     */
    @Override
    public String toString() {
        return description;
    }
}