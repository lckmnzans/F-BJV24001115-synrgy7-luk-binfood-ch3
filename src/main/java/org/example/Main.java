package org.example;

import org.example.controller.ConsoleOrderController;
import org.example.entity.Order;
import org.example.entity.OrderModel;
import org.example.view.ConsoleOrderView;

public class Main {
    public static void main(String[] args) {
        OrderModel orderModel = new Order();
        ConsoleOrderView orderView = new ConsoleOrderView();
        ConsoleOrderController orderController = new ConsoleOrderController(orderModel, orderView);
    }
}