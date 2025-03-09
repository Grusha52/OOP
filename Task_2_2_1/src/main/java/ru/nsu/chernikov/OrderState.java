package ru.nsu.chernikov;

public enum OrderState {
    IN_ORDERLIST("В списке заказов"),
    COOKING("Готовится"),
    DONE("Готово"),
    DELIVERING("Доставляется"),
    DELIVERED("Доставлено");

    private final String description;

    OrderState(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
};


