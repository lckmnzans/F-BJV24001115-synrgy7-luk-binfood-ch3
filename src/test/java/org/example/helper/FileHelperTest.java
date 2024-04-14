package org.example.helper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class FileHelperTest {
    FileHelper fileHelper;

    @BeforeEach
    void setup() {
        fileHelper = new FileHelper();
        deleteFileIfExist();
    }

    @Test
    @DisplayName("Test - cetak file struk ke filepath yang telah ditentukan")
    void testWriteFile() {
        String msg = "Test msg";
        fileHelper.writeFile(msg);

        String fileName = "invoice-00.txt";
        String filePath = System.getProperty("user.home") + "/Documents/" + fileName;
        File file = new File(filePath);
        Assertions.assertTrue(file.exists());

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String content = bufferedReader.readLine();
            bufferedReader.close();

            Assertions.assertEquals(msg, content);

            file.delete();
        } catch (Exception e) {
            Assertions.fail("Exception occured: " + e.getMessage());
        }
    }

    private void deleteFileIfExist() {
        String fileName = "invoice-00.txt";
        String filePath = System.getProperty("user.home") + "/Documents/" + fileName;
        File file = new File(filePath);

        if (file.exists()) {
            file.delete();
        }
    }
}