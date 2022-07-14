package service;

import pojo.Order;

import java.util.Optional;
import java.util.function.Consumer;

public class OrderProcessStep {
    private final Consumer<Order> processOrder;
    private OrderProcessStep nextStep;

    public OrderProcessStep(Consumer<Order> processOrder){
        this.processOrder = processOrder;
    }

    public OrderProcessStep setNextStep(OrderProcessStep nextStep) {
        if(this.nextStep == null) {
            this.nextStep = nextStep;
        } else {
            this.nextStep.setNextStep(nextStep);
        }
        return this;
    }

    public void process(Order order){
        processOrder.accept(order);
        Optional.ofNullable(nextStep)
                .ifPresent(nextStep -> nextStep.process(order));
    }
}
