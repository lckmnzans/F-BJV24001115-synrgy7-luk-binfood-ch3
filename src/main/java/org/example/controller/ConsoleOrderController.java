package org.example.controller;

import org.example.entity.OrderModel;
import org.example.helper.FileHelper;
import org.example.view.ConsoleOrderView;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import static org.example.Const.*;

public class ConsoleOrderController {
    private static final FileHelper fileHelper = new FileHelper();
    private final Scanner inputConsole;
    private final OrderModel model;
    private final ConsoleOrderView view;

    public ConsoleOrderController(OrderModel model, ConsoleOrderView view) {
        this.model = model;
        this.view = view;
        this.inputConsole = new Scanner(System.in);
        init();
    }

    void init() {
        mainMenuProcess();
    }

    private void mainMenuProcess() {
        view.displayMainMenu();
        String userInput = getUserInput();

        if (menuOption.contains(userInput)) {
            String menuId = userInput;
            String menuName = getMenuName(Integer.parseInt(menuId));
            view.displaySelectedMenu(menuId, menuName);
            int menuQty = getUserInputInt(() -> {
                view.displayHeader("Maaf, masukkan jumlah pesanan");
                view.displayBody("+ "+menuName);
                view.displayFooter("", "=>");
                inputConsole.nextLine();
            });
            if (view.displayConfirmationContinue(menuQty, menuId, menuName)) {
                if (menuQty != 0) {
                    model.addOrders(userInput, menuQty);
                    view.displayHeader("Menu berhasil ditambahkan ke daftar pesanan anda");
                    mainMenuProcess();
                }
            } else {
                view.displayHeader("Pesanan menu dibatalkan, kembali ke menu utama");
                mainMenuProcess();
            }
        } else if (subMenuOption.contains(userInput)) {
            subMenuProcess(userInput);
        } else {
            view.displayHeader("Maaf, masukkan pilihan yang sesuai");
            mainMenuProcess();
        }
    }

    private void subMenuProcess(String menu) {
        if (Objects.equals(menu, "00")) {
            boolean isOrderEmpty = isOrderEmpty();
            if (!isOrderEmpty) {
                int[][] item = model.getOrders();
                String invoice = buildInvoiceFormat(item);
                view.displayOrderedMenu(invoice);
                boolean proceeded = getUserInputBool("00");
                if (proceeded) {
                    if (invoice != null) {
                        System.out.println("Pesanan anda terkonfirmasi");
                        fileHelper.writeFile(invoice);
                    } else {
                        System.out.println("Pemesanan anda dibatalkan dan anda akan kembali ke menu utama");
                        mainMenuProcess();
                    }
                }
            } else {
                view.displayHeader("Anda belum memesan menu apapun");
                mainMenuProcess();
            }
        } else {
            view.displayHeader("Pemesanan anda dibatalkan dan anda akan keluar aplikasi");
            System.exit(0);
        }
    }

    private String getMenuName(int menuId) {
        return menuList[menuId-1];
    }

    private boolean isOrderEmpty() {
        int[][] orderedMenu = model.getOrders();
        int countOrderedItem = 0;
        for (int i=0; i < orderedMenu[0].length; i++) {
            if (orderedMenu[0][i] != 0) {
                countOrderedItem++;
            }
        }
        return countOrderedItem == 0;
    }

    private String buildInvoiceFormat(int[][] item) {
        int totalPrice = 0;

        StringBuilder invoice = new StringBuilder()
                .append("=".repeat(ConsoleOrderView.separatorLength))
                .append("\n")
                .append(String.format("%23s","Binar Food"))
                .append("\n")
                .append("=".repeat(ConsoleOrderView.separatorLength))
                .append("\n");

        for (int i=0;i<item[0].length;i++) {
            if (item[0][i] != 0) {
                String orderedMenu = String.format("%-26s | %6d", item[0][i] + " " + menuList[i], item[1][i]);
                invoice.append(orderedMenu).append("\n");
                totalPrice += item[1][i];
            }
        }

        invoice.append("-".repeat(ConsoleOrderView.separatorLength))
                .append("\n")
                .append("Total = ")
                .append(totalPrice)
                .append("\n");

        return invoice.toString();
    }

    private String getUserInput() {
        return inputConsole.nextLine();
    }

    private Integer getUserInputInt(Runnable code) {
        int quantity = 0;
        while (true) {
            if (inputConsole.hasNextInt()) {
                quantity = inputConsole.nextInt();
                inputConsole.nextLine();
                if (quantity == 0) {
                    return 0;
                } else {
                    return quantity;
                }
            } else {
                code.run();
            }
        }
    }

    private Boolean getUserInputBool(String caseTrue) {
        String userInput = inputConsole.nextLine();
        if (Objects.equals(userInput, caseTrue)) {
            return true;
        } else {
            return false;
        }
    }
}
