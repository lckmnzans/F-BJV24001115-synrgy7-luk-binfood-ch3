package org.example.view;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ConsoleOrderViewTest {

    private PrintStream stdOut;
    private ByteArrayOutputStream outputStream;
    private ConsoleOrderView view;

    @BeforeEach
    void setup() {
        stdOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        view = new ConsoleOrderView();
    }

    @AfterEach
    void clear() {
        System.setOut(stdOut);
    }

    @Test
    @DisplayName("Menampilkan main menu")
    void displayMainMenu() {
        view.displayMainMenu();

        String actualOutput = outputStream.toString().replaceAll("\\R", "\n");
        String expectedOutput =
                """
                        ====================================
                            Selamat datang di Binar Food
                        ====================================
                        1. Nasi Goreng             |  10000
                        2. Mie Goreng              |  10000
                        3. Ayam Bali               |  12000
                        4. Telur Bali              |  10000
                        5. Orak-arik Ayam          |  10000
                        6. Orak-arik Telur         |   8000
                        7. Es Teh                  |   3000
                        8. Es Jeruk                |   3000
                        ------------------------------------
                        00. Lihat pesanan dan bayar        \s
                        01. Keluar dan batalkan            \s
                        =>\s""";

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    @DisplayName("Menampilkan item pesanan terpilih")
    void displaySelectedMenu() {
    }

    @Test
    @DisplayName("Menampilkan menu memasukkan jumlah pesanan")
    void displayOrderedMenu() {
    }

    @Test
    @DisplayName("Menampilkan menu konfirmasi pesanan")
    void displayConfirmationContinue() {
    }

    @Test
    @DisplayName("Menampilkan header")
    void displayHeader() {
    }

    @Test
    @DisplayName("Menampilkan body")
    void displayBody() {
    }
}