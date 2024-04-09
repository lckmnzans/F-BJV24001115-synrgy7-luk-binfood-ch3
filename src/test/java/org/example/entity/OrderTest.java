package org.example.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    Order order;

    @BeforeEach
    void setup() {
        order = new Order();
    }

    @Test
    @DisplayName("Positive case - menambahkan menu serta jumlahnya")
    void testAddOrders() {
        Assertions.assertTrue(order.addOrders("1", 2));
        int[][] orders = order.getOrders();
        Assertions.assertEquals(2, orders[0][0]); //jumlah item menu 1
        Assertions.assertEquals(20000, orders[1][0]); //total harga item menu 1
    }

    @Test
    @DisplayName("Positive case - melihat isi list order")
    void getOrders() {
        int[][] orders = order.getOrders();
        Assertions.assertNotNull(orders);
        Assertions.assertEquals(2, orders.length);
        Assertions.assertEquals(8, orders[0].length);
        for (int i = 0; i < 8; i++) {
            // setiap nilai awal pada array haruslah 0
            Assertions.assertEquals(0, orders[0][i]);
            Assertions.assertEquals(0, orders[1][i]);
        }
    }

    @Test
    @DisplayName("Positive case - mengosongkan list order")
    void clearOrders() {
        order.addOrders("1", 2);
        order.addOrders("2", 3);
        order.clearOrders();
        int[][] orders = order.getOrders();
        for (int i=0; i<8;i++) {
            // harusnya setiap elemen sudah diganti dengan 0
            Assertions.assertEquals(0, orders[0][i]);
            Assertions.assertEquals(0, orders[1][i]);
        }
    }
}